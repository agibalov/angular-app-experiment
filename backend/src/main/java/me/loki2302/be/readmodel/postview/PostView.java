package me.loki2302.be.readmodel.postview;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
public class PostView {
    @Id
    public Long id;
    public String text;
    public long commentCount;
    public long userId;
}
