package me.loki2302.api.create_post;

import me.loki2302.api.ApiRequestHandler;
import me.loki2302.commands.create_post.CreatePostCommand;
import me.loki2302.commands.create_post.CreatePostCommandHandler;
import me.loki2302.commands.create_post.CreatePostCommandResult;
import me.loki2302.commands.create_post.PostCreatedCreatePostCommandResult;
import me.loki2302.events.PostCreatedDomainEvent;
import me.loki2302.queries.get_post.GetPostQuery;
import me.loki2302.queries.get_post.GetPostQueryHandler;
import me.loki2302.queries.get_post.GetPostQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreatePostApiRequestHandler implements ApiRequestHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreatePostCommandHandler createPostCommandHandler;

    @Autowired
    private GetPostQueryHandler getPostQueryHandler;

    public CreatePostApiResponse createPost(CreatePostApiRequest createPostApiRequest) {
        CreatePostCommand createPostCommand = new CreatePostCommand();
        createPostCommand.userId = createPostApiRequest.userId;
        createPostCommand.title = createPostApiRequest.title;
        createPostCommand.text = createPostApiRequest.text;
        CreatePostCommandResult createPostCommandResult = createPostCommandHandler.createPost(createPostCommand);
        if(!(createPostCommandResult instanceof PostCreatedCreatePostCommandResult)) {
            return new FailedToCreatePostCreatePostApiResponse();
        }

        PostCreatedCreatePostCommandResult postCreatedCreatePostCommandResult =
                (PostCreatedCreatePostCommandResult)createPostCommandResult;

        PostCreatedDomainEvent postCreatedDomainEvent = new PostCreatedDomainEvent();
        postCreatedDomainEvent.postId = postCreatedCreatePostCommandResult.postId;
        postCreatedDomainEvent.userId = createPostApiRequest.userId; // ...
        postCreatedDomainEvent.title = createPostApiRequest.title; // ...
        postCreatedDomainEvent.text = createPostApiRequest.text; // ...
        applicationEventPublisher.publishEvent(postCreatedDomainEvent);

        GetPostQuery getPostQuery = new GetPostQuery();
        getPostQuery.postId = postCreatedCreatePostCommandResult.postId;
        GetPostQueryResult getPostQueryResult = getPostQueryHandler.getPost(getPostQuery);

        PostCreatedCreatePostApiResponse postCreatedCreatePostApiResponse = new PostCreatedCreatePostApiResponse();
        postCreatedCreatePostApiResponse.postId = getPostQueryResult.postId;
        postCreatedCreatePostApiResponse.userId = getPostQueryResult.userId;
        postCreatedCreatePostApiResponse.title = getPostQueryResult.title;
        postCreatedCreatePostApiResponse.text = getPostQueryResult.text;
        return postCreatedCreatePostApiResponse;
    }
}
