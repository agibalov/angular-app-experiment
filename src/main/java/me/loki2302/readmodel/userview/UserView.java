package me.loki2302.readmodel.userview;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
public class UserView {
    @Id
    public Long userId;
    public String name;
    public long postCount;
    public long commentCount;
    public long followerCount;
    public long followsCount;
}
