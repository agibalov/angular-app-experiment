package me.loki2302.be.be.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowshipFeedController {
    @RequestMapping(value = "/users/{userId}/feed", method = RequestMethod.GET)
    public ResponseEntity getFollowshipFeedRecords() {
        throw new RuntimeException("Not implemented");
    }
}
