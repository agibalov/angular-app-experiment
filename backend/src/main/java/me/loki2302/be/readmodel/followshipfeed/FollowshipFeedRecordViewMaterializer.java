package me.loki2302.be.readmodel.followshipfeed;

import me.loki2302.be.events.CommentCreatedEvent;
import me.loki2302.be.events.FollowshipCreatedEvent;
import me.loki2302.be.events.PostCreatedEvent;
import me.loki2302.be.writemodel.FollowshipEntity;
import me.loki2302.be.writemodel.FollowshipEntityRepository;
import me.loki2302.be.writemodel.UserEntity;
import me.loki2302.be.writemodel.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FollowshipFeedRecordViewMaterializer {
    private final static Logger LOGGER = LoggerFactory.getLogger(FollowshipFeedRecordViewMaterializer.class);

    @Autowired
    private FollowshipFeedRecordViewRepository followshipFeedRecordViewRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private FollowshipEntityRepository followshipEntityRepository;

    public List<FollowshipFeedRecordView> findByUserId(long userId) {
        return followshipFeedRecordViewRepository.findByUserId(userId);
    }

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        // User: "You have posted"
        // User's friends: "xxx has posted"
    }

    @EventListener
    public void handleCommentCreated(CommentCreatedEvent event) {
        // User: "You have posted"
        // User's friends: "xxx has commented on yyy"
        // Post author: "xxx has commented on yyy"
    }

    @EventListener
    public void handleFriendshipCreated(FollowshipCreatedEvent event) {
        // TODO: where do I get usernames for text?

        UserEntity follower = userEntityRepository.findOne(event.followerId);
        UserEntity leader = userEntityRepository.findOne(event.leaderId);

        // handle it for initiator user
        if(true) {
            FollowshipFeedRecordView followshipFeedRecordView = new FollowshipFeedRecordView();
            followshipFeedRecordView.userId = event.followerId;
            followshipFeedRecordView.text = String.format("You're now following %s", leader.name);
            followshipFeedRecordViewRepository.save(followshipFeedRecordView);
        }

        // handle it for target user
        if(true) {
            FollowshipFeedRecordView followshipFeedRecordView = new FollowshipFeedRecordView();
            followshipFeedRecordView.userId = event.leaderId;
            followshipFeedRecordView.text = String.format("User %s is now following you", follower.name);
            followshipFeedRecordViewRepository.save(followshipFeedRecordView);
        }

        // handle it for follower's followers
        if(true) {
            List<FollowshipEntity> followshipEntities = followshipEntityRepository.findByLeaderId(event.followerId);
            List<FollowshipFeedRecordView> followshipFeedRecordViews = followshipEntities.stream().map(fe -> {
                FollowshipFeedRecordView followshipFeedRecordView = new FollowshipFeedRecordView();
                followshipFeedRecordView.userId = fe.followerId;
                followshipFeedRecordView.text = String.format("Your friend %s is now following %s", follower.name, leader.name);
                return followshipFeedRecordView;
            }).collect(Collectors.toList());
            followshipFeedRecordViewRepository.save(followshipFeedRecordViews);
        }

        // handle it for leader's followers, other than this new followers
        if(true) {
            List<FollowshipEntity> followshipEntities = followshipEntityRepository.findByLeaderId(event.leaderId);
            List<FollowshipFeedRecordView> followshipFeedRecordViews = followshipEntities.stream()
                    .filter(fe -> fe.followerId != event.followerId)
                    .map(fe -> {
                        FollowshipFeedRecordView followshipFeedRecordView = new FollowshipFeedRecordView();
                        followshipFeedRecordView.userId = fe.followerId;
                        followshipFeedRecordView.text = String.format("Your friend %s is now followed by %s", leader.name, follower.name);
                        return followshipFeedRecordView;
                    }).collect(Collectors.toList());
            followshipFeedRecordViewRepository.save(followshipFeedRecordViews);
        }
    }
}
