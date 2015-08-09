package me.loki2302.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GlobalStatsView {
    public final static long SingletonId = 1;

    @Id
    public Long id;
    public int userCount;
    public int postCount;
}
