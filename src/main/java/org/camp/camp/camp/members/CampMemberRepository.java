package org.camp.camp.camp.members;

import org.camp.camp.models.Camp;
import org.camp.camp.models.CampMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CampMemberRepository extends JpaRepository<CampMember, UUID> {
    List<Camp> findByUserId(UUID userId);


}
