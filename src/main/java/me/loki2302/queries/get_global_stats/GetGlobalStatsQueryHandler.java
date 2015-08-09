package me.loki2302.queries.get_global_stats;

import me.loki2302.entities.GlobalStatsView;
import me.loki2302.entities.GlobalStatsViewRepository;
import me.loki2302.events.PostCreatedDomainEvent;
import me.loki2302.events.UserCreatedDomainEvent;
import me.loki2302.queries.QueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class GetGlobalStatsQueryHandler implements QueryHandler {
    private final static Logger log = LoggerFactory.getLogger(GetGlobalStatsQueryHandler.class);

    @Autowired
    private GlobalStatsViewRepository globalStatsViewRepository;

    public GetGlobalStatsQueryResult getGlobalStats(GetGlobalStatsQuery getGlobalStatsQuery) {
        GlobalStatsView globalStatsView = globalStatsViewRepository.findOne(GlobalStatsView.SingletonId);

        GetGlobalStatsQueryResult getGlobalStatsQueryResult = new GetGlobalStatsQueryResult();
        getGlobalStatsQueryResult.userCount = globalStatsView.userCount;
        getGlobalStatsQueryResult.postCount = globalStatsView.postCount;
        return getGlobalStatsQueryResult;
    }

    @EventListener
    public void initializeGlobalStats(ContextRefreshedEvent event) {
        GlobalStatsView globalStatsView = globalStatsViewRepository.findOne(GlobalStatsView.SingletonId);
        if(globalStatsView != null) {
            return;
        }

        globalStatsView = new GlobalStatsView();
        globalStatsView.id = GlobalStatsView.SingletonId;
        globalStatsView.userCount = 0;
        globalStatsView.postCount = 0;
        globalStatsViewRepository.save(globalStatsView);

        log.info("Initialized global stats");
    }

    @EventListener
    public void updateUserCount(UserCreatedDomainEvent event) {
        log.info("Got application event: {}", event);

        GlobalStatsView globalStatsView = globalStatsViewRepository.findOne(GlobalStatsView.SingletonId);
        ++globalStatsView.userCount;
        globalStatsViewRepository.save(globalStatsView);
    }

    @EventListener
    public void updatePostCount(PostCreatedDomainEvent event) {
        log.info("Got application event: {}", event);

        GlobalStatsView globalStatsView = globalStatsViewRepository.findOne(GlobalStatsView.SingletonId);
        ++globalStatsView.postCount;
        globalStatsViewRepository.save(globalStatsView);
    }
}
