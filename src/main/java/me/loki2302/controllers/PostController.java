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
public class PostController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity createPost(@RequestBody CommentAttributesDto commentAttributesDto) {
        CreatePostCommand command = new CreatePostCommand(0, commentAttributesDto.text);
        long postId = commandHandler.handle(command);
        URI location = fromMethodCall(on(PostController.class).getPost(postId)).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity getPosts() {
        throw new RuntimeException("Not implemented");
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable("postId") long postId) {
        throw new RuntimeException("Not implemented");
    }
}
