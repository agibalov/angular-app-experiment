package me.loki2302.api.create_post;

import me.loki2302.api.ApiRequest;

public class CreatePostApiRequest implements ApiRequest {
    public long userId;
    public String title;
    public String text;
}
