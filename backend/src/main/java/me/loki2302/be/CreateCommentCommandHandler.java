package me.loki2302.be;

import me.loki2302.be.commands.CreateCommentCommand;
import me.loki2302.be.events.CommentCreatedEvent;
import me.loki2302.be.writemodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateCommentCommandHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PostEntityRepository postEntityRepository;

    @Autowired
    private CommentEntityRepository commentEntityRepository;

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

        applicationEventPublisher.publishEvent(new CommentCreatedEvent(
                userEntity.id,
                postEntity.id,
                commentEntity.id,
                commentEntity.text));

        return commentEntity.id;
    }
}
