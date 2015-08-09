package me.loki2302.api;

import me.loki2302.events.DomainEvent;

import java.util.List;

public class GetDomainEventsApiResponse implements ApiResponse {
    public List<DomainEvent> events;
}
