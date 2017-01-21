package me.loki2302.be;

import me.loki2302.be.commands.CreateCommentCommand;
import me.loki2302.be.commands.CreateUserCommand;
import me.loki2302.be.events.CommentCreatedEvent;
import me.loki2302.be.events.FollowshipCreatedEvent;
import me.loki2302.be.events.UserCreatedEvent;
import me.loki2302.be.commands.CreateFollowshipCommand;
import me.loki2302.be.commands.CreatePostCommand;
import me.loki2302.be.events.PostCreatedEvent;
import me.loki2302.be.writemodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CommandHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PostEntityRepository postEntityRepository;

    @Autowired
    private CommentEntityRepository commentEntityRepository;

    @Autowired
    private FollowshipEntityRepository followshipEntityRepository;

    public long handle(CreateUserCommand command) {
        String name = command.name;

        UserEntity userEntity = userEntityRepository.findByName(name);
        if(userEntity != null) {
            throw new UserAlreadyExistsException();
        }

        userEntity = new UserEntity();
        userEntity.name = name;
        userEntity = userEntityRepository.save(userEntity);

        applicationEventPublisher.publishEvent(new UserCreatedEvent(userEntity.id, userEntity.name));

        return userEntity.id;
    }

    public long handle(CreatePostCommand command) {
        UserEntity userEntity = userEntityRepository.findOne(command.userId);
        if(userEntity == null) {
            throw new RuntimeException("User does not exist");
        }

        PostEntity postEntity = new PostEntity();
        postEntity.text = command.text;
        postEntity.user = userEntity;
        postEntity = postEntityRepository.save(postEntity);

        applicationEventPublisher.publishEvent(new PostCreatedEvent(userEntity.id, postEntity.id, postEntity.text));

        return postEntity.id;
    }

    public long handle(CreateCommentCommand command) {
        UserEntity userEntity = userEntityRepository.findOne(command.userId);
        if(userEntity == null) {
            throw new RuntimeException("User does not exist");
        }

        PostEntity postEntity = postEntityRepository.findOne(command.postId);
        if(postEntity == null) {
            throw new RuntimeException("Post does not exist");
        }

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.user = userEntity;
        commentEntity.post = postEntity;
        commentEntity.text = command.text;
        commentEntity = commentEntityRepository.save(commentEntity);

        applicationEventPublisher.publishEvent(new CommentCreatedEvent(userEntity.id, postEntity.id, commentEntity.id, commentEntity.text));

        return commentEntity.id;
    }

    public void handle(CreateFollowshipCommand command) {
        UserEntity followerEntity = userEntityRepository.findOne(command.followerId);
        if(followerEntity == null) {
            throw new RuntimeException("Follower does not exist");
        }

        UserEntity leaderEntity = userEntityRepository.findOne(command.leaderId);
        if(leaderEntity == null) {
            throw new RuntimeException("Leader does not exist");
        }

        FollowshipEntity followshipEntity = followshipEntityRepository.findOne(new FollowshipEntityId(followerEntity.id, leaderEntity.id));
        if(followshipEntity != null) {
            throw new RuntimeException("Followship already exists");
        }

        followshipEntity = new FollowshipEntity();
        followshipEntity.followerId = followerEntity.id;
        followshipEntity.leaderId = leaderEntity.id;
        followshipEntityRepository.save(followshipEntity);

        applicationEventPublisher.publishEvent(new FollowshipCreatedEvent(followshipEntity.followerId, followshipEntity.leaderId));
    }
}
