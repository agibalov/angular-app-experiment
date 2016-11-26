package me.loki2302.controllers;

import me.loki2302.CommandHandler;
import me.loki2302.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity createFollowship() {
        throw new RuntimeException("Not implemented");
    }
}
