package me.loki2302.events;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("userCreated")
public class UserCreatedDomainEvent extends DomainEvent {
    public long userId;
    public String name;
}
