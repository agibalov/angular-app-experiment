package me.loki2302.queries.get_post;

import me.loki2302.queries.QueryResult;

public class GetPostQueryResult implements QueryResult {
    public long postId;
    public long userId;
    public String title;
    public String text;
}
