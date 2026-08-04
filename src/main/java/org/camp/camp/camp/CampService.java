package org.camp.camp.camp;

import org.camp.camp.camp.dto.CampCreateRequest;
import org.camp.camp.models.Camp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class CampService {

    @Autowired
    private CampRepository campRepository;

    public Camp createCamp(CampCreateRequest request) {

        if (campRepository.existsBySlug(request.getSlug())) {
            throw new IllegalArgumentException("Slug already exists");
        }

        Camp camp = Camp.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .description(request.getDescription())
                .avatarUrl(request.getAvatarUrl())
                .visibility(request.getVisibility())
                .ownerId(request.getOwnerId())
                .memberCount(1)
                .tags(request.getTags())
                .build();

        Camp campCreated = campRepository.save(camp);

        if(campCreated.getId() == null){
            return null;
        }

        return campCreated;

    }

    public Camp getCampById(UUID id) {
        if(id == null)  return null;
        return campRepository.findById(id).orElse(null);
    }

    public List<Camp> getAllCampsOfUser(UUID ownerId) {

        if(ownerId == null)  return null;
        return campRepository.findByOwnerId(ownerId);
    }

    public void deleteCamp(UUID campUUID) {
        if(campUUID == null)  return;
        Camp camp = campRepository.findById(campUUID).orElseThrow(() -> new IllegalArgumentException("Camp not found with id: " + campUUID));

//        if(!camp.getOwnerId().equals(userId)) {
//            throw new IllegalArgumentException("User is not the owner of the camp");
//        }

        camp.setDeletedAt(Instant.now());
        campRepository.save(camp);
    }
}
