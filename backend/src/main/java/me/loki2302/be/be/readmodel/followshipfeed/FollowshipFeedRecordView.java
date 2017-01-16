package me.loki2302.be.be.readmodel.followshipfeed;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
public class FollowshipFeedRecordView {
    @Id
    @GeneratedValue
    public Long id;
    public long userId;
    public String text;
}
