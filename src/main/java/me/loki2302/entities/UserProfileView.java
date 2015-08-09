package me.loki2302.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserProfileView {
    @Id
    public Long id;
    public String name;
    public int postCount;

}
