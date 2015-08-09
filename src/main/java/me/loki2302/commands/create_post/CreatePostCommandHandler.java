package me.loki2302.commands.create_post;

import me.loki2302.commands.CommandHandler;
import me.loki2302.entities.PostEntity;
import me.loki2302.entities.PostEntityRepository;
import me.loki2302.entities.UserEntity;
import me.loki2302.entities.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePostCommandHandler implements CommandHandler {
    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PostEntityRepository postEntityRepository;

    public CreatePostCommandResult createPost(CreatePostCommand createPostCommand) {
        String title = createPostCommand.title;
        if(title == null || title.isEmpty()) {
            return new FailedToCreatePostCreatePostCommandResult();
        }

        String text = createPostCommand.text;
        if(text == null || text.isEmpty()) {
            return new FailedToCreatePostCreatePostCommandResult();
        }

        long userId = createPostCommand.userId;
        UserEntity author = userEntityRepository.findOne(userId);
        if(author == null) {
            return new FailedToCreatePostCreatePostCommandResult();
        }

        PostEntity postEntity = new PostEntity();
        postEntity.title = title;
        postEntity.text = text;
        postEntity.author = author;
        postEntity = postEntityRepository.save(postEntity);

        PostCreatedCreatePostCommandResult postCreatedCreatePostCommandResult = new PostCreatedCreatePostCommandResult();
        postCreatedCreatePostCommandResult.postId = postEntity.id;
        return postCreatedCreatePostCommandResult;
    }
}
