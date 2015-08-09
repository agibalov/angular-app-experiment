package me.loki2302.api;

import me.loki2302.commands.CreateUserCommand;
import me.loki2302.commands.CreateUserCommandHandler;
import me.loki2302.commands.CreateUserCommandResult;
import me.loki2302.events.UserCreatedDomainEvent;
import me.loki2302.queries.GetUserProfileQuery;
import me.loki2302.queries.GetUserProfileQueryHandler;
import me.loki2302.queries.GetUserProfileQueryResult;
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

        long userId = createUserCommandResult.userId;

        UserCreatedDomainEvent userCreatedEvent = new UserCreatedDomainEvent();
        userCreatedEvent.userId = userId; // ...
        userCreatedEvent.name = request.name; // ...
        applicationEventPublisher.publishEvent(userCreatedEvent);

        GetUserProfileQuery getUserProfileQuery = new GetUserProfileQuery();
        getUserProfileQuery.userId = userId;
        GetUserProfileQueryResult getUserProfileQueryResult = getUserProfileQueryHandler.getUserProfile(getUserProfileQuery);

        CreateUserApiResponse createUserApiResponse = new CreateUserApiResponse();
        createUserApiResponse.userId = getUserProfileQueryResult.userId;
        createUserApiResponse.name = getUserProfileQueryResult.name;
        createUserApiResponse.postCount = getUserProfileQueryResult.postCount;

        return createUserApiResponse;
    }
}
