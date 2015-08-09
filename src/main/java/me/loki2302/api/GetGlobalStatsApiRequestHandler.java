package me.loki2302.api;

import me.loki2302.queries.GetGlobalStatsQuery;
import me.loki2302.queries.GetGlobalStatsQueryHandler;
import me.loki2302.queries.GetGlobalStatsQueryResult;
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
        return getGlobalStatsApiResponse;
    }
}
