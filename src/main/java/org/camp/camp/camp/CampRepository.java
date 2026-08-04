package org.camp.camp.camp;

import org.camp.camp.models.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CampRepository extends JpaRepository<Camp, UUID> {
    boolean existsBySlug(String slug);
    List<Camp> findByOwnerId(UUID id);
}
