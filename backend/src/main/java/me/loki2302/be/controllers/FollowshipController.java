package me.loki2302.be.controllers;

import me.loki2302.be.CommandHandler;
import me.loki2302.be.QueryHandler;
import me.loki2302.be.commands.CreateFollowshipCommand;
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
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(value = "/followships", method = RequestMethod.POST)
    public ResponseEntity createFollowship(
            @CurrentUser long userId,
            @RequestBody FollowshipAttributesDto followshipAttributesDto) {

        CreateFollowshipCommand command = new CreateFollowshipCommand(
                userId,
                followshipAttributesDto.leaderId);

        commandHandler.handle(command);

        return ResponseEntity.noContent().build();
    }
}
