package me.loki2302.be.controllers;

import me.loki2302.be.CreatePostCommandHandler;
import me.loki2302.be.commands.CreatePostCommand;
import me.loki2302.be.readmodel.postview.PostView;
import me.loki2302.be.readmodel.postview.PostQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodCall;
import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private CreatePostCommandHandler createPostCommandHandler;

    @Autowired
    private PostQueryHandler postQueryHandler;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public ResponseEntity createPost(
            @CurrentUser Long userId,
            @RequestBody CommentAttributesDto commentAttributesDto) {

        CreatePostCommand command = new CreatePostCommand(userId, commentAttributesDto.text);
        long postId = createPostCommandHandler.handle(command);
        URI location = fromMethodCall(on(PostController.class).getPost(postId)).build().toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public ResponseEntity getPosts(@CurrentUser Long userId) {
        List<PostView> postViews = postQueryHandler.findByUserId(userId);
        List<PostDto> postDtos = postDtosFromPostViews(postViews);
        return ResponseEntity.ok(postDtos);
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public ResponseEntity getPost(@PathVariable("postId") long postId) {
        PostView postView = postQueryHandler.findPost(postId);
        if(postView == null) {
            return ResponseEntity.notFound().build();
        }

        PostDto postDto = postDtoFromPostView(postView);
        return ResponseEntity.ok(postDto);
    }

    private static PostDto postDtoFromPostView(PostView postView) {
        PostDto postDto = new PostDto();
        postDto.postId = postView.id;
        postDto.text = postView.text;
        return postDto;
    }

    private static List<PostDto> postDtosFromPostViews(List<PostView> postViews) {
        return postViews.stream()
                .map(PostController::postDtoFromPostView)
                .collect(Collectors.toList());
    }

    public static class PostDto {
        public long postId;
        public String text;
    }
}
