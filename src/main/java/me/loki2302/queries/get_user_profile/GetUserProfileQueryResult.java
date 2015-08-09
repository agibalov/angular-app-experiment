package me.loki2302.queries.get_user_profile;

import me.loki2302.queries.QueryResult;

public class GetUserProfileQueryResult implements QueryResult {
    public long userId;
    public String name;
    public int postCount;
}
