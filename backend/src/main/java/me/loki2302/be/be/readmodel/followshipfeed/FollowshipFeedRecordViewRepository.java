package me.loki2302.be.be.readmodel.followshipfeed;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowshipFeedRecordViewRepository extends JpaRepository<FollowshipFeedRecordView, Long> {
    List<FollowshipFeedRecordView> findByUserId(long followerId);
}
