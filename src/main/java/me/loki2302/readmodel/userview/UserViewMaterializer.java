package me.loki2302.readmodel.userview;

import me.loki2302.events.CommentCreatedEvent;
import me.loki2302.events.FollowshipCreatedEvent;
import me.loki2302.events.PostCreatedEvent;
import me.loki2302.events.UserCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserViewMaterializer {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserViewMaterializer.class);

    @Autowired
    private UserViewRepository userViewRepository;

    public UserView findUser(long userId) {
        return userViewRepository.findOne(userId);
    }

    public List<UserView> findAll() {
        return userViewRepository.findAll();
    }

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        LOGGER.info("{}", event);

        UserView userView = new UserView();
        userView.userId = event.id;
        userView.name = event.name;
        userView.postCount = 0;
        userView.commentCount = 0;
        userView.followerCount = 0;
        userView.followsCount = 0;
        userViewRepository.save(userView);
    }

    @EventListener
    public void handlePostCreated(PostCreatedEvent event) {
        LOGGER.info("{}", event);

        UserView userView = userViewRepository.findOne(event.userId);
        ++userView.postCount;
        userViewRepository.save(userView);
    }

    @EventListener
    public void handleCommentCreated(CommentCreatedEvent event) {
        LOGGER.info("{}", event);

        UserView userView = userViewRepository.findOne(event.userId);
        ++userView.commentCount;
        userViewRepository.save(userView);
    }

    @EventListener
    public void handleFriendshipCreated(FollowshipCreatedEvent event) {
        LOGGER.info("{}", event);

        UserView follower = userViewRepository.findOne(event.followerId);
        ++follower.followsCount;
        userViewRepository.save(follower);

        UserView leader = userViewRepository.findOne(event.leaderId);
        ++leader.followerCount;
        userViewRepository.save(leader);
    }
}
