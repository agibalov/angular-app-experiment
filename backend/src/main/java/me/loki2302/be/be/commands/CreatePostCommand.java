package me.loki2302.be.be.commands;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CreatePostCommand {
    public final long userId;
    public final String text;
}
