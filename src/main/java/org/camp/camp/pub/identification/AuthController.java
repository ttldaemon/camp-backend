package org.camp.camp.pub.identification;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.camp.camp.jwt.JwtAuthFilter;
import org.camp.camp.pub.identification.dto.AuthResponse;
import org.camp.camp.pub.identification.dto.LoginRequest;
import org.camp.camp.pub.identification.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildAuthCookie(response.getToken(), response.getExpiresIn()).toString())
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, buildAuthCookie(response.getToken(), response.getExpiresIn()).toString())
                .body(response);
    }

//    @PostMapping("/reset-password")
//    public ResponseEntity<> resetPassword(@RequestBody String email) {
//
//    }

    @GetMapping("/me")
    public ResponseEntity<AuthResponse> me() {
        System.out.println("Request me");
        AuthResponse res = authService.getCurrentUser();
        if(res == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    private ResponseCookie buildAuthCookie(String token, long expiresInMillis) {
        return ResponseCookie.from(JwtAuthFilter.ACCESS_TOKEN_COOKIE, token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .sameSite("Lax")
                .maxAge(java.time.Duration.ofMillis(expiresInMillis))
                .build();
    }

}

