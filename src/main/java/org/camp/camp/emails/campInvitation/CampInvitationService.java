package org.camp.camp.emails.campInvitation;

import org.camp.camp.emails.EmailService;
import org.camp.camp.emails.dto.CampInviteRequest;
import org.camp.camp.emails.templates.InviteEmailTemplate;
import org.camp.camp.models.CampInviteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CampInvitationService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CampInviteRepository campInviteRepository;

    @Value("${frontend.web.url}")
    private String frontendUrl;

    public void sendInvitation(CampInviteRequest request) {
        try {

            CampInviteEntity invite = CampInviteEntity.builder()
                    .invitedBy(request.getInvitedBy())
                    .inviteeEmail(request.getInviteeEmail())
                    .campId(request.getCampId())
                    .expiresAt(request.getExpiresAt())
                    .build();

            CampInviteEntity savedInvite = campInviteRepository.save(invite);

            emailService.sendEmail(
                    savedInvite.getInviteeEmail(),
                    "Camp - Invitation",
                    InviteEmailTemplate.generateInviteEmail("My Camp", String.format("%s/invite/accept/%s",frontendUrl, savedInvite.getId().toString()))
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
