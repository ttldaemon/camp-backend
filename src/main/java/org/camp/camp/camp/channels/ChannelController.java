package org.camp.camp.camp.channels;

import org.camp.camp.camp.dto.ChannelCreateRequest;
import org.camp.camp.models.CampChannel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/camps/channels")
public class ChannelController {

    @PostMapping("/create")
    public ResponseEntity<Void> createChannel(@RequestBody ChannelCreateRequest camp) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
