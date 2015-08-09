package me.loki2302.api.create_user;

import me.loki2302.api.ApiRequestHandler;
import me.loki2302.commands.create_user.CreateUserCommand;
import me.loki2302.commands.create_user.CreateUserCommandHandler;
import me.loki2302.commands.create_user.CreateUserCommandResult;
import me.loki2302.commands.create_user.UserCreatedCreateUserCommandResult;
import me.loki2302.events.UserCreatedDomainEvent;
import me.loki2302.queries.get_user_profile.GetUserProfileQuery;
import me.loki2302.queries.get_user_profile.GetUserProfileQueryHandler;
import me.loki2302.queries.get_user_profile.GetUserProfileQueryResult;
import me.loki2302.queries.get_user_profile.SuccessfulGetUserProfileQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class CreateUserApiRequestHandler implements ApiRequestHandler {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private CreateUserCommandHandler createUserCommandHandler;

    @Autowired
    private GetUserProfileQueryHandler getUserProfileQueryHandler;

    public CreateUserApiResponse createUser(CreateUserApiRequest request) {
        CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.name = request.name;
        CreateUserCommandResult createUserCommandResult = createUserCommandHandler.createUser(createUserCommand);
        if(!(createUserCommandResult instanceof UserCreatedCreateUserCommandResult)) {
            return new FailedToCreateUserCreateUserApiResponse();
        }

        UserCreatedCreateUserCommandResult userCreatedCreateUserCommandResult =
                (UserCreatedCreateUserCommandResult)createUserCommandResult;

        long userId = userCreatedCreateUserCommandResult.userId;

        UserCreatedDomainEvent userCreatedEvent = new UserCreatedDomainEvent();
        userCreatedEvent.userId = userId; // ...
        userCreatedEvent.name = request.name; // ...
        applicationEventPublisher.publishEvent(userCreatedEvent);

        GetUserProfileQuery getUserProfileQuery = new GetUserProfileQuery();
        getUserProfileQuery.userId = userId;
        GetUserProfileQueryResult getUserProfileQueryResult = getUserProfileQueryHandler.getUserProfile(getUserProfileQuery);
        if(!(getUserProfileQueryResult instanceof SuccessfulGetUserProfileQueryResult)) {
            // TODO: should it be internal error instead?
            return new FailedToCreateUserCreateUserApiResponse();
        }

        SuccessfulGetUserProfileQueryResult successfulGetUserProfileQueryResult =
                (SuccessfulGetUserProfileQueryResult)getUserProfileQueryResult;

        UserCreatedCreateUserApiResponse userCreatedCreateUserApiResponse = new UserCreatedCreateUserApiResponse();
        userCreatedCreateUserApiResponse.userId = successfulGetUserProfileQueryResult.userId;
        userCreatedCreateUserApiResponse.name = successfulGetUserProfileQueryResult.name;
        userCreatedCreateUserApiResponse.postCount = successfulGetUserProfileQueryResult.postCount;

        return userCreatedCreateUserApiResponse;
    }
}
