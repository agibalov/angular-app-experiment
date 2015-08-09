package me.loki2302.queries.get_domain_events;

import me.loki2302.events.DomainEvent;
import me.loki2302.queries.QueryResult;

import java.util.List;

public class GetDomainEventsQueryResult implements QueryResult {
    public List<DomainEvent> events;
}
