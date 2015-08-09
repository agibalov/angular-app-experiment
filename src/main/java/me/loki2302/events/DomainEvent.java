package me.loki2302.events;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class DomainEvent {
    @Id
    @GeneratedValue
    public Long id;
}
