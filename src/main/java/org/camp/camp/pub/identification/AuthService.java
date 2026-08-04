package org.camp.camp.pub.identification;

import lombok.RequiredArgsConstructor;
import org.camp.camp.exceptions.EmailAlreadyExistsException;
import org.camp.camp.exceptions.InvalidCredentialsException;
import org.camp.camp.jwt.JwtAuthFilter;
import org.camp.camp.jwt.JwtService;
import org.camp.camp.models.User;
import org.camp.camp.pub.identification.dto.AuthResponse;
import org.camp.camp.pub.identification.dto.LoginRequest;
import org.camp.camp.pub.identification.dto.RegisterRequest;
import org.camp.camp.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private JwtAuthFilter jwtAuthFilter;;

    @Value("${jwt.access-token-expiry}")
    private long tokenExpiry;

    @Transactional
    public AuthResponse login(LoginRequest request) {
        // find by email if the user with email exists or not
        // matches the password matches or not with the hashed password saved in database
        // build the user with token and send it to frontend
        String email = normalizeEmail(request.getEmail());
        User user = userRepository.findByEmail(email).orElseThrow(InvalidCredentialsException::new);

        if(user.getPasswordHash() == null || !passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        return buildAuthResponse(user);
    }

    public AuthResponse register(RegisterRequest request) {
        // check if another user with the same credentials already present
        // if yes then throw the error
        // if not then build up the user using builder() method
        // save the user
        // may remove the buildAuthResponse, as i think it is not necessary

        String email = normalizeEmail(request.getEmail());

        if(userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        if(userRepository.existsByUsername(request.getUserName())) {
            throw new IllegalArgumentException(request.getUserName());
        }

        User user = User.builder()
            .email(email)
                .username(request.getUserName())
                .displayName(request.getDisplayName())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .isActive(true)
                .build();

        User savedUser = userRepository.save(user);

        return buildAuthResponse(savedUser);
    }

    private AuthResponse buildAuthResponse(User user) {
        String token = jwtService.generateToken(user.getId(), user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .expiresIn(tokenExpiry)
                .user(AuthResponse.UserSummary.builder()
                        .id(user.getId())
                        .userName(user.getUsername())
                        .displayName(user.getDisplayName())
                        .email(user.getEmail())
                        .build()
                ).build();
    }

    private String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase();
    }

    public AuthResponse getCurrentUser() {

        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        assert auth != null;
        UUID userId = (UUID) auth.getPrincipal();

        if(userId == null) {
            return null;
        }

        User user = userRepository.findById(userId).orElseThrow(InvalidCredentialsException::new);
        return buildAuthResponse(user);
    }
}
