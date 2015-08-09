package me.loki2302.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostView {
    @Id
    public Long id;
    public String title;
    public String text;
    public Long userId;
    // TODO: author name
    // TODO: author post count
}
