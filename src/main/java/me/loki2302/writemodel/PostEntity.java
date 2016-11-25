package me.loki2302.writemodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PostEntity {
    @Id
    @GeneratedValue
    public Long id;
    public String text;

    @ManyToOne
    public UserEntity user;
}
