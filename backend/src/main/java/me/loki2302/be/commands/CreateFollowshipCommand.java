package me.loki2302.be.commands;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CreateFollowshipCommand {
    public final long followerId;
    public final long leaderId;
}
