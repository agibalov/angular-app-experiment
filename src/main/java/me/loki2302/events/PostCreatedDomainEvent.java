package me.loki2302.events;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("postCreated")
public class PostCreatedDomainEvent extends DomainEvent {
    public long userId;
    public long postId;
    public String title;
    public String text;
}
