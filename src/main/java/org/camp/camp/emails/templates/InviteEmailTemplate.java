package org.camp.camp.emails.templates;

public class InviteEmailTemplate {

    public static String generateInviteEmail(
            String campName,
            String inviteLink
    ) {
        return "Invitation to join " + campName + "!\n\n" +
                "You have been invited to join the camp \"" + campName + "\".\n" +
                "Click the link below to accept the invitation and join the camp:\n\n" +
                inviteLink + "\n\n" +
                "We look forward to having you in our community!";
    }
}