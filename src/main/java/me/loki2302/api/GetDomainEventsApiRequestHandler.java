package me.loki2302.api;

import me.loki2302.queries.GetDomainEventsQuery;
import me.loki2302.queries.GetDomainEventsQueryHandler;
import me.loki2302.queries.GetDomainEventsQueryResult;
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
