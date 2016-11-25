package me.loki2302.commands;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class CreateCommentCommand {
    public final long userId;
    public final long postId;
    public final String text;
}
