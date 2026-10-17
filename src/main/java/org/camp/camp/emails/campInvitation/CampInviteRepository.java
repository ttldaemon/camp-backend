package org.camp.camp.emails.campInvitation;

import org.camp.camp.models.CampInviteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampInviteRepository extends JpaRepository<CampInviteEntity, Long> {
    CampInviteEntity findByInviteeEmail(String email);
}
