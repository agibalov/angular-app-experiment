package me.loki2302.be.controllers;

import me.loki2302.be.CommandHandler;
import me.loki2302.be.QueryHandler;
import me.loki2302.be.commands.CreateCommentCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ResponseEntity createPost(
            @CurrentUser long userId,
            @RequestBody CommentAttributesDto commentAttributesDto) {

        CreateCommentCommand command = new CreateCommentCommand(
                userId,
                commentAttributesDto.postId,
                commentAttributesDto.text);

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
