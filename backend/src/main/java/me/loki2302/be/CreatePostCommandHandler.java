package me.loki2302.be;

import me.loki2302.be.commands.CreatePostCommand;
import me.loki2302.be.events.PostCreatedEvent;
import me.loki2302.be.writemodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreatePostCommandHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private PostEntityRepository postEntityRepository;

    public long handle(CreatePostCommand command) {
        UserEntity userEntity = userEntityRepository.findOne(command.userId);
        if(userEntity == null) {
            throw new RuntimeException("User does not exist");
        }

        PostEntity postEntity = new PostEntity();
        postEntity.text = command.text;
        postEntity.user = userEntity;
        postEntity = postEntityRepository.save(postEntity);

        applicationEventPublisher.publishEvent(new PostCreatedEvent(
                userEntity.id,
                postEntity.id,
                postEntity.text));

        return postEntity.id;
    }
}
