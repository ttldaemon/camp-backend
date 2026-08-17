package org.camp.camp.camp.channels;

import org.camp.camp.camp.dto.ChannelCreateRequest;
import org.camp.camp.models.Camp;
import org.camp.camp.models.CampChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChannelService {

    @Autowired
    private ChannelRepository channelRepository;

    public String createChannel(ChannelCreateRequest channel) {
        // validate the channel data

        System.out.println("Creating channel with request: " + channel);

        CampChannel newChannel = CampChannel.builder()
                .campId(channel.getCampId())
                .name(channel.getName())
                .description(channel.getDescription())
                .type(channel.getType())
                .build();
        // create a new CampChannel entity

        System.out.println("New channel entity: " + newChannel);

        CampChannel savedChannel = channelRepository.save(newChannel);

        System.out.println("Channel created: " + savedChannel);
        return savedChannel.getId().toString();
    }

    public List<CampChannel> getChannelsOfCamp(UUID campId) {
        // find all the camps from the db based on campId
        try {
            return channelRepository.findByCampId(campId);
        } catch (Exception e) {
            return null;
        }
    }
}
