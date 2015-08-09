package me.loki2302.api.get_global_stats;

import me.loki2302.api.ApiRequestHandler;
import me.loki2302.queries.get_global_stats.GetGlobalStatsQuery;
import me.loki2302.queries.get_global_stats.GetGlobalStatsQueryHandler;
import me.loki2302.queries.get_global_stats.GetGlobalStatsQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetGlobalStatsApiRequestHandler implements ApiRequestHandler {
    @Autowired
    private GetGlobalStatsQueryHandler getGlobalStatsQueryHandler;

    public GetGlobalStatsApiResponse getGlobalStats(GetGlobalStatsApiRequest request) {
        GetGlobalStatsQuery getGlobalStatsQuery = new GetGlobalStatsQuery();
        GetGlobalStatsQueryResult getGlobalStatsQueryResult = getGlobalStatsQueryHandler.getGlobalStats(getGlobalStatsQuery);
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = new GetGlobalStatsApiResponse();
        getGlobalStatsApiResponse.userCount = getGlobalStatsQueryResult.userCount;
        getGlobalStatsApiResponse.postCount = getGlobalStatsQueryResult.postCount;
        return getGlobalStatsApiResponse;
    }
}
