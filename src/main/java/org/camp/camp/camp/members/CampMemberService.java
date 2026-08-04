package org.camp.camp.camp.members;

import org.camp.camp.camp.CampRepository;
import org.camp.camp.models.Camp;
import org.camp.camp.models.CampMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CampMemberService {
    @Autowired
    private CampRepository campRepository;

    @Autowired
    private CampMemberRepository campMemberRepository;

    // this function will return camps in which the user is a member
    public List<Camp> memberOfCamp(UUID userId) {
        System.out.println("User is " + userId);
        if (userId == null) {
            throw new IllegalArgumentException("User id is required");
        }


        List<Camp> camps = new ArrayList<>();
//      camps.addAll(campMemberRepository.findByUserId(userId));

        System.out.println(campRepository.findByOwnerId(userId));
        System.out.println(campMemberRepository.findByUserId(userId));


        return new ArrayList<>();
    }

    // this function will add a new member to a camp
    public CampMember addCampMember(CampMember campMember) {
        if(campMember == null){
            throw new IllegalArgumentException("CampMember is required");
        }
        return campMemberRepository.save(campMember);
    }

    public void deleteCampMember(CampMember campMember) {
        if(campMember == null){
            throw new IllegalArgumentException("CampMember is required");
        }
        campMemberRepository.delete(campMember);
    }

    // remove a member from a camp

}
