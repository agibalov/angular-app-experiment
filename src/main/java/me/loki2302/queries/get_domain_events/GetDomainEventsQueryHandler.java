package me.loki2302.queries.get_domain_events;

import me.loki2302.events.DomainEvent;
import me.loki2302.events.DomainEventRepository;
import me.loki2302.queries.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDomainEventsQueryHandler implements QueryHandler {
    @Autowired
    private DomainEventRepository domainEventRepository;

    @EventListener
    public void persistDomainEvent(DomainEvent domainEvent) {
        domainEventRepository.save(domainEvent);
    }

    public GetDomainEventsQueryResult getDomainEvents(GetDomainEventsQuery getDomainEventsQuery) {
        List<DomainEvent> domainEvents = domainEventRepository.findAll();

        GetDomainEventsQueryResult getDomainEventsQueryResult = new GetDomainEventsQueryResult();
        getDomainEventsQueryResult.events = domainEvents;
        return getDomainEventsQueryResult;
    }
}
