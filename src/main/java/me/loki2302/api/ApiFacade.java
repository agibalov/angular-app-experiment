package me.loki2302.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiFacade {
    @Autowired
    private CreateUserApiRequestHandler createUserApiRequestHandler;

    @Autowired
    private GetGlobalStatsApiRequestHandler getGlobalStatsApiRequestHandler;

    @Autowired
    private GetDomainEventsApiRequestHandler getDomainEventsApiRequestHandler;

    public CreateUserApiResponse createUser(CreateUserApiRequest request) {
        return createUserApiRequestHandler.createUser(request);
    }

    public GetGlobalStatsApiResponse getGlobalStats(GetGlobalStatsApiRequest request) {
        return getGlobalStatsApiRequestHandler.getGlobalStats(request);
    }

    public GetDomainEventsApiResponse getDomainEvents(GetDomainEventsApiRequest request) {
        return getDomainEventsApiRequestHandler.getDomainEvents(request);
    }
}
