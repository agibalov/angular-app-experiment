package me.loki2302.api.create_post;

public class PostCreatedCreatePostApiResponse implements CreatePostApiResponse {
    public long postId;
    public long userId;
    public String title;
    public String text;
}
