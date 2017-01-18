package me.loki2302.be.commands;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CreateCommentCommand {
    public final long userId;
    public final long postId;
    public final String text;
}
