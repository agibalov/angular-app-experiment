package me.loki2302.be;

import me.loki2302.be.commands.CreateCommentCommand;
import me.loki2302.be.commands.CreateFollowshipCommand;
import me.loki2302.be.commands.CreatePostCommand;
import me.loki2302.be.commands.CreateUserCommand;
import me.loki2302.be.readmodel.followshipfeed.FollowshipFeedRecordView;
import me.loki2302.be.readmodel.followshipfeed.FollowshipFeedRecordQueryHandler;
import me.loki2302.be.readmodel.userview.UserView;
import me.loki2302.be.readmodel.userview.UserQueryHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = App.class)
@RunWith(SpringRunner.class)
public class DummyTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(DummyTest.class);

    @Autowired
    private CreateUserCommandHandler createUserCommandHandler;

    @Autowired
    private CreatePostCommandHandler createPostCommandHandler;

    @Autowired
    private CreateCommentCommandHandler createCommentCommandHandler;

    @Autowired
    private CreateFollowshipCommandHandler createFollowshipCommandHandler;

    @Autowired
    private UserQueryHandler userQueryHandler;

    @Autowired
    private FollowshipFeedRecordQueryHandler followshipFeedRecordQueryHandler;

    @Test
    public void dummy() {
        long userAId = createUserCommandHandler.handle(new CreateUserCommand("loki2302"));
        long postId = createPostCommandHandler.handle(new CreatePostCommand(userAId, "hello there"));
        createPostCommandHandler.handle(new CreatePostCommand(userAId, "hello there #2"));
        createCommentCommandHandler.handle(new CreateCommentCommand(userAId, postId, "that's a nice post"));
        long userBId = createUserCommandHandler.handle(new CreateUserCommand("johnsmith"));

        long followerOfAId = createUserCommandHandler.handle(new CreateUserCommand("followerOfA"));
        createFollowshipCommandHandler.handle(new CreateFollowshipCommand(followerOfAId, userAId));

        long followerOfBId = createUserCommandHandler.handle(new CreateUserCommand("followerOfB"));
        createFollowshipCommandHandler.handle(new CreateFollowshipCommand(followerOfBId, userBId));

        createFollowshipCommandHandler.handle(new CreateFollowshipCommand(userBId, userAId));

        List<UserView> userViews = userQueryHandler.findAll();
        LOGGER.info("{}", userViews);

        dumpUser(userAId);
        dumpUser(userBId);
        dumpUser(followerOfAId);
        dumpUser(followerOfBId);
    }

    private void dumpUser(long userId) {
        UserView userView = userQueryHandler.findOneById(userId);
        LOGGER.info("*** {} ***", userView);

        List<FollowshipFeedRecordView> records = followshipFeedRecordQueryHandler.findByUserId(userId);
        records.forEach(r -> LOGGER.info("{}", r));
        LOGGER.info("");
    }
}
