package me.loki2302.events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainEventRepository extends JpaRepository<DomainEvent, Long> {
}
