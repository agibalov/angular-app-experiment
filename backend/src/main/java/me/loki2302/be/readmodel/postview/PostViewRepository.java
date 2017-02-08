package me.loki2302.be.readmodel.postview;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostViewRepository extends JpaRepository<PostView, Long> {
    List<PostView> findByUserId(long userId);
}
