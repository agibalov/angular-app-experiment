package me.loki2302.api;

import me.loki2302.api.create_post.CreatePostApiRequest;
import me.loki2302.api.create_post.CreatePostApiRequestHandler;
import me.loki2302.api.create_post.CreatePostApiResponse;
import me.loki2302.api.create_user.CreateUserApiRequest;
import me.loki2302.api.create_user.CreateUserApiRequestHandler;
import me.loki2302.api.create_user.CreateUserApiResponse;
import me.loki2302.api.get_domain_events.GetDomainEventsApiRequest;
import me.loki2302.api.get_domain_events.GetDomainEventsApiRequestHandler;
import me.loki2302.api.get_domain_events.GetDomainEventsApiResponse;
import me.loki2302.api.get_global_stats.GetGlobalStatsApiRequest;
import me.loki2302.api.get_global_stats.GetGlobalStatsApiRequestHandler;
import me.loki2302.api.get_global_stats.GetGlobalStatsApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiFacade {
    @Autowired
    private CreateUserApiRequestHandler createUserApiRequestHandler;

    @Autowired
    private CreatePostApiRequestHandler createPostApiRequestHandler;

    @Autowired
    private GetGlobalStatsApiRequestHandler getGlobalStatsApiRequestHandler;

    @Autowired
    private GetDomainEventsApiRequestHandler getDomainEventsApiRequestHandler;

    public CreateUserApiResponse createUser(CreateUserApiRequest request) {
        return createUserApiRequestHandler.createUser(request);
    }

    public CreatePostApiResponse createPost(CreatePostApiRequest request) {
        return createPostApiRequestHandler.createPost(request);
    }

    public GetGlobalStatsApiResponse getGlobalStats(GetGlobalStatsApiRequest request) {
        return getGlobalStatsApiRequestHandler.getGlobalStats(request);
    }

    public GetDomainEventsApiResponse getDomainEvents(GetDomainEventsApiRequest request) {
        return getDomainEventsApiRequestHandler.getDomainEvents(request);
    }
}
