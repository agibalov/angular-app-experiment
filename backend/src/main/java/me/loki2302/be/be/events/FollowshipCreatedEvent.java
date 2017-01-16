package me.loki2302.be.be.events;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class FollowshipCreatedEvent {
    public final long followerId;
    public final long leaderId;
}
