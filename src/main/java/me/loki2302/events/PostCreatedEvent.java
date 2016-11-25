package me.loki2302.events;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class PostCreatedEvent {
    public final long userId;
    public final long postId;
    public final String text;
}
