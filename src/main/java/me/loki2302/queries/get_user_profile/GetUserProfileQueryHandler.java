package me.loki2302.queries.get_user_profile;

import me.loki2302.entities.UserProfileView;
import me.loki2302.entities.UserProfileViewRepository;
import me.loki2302.events.PostCreatedDomainEvent;
import me.loki2302.events.UserCreatedDomainEvent;
import me.loki2302.queries.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GetUserProfileQueryHandler implements QueryHandler {
    private final static Logger log = LoggerFactory.getLogger(GetUserProfileQueryHandler.class);

    @Autowired
    private UserProfileViewRepository userProfileViewRepository;

    public GetUserProfileQueryResult getUserProfile(GetUserProfileQuery getUserProfileQuery) {
        long userId = getUserProfileQuery.userId;
        UserProfileView userProfileView = userProfileViewRepository.findOne(userId);
        if(userProfileView == null) {
            // TODO: replace with return value
            throw new RuntimeException("Looks like this user does not exist");
        }

        GetUserProfileQueryResult getUserProfileQueryResult = new GetUserProfileQueryResult();
        getUserProfileQueryResult.userId = userProfileView.id;
        getUserProfileQueryResult.name = userProfileView.name;
        getUserProfileQueryResult.postCount = userProfileView.postCount;

        return getUserProfileQueryResult;
    }

    @EventListener
    public void createUserProfileView(UserCreatedDomainEvent event) {
        log.info("Got application event: {}", event);

        UserProfileView userProfileView = new UserProfileView();
        userProfileView.id = event.userId;
        userProfileView.name = event.name;
        userProfileView.postCount = 0;
        userProfileViewRepository.save(userProfileView);
    }

    @EventListener
    public void updateUserProfileViewPostCount(PostCreatedDomainEvent event) {
        long userId = event.userId;
        UserProfileView userProfileView = userProfileViewRepository.findOne(userId);
        ++userProfileView.postCount;
        userProfileViewRepository.save(userProfileView);
    }
}
