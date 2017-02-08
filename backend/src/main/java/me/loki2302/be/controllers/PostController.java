package me.loki2302.be.controllers;

import me.loki2302.be.CommandHandler;
import me.loki2302.be.QueryHandler;
import me.loki2302.be.commands.CreatePostCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity createPost(
            @CurrentUser Long userId,
            @RequestBody CommentAttributesDto commentAttributesDto) {

        CreatePostCommand command = new CreatePostCommand(userId, commentAttributesDto.text);
        long postId = commandHandler.handle(command);
        URI location = fromMethodCall(on(PostController.class).getPost(postId)).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity getPosts(@CurrentUser Long userId) {
        List<PostDto> postDtos = new ArrayList<>();

        for(int i = 0; i < 5; ++i) {
            PostDto postDto = new PostDto();
            postDto.postId = i;
            postDto.text = String.format("Post #%d of user %d", i, userId);
            postDtos.add(postDto);
        }

        return ResponseEntity.ok(postDtos);
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable("postId") long postId) {
        throw new RuntimeException("Not implemented");
    }

    public static class PostDto {
        public long postId;
        public String text;
    }
}
