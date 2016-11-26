package me.loki2302.controllers;

import me.loki2302.CommandHandler;
import me.loki2302.QueryHandler;
import me.loki2302.commands.CreateFollowshipCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowshipController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(value = "/followships", method = RequestMethod.POST)
    public ResponseEntity createFollowship(@RequestBody FollowshipAttributesDto followshipAttributesDto) {
        CreateFollowshipCommand command = new CreateFollowshipCommand(
                followshipAttributesDto.followerId,
                followshipAttributesDto.leaderId);

        commandHandler.handle(command);

        return ResponseEntity.noContent().build();
    }
}
