package me.loki2302.be;

import me.loki2302.be.commands.CreatePostCommand;
import me.loki2302.be.commands.CreateUserCommand;
import me.loki2302.be.events.UserCreatedEvent;
import me.loki2302.be.writemodel.UserEntity;
import me.loki2302.be.writemodel.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommandHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private CreatePostCommandHandler createPostCommandHandler;

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

        // Should I be emitting events instead of commands?
        for(int i = 0; i < 5; ++i) {
            CreatePostCommand createPostCommand = new CreatePostCommand(
                    userEntity.id,
                    String.format("A dummy post #%d of user %s.", i + 1, userEntity.name));
            createPostCommandHandler.handle(createPostCommand);
        }

        return userEntity.id;
    }
}
