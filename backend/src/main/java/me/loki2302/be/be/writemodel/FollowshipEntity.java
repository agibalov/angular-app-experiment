package me.loki2302.be.be.writemodel;

import javax.persistence.*;

@Entity
@IdClass(FollowshipEntityId.class)
public class FollowshipEntity {
    @Id
    public Long followerId;

    @Id
    public Long leaderId;

    @ManyToOne
    @JoinColumn(name = "followerId", updatable = false, insertable = false)
    public UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "leaderId", updatable = false, insertable = false)
    public UserEntity leader;
}
