package me.loki2302.be;

import me.loki2302.be.commands.CreateFollowshipCommand;
import me.loki2302.be.events.FollowshipCreatedEvent;
import me.loki2302.be.writemodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateFollowshipCommandHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private FollowshipEntityRepository followshipEntityRepository;

    public void handle(CreateFollowshipCommand command) {
        UserEntity followerEntity = userEntityRepository.findOne(command.followerId);
        if(followerEntity == null) {
            throw new RuntimeException("Follower does not exist");
        }

        UserEntity leaderEntity = userEntityRepository.findOne(command.leaderId);
        if(leaderEntity == null) {
            throw new RuntimeException("Leader does not exist");
        }

        FollowshipEntityId followshipEntityId = new FollowshipEntityId(followerEntity.id, leaderEntity.id);
        FollowshipEntity followshipEntity = followshipEntityRepository.findOne(followshipEntityId);
        if(followshipEntity != null) {
            throw new RuntimeException("Followship already exists");
        }

        followshipEntity = new FollowshipEntity();
        followshipEntity.followerId = followerEntity.id;
        followshipEntity.leaderId = leaderEntity.id;
        followshipEntityRepository.save(followshipEntity);

        applicationEventPublisher.publishEvent(new FollowshipCreatedEvent(
                followshipEntity.followerId,
                followshipEntity.leaderId));
    }
}
