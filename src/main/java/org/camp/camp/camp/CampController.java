package org.camp.camp.camp;

import org.camp.camp.camp.dto.CampCreateRequest;
import org.camp.camp.models.Camp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/camps")
public class CampController {

    @Autowired
    private CampService campService;

    // create a new camp
    @PostMapping("/create")
    public ResponseEntity<Camp> createCamp(@RequestBody CampCreateRequest request) {
        System.out.println("Request for create camp");
        Camp camp = campService.createCamp(request);
        return new ResponseEntity<>(camp, HttpStatus.CREATED);
    }

    // get the details of a camp by id
    @GetMapping("/get/{id}")
    public ResponseEntity<Camp> getCampById(@PathVariable String id) {
        System.out.println("id: " + id);
        try {
            Camp camp = campService.getCampById(UUID.fromString(id));
            if (camp == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(camp, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get all the camps in which the user is the owner
    @GetMapping("/getAll/{userId}")
    public ResponseEntity<List<Camp>> getAllCampsOfUser(@PathVariable String userId) {
        System.out.println("userId: " + userId);

        try {
            List<Camp> camps = campService.getAllCampsOfUser(UUID.fromString(userId));
            if (camps == null) {return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
            return new ResponseEntity<>(camps, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // delete the camp on their campID
    // TODO: validate that the request can only be served for the owner of the camp
    @DeleteMapping("/delete/{campId}")
    public ResponseEntity<Void> deleteCamp(@PathVariable String campId) {
            campService.deleteCamp(UUID.fromString(campId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // camps discover controller
    @GetMapping("/discover")
    public ResponseEntity<List<Camp>> discoverCamps() {
        try {
            List<Camp> camps = campService.discoverCamps();
            if (camps == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(camps, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // TODO: update api


}
