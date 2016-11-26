package me.loki2302.controllers;

import me.loki2302.CommandHandler;
import me.loki2302.QueryHandler;
import me.loki2302.commands.CreatePostCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class CommentController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody PostAttributesDto postAttributesDto) {
        CreatePostCommand command = new CreatePostCommand(0, postAttributesDto.text);
        long commentId = commandHandler.handle(command);
        URI location = fromMethodCall(on(CommentController.class).getComment(commentId)).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity getComments() {
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value = "/comments/{commentId}", method = RequestMethod.GET)
    public ResponseEntity getComment(@PathVariable("commentId") long postId) {
        throw new RuntimeException("Not implemented");
    }
}
