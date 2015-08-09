package me.loki2302.api.get_domain_events;

import me.loki2302.api.ApiRequestHandler;
import me.loki2302.queries.get_domain_events.GetDomainEventsQuery;
import me.loki2302.queries.get_domain_events.GetDomainEventsQueryHandler;
import me.loki2302.queries.get_domain_events.GetDomainEventsQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDomainEventsApiRequestHandler implements ApiRequestHandler {
    @Autowired
    private GetDomainEventsQueryHandler getDomainEventsQueryHandler;

    public GetDomainEventsApiResponse getDomainEvents(GetDomainEventsApiRequest getDomainEventsApiRequest) {
        GetDomainEventsQuery getDomainEventsQuery = new GetDomainEventsQuery();
        GetDomainEventsQueryResult getDomainEventsQueryResult = getDomainEventsQueryHandler.getDomainEvents(getDomainEventsQuery);

        GetDomainEventsApiResponse getDomainEventsApiResponse = new GetDomainEventsApiResponse();
        getDomainEventsApiResponse.events = getDomainEventsQueryResult.events;
        return getDomainEventsApiResponse;
    }
}
