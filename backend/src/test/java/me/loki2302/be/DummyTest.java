package me.loki2302.be;

import me.loki2302.be.be.App;
import me.loki2302.be.be.CommandHandler;
import me.loki2302.be.be.commands.CreateCommentCommand;
import me.loki2302.be.be.commands.CreateFollowshipCommand;
import me.loki2302.be.be.commands.CreatePostCommand;
import me.loki2302.be.be.commands.CreateUserCommand;
import me.loki2302.be.be.readmodel.followshipfeed.FollowshipFeedRecordView;
import me.loki2302.be.be.readmodel.followshipfeed.FollowshipFeedRecordViewMaterializer;
import me.loki2302.be.be.readmodel.userview.UserView;
import me.loki2302.be.be.readmodel.userview.UserViewMaterializer;
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
    private CommandHandler commandHandler;

    @Autowired
    private UserViewMaterializer userViewMaterializer;

    @Autowired
    private FollowshipFeedRecordViewMaterializer followshipFeedRecordViewMaterializer;

    @Test
    public void dummy() {
        long userAId = commandHandler.handle(new CreateUserCommand("loki2302"));
        long postId = commandHandler.handle(new CreatePostCommand(userAId, "hello there"));
        commandHandler.handle(new CreatePostCommand(userAId, "hello there #2"));
        commandHandler.handle(new CreateCommentCommand(userAId, postId, "that's a nice post"));
        long userBId = commandHandler.handle(new CreateUserCommand("johnsmith"));

        long followerOfAId = commandHandler.handle(new CreateUserCommand("followerOfA"));
        commandHandler.handle(new CreateFollowshipCommand(followerOfAId, userAId));

        long followerOfBId = commandHandler.handle(new CreateUserCommand("followerOfB"));
        commandHandler.handle(new CreateFollowshipCommand(followerOfBId, userBId));

        commandHandler.handle(new CreateFollowshipCommand(userBId, userAId));

        List<UserView> userViews = userViewMaterializer.findAll();
        LOGGER.info("{}", userViews);

        dumpUser(userAId);
        dumpUser(userBId);
        dumpUser(followerOfAId);
        dumpUser(followerOfBId);
    }

    private void dumpUser(long userId) {
        UserView userView = userViewMaterializer.findUser(userId);
        LOGGER.info("*** {} ***", userView);

        List<FollowshipFeedRecordView> records = followshipFeedRecordViewMaterializer.findByUserId(userId);
        records.forEach(r -> LOGGER.info("{}", r));
        LOGGER.info("");
    }
}
