package me.loki2302.commands.create_user;

import me.loki2302.commands.CommandHandler;
import me.loki2302.entities.UserEntity;
import me.loki2302.entities.UserEntityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserCommandHandler implements CommandHandler {
    private final static Logger log = LoggerFactory.getLogger(CreateUserCommandHandler.class);

    @Autowired
    private UserEntityRepository userEntityRepository;

    public CreateUserCommandResult createUser(CreateUserCommand createUserCommand) {
        String name = createUserCommand.name;
        if(name == null || name.isEmpty()) {
            return new UserNameIsNotValidErrorCreateUserCommandResult();
        }

        UserEntity existingUser = userEntityRepository.findByName(name);
        if(existingUser != null) {
            return new UserAlreadyExistsErrorCreateUserCommandResult();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.name = name;
        userEntity = userEntityRepository.save(userEntity);

        UserCreatedCreateUserCommandResult userCreatedCreateUserCommandResult = new UserCreatedCreateUserCommandResult();
        userCreatedCreateUserCommandResult.userId = userEntity.id;
        return userCreatedCreateUserCommandResult;
    }
}
