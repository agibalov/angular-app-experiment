package me.loki2302.events;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CommentCreatedEvent {
    public final long userId;
    public final long postId;
    public final long commentId;
    public final String text;
}
