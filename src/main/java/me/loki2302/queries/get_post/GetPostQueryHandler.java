package me.loki2302.queries.get_post;

import me.loki2302.entities.PostView;
import me.loki2302.entities.PostViewRepository;
import me.loki2302.events.PostCreatedDomainEvent;
import me.loki2302.queries.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GetPostQueryHandler implements QueryHandler {
    private final static Logger log = LoggerFactory.getLogger(GetPostQueryHandler.class);

    @Autowired
    private PostViewRepository postViewRepository;

    @EventListener
    public void createPostView(PostCreatedDomainEvent event) {
        log.info("Got application event: {}", event);

        PostView postView = new PostView();
        postView.id = event.postId;
        postView.userId = event.userId;
        postView.title = event.title;
        postView.text = event.text;
        postViewRepository.save(postView);
    }

    public GetPostQueryResult getPost(GetPostQuery getPostQuery) {
        PostView postView = postViewRepository.findOne(getPostQuery.postId);
        if(postView == null) {
            // TODO: replace with return value
            throw new RuntimeException("post not found");
        }

        GetPostQueryResult getPostQueryResult = new GetPostQueryResult();
        getPostQueryResult.postId = postView.id;
        getPostQueryResult.userId = postView.userId;
        getPostQueryResult.title = postView.title;
        getPostQueryResult.text = postView.text;
        return getPostQueryResult;
    }
}
