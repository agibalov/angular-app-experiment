package me.loki2302.be.be.writemodel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowshipEntityRepository extends JpaRepository<FollowshipEntity, FollowshipEntityId> {
    List<FollowshipEntity> findByFollowerId(long followerId);
    List<FollowshipEntity> findByLeaderId(long leaderId);
}
