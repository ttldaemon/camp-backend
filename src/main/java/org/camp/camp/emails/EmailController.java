package org.camp.camp.emails;


import jakarta.mail.MessagingException;
import org.camp.camp.emails.campInvitation.CampInvitationService;
import org.camp.camp.emails.dto.CampInviteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private CampInvitationService campInvitationService;

    @PostMapping("/invite/send")
    public ResponseEntity<String> sendInvite(@RequestBody CampInviteRequest request) {

        System.out.println("Sending invite to: " + request.getInviteeEmail());

        try {
            campInvitationService.sendInvitation(request);

            return ResponseEntity.ok("Invite sent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/test")
    public ResponseEntity<String> sendTestEmail(
            @RequestParam String to
    ) throws MessagingException {
        System.out.println("Sending test email to: " + to);
        emailService.sendEmail(
                to,
                "Camp - Test Email",
                "Your Spring Boot email setup is working!"
        );

        return ResponseEntity.ok("Email sent successfully");
    }


}
