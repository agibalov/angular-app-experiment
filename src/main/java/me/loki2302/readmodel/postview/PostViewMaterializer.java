package me.loki2302.readmodel.postview;

import me.loki2302.events.CommentCreatedEvent;
import me.loki2302.events.PostCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostViewMaterializer {
    private final static Logger LOGGER = LoggerFactory.getLogger(PostViewMaterializer.class);

    @Autowired
    private PostViewRepository postViewRepository;

    public PostView findPost(long postId) {
        return postViewRepository.findOne(postId);
    }

    public List<PostView> findAll() {
        return postViewRepository.findAll();
    }

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        PostView postView = new PostView();
        postView.id = event.postId;
        postView.text = event.text;
        postView.commentCount = 0;
        postView.userId = event.userId;
        postViewRepository.save(postView);
    }

    @EventListener
    public void handleCommentCreated(CommentCreatedEvent event) {
        PostView postView = postViewRepository.findOne(event.postId);
        ++postView.commentCount;
        postViewRepository.save(postView);
    }
}
