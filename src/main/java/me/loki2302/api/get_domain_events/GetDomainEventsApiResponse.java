package me.loki2302.api.get_domain_events;

import me.loki2302.api.ApiResponse;
import me.loki2302.events.DomainEvent;

import java.util.List;

public class GetDomainEventsApiResponse implements ApiResponse {
    public List<DomainEvent> events;
}
