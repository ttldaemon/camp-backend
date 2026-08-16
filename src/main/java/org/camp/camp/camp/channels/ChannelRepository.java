package org.camp.camp.camp.channels;

import org.camp.camp.models.CampChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChannelRepository extends JpaRepository<CampChannel, UUID> {

}
