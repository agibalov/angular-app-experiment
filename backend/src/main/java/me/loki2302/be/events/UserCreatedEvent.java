package me.loki2302.be.events;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class UserCreatedEvent {
    public final long id;
    public final String name;
}
