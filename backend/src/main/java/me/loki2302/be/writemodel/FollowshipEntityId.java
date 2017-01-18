package me.loki2302.be.writemodel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class FollowshipEntityId implements Serializable {
    public long followerId;
    public long leaderId;
}
