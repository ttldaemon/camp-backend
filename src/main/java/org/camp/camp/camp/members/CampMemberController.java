package org.camp.camp.camp.members;

import org.camp.camp.models.Camp;
import org.camp.camp.models.CampMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/camps/member")
public class CampMemberController {
    @Autowired
    private CampMemberService campMemberService;

    // this controller will return the camps in which the member is a part of, either be it owner or member
    @GetMapping
    public ResponseEntity<List<Camp>> getMemberCamps(@AuthenticationPrincipal UUID userId) {
        List<Camp> campsList = campMemberService.memberOfCamp(userId);
        return ResponseEntity.ok(campsList);
    }

    @PostMapping("/add")
    public ResponseEntity<CampMember> addCampMember(@RequestBody CampMember campMember) {
        try {
            CampMember savedCampMember = campMemberService.addCampMember(campMember);
            return ResponseEntity.ok(savedCampMember);
        } catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteCampMember(@RequestBody CampMember campMember) {
        try {
            campMemberService.deleteCampMember(campMember);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
