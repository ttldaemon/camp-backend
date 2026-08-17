package org.camp.camp.camp.channels;

import org.camp.camp.camp.dto.ChannelCreateRequest;
import org.camp.camp.models.CampChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/camps/channels")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    public ResponseEntity<Void> createChannel(@RequestBody ChannelCreateRequest camp) {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{campId}")
    public ResponseEntity<List<CampChannel>> getChannelsOfCamp(@PathVariable String campId) {
        System.out.println(campId);
        List<CampChannel> channels = channelService.getChannelsOfCamp(UUID.fromString(campId));
        if(channels == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(channels);
    }


}
