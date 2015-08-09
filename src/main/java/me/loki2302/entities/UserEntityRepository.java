package me.loki2302.entities;

import me.loki2302.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);
}
