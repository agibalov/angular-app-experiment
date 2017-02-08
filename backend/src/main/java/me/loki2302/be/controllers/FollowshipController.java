package me.loki2302.be.controllers;

import me.loki2302.be.CreateFollowshipCommandHandler;
import me.loki2302.be.commands.CreateFollowshipCommand;
import me.loki2302.be.readmodel.followshipfeed.FollowshipFeedRecordQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FollowshipController {
    @Autowired
    private CreateFollowshipCommandHandler createFollowshipCommandHandler;

    @Autowired
    private FollowshipFeedRecordQueryHandler followshipFeedRecordQueryHandler;

    @RequestMapping(value = "/followships", method = RequestMethod.POST)
    public ResponseEntity createFollowship(
            @CurrentUser Long userId,
            @RequestBody FollowshipAttributesDto followshipAttributesDto) {

        CreateFollowshipCommand command = new CreateFollowshipCommand(
                userId,
                followshipAttributesDto.leaderId);

        createFollowshipCommandHandler.handle(command);

        return ResponseEntity.noContent().build();
    }
}
