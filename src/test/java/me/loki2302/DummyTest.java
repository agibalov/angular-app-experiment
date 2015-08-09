package me.loki2302;

import me.loki2302.api.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DummyConfig.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DummyTest {
    @Autowired
    private ApiFacade apiFacade;

    @Test
    public void thereAreNoUsersByDefault() {
        GetGlobalStatsApiRequest getGlobalStatsApiRequest = new GetGlobalStatsApiRequest();
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = apiFacade.getGlobalStats(getGlobalStatsApiRequest);
        assertEquals(0, getGlobalStatsApiResponse.userCount);
    }

    @Test
    public void thereAreNoEventsByDefault() {
        GetDomainEventsApiRequest getDomainEventsApiRequest = new GetDomainEventsApiRequest();
        GetDomainEventsApiResponse getDomainEventsApiResponse = apiFacade.getDomainEvents(getDomainEventsApiRequest);
        assertTrue(getDomainEventsApiResponse.events.isEmpty());
    }

    @Test
    public void itIsPossibleToCreateAUser() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
        assertEquals(1, createUserApiResponse.userId);
        assertEquals("loki2302", createUserApiResponse.name);
        assertEquals(0, createUserApiResponse.postCount);
    }

    @Test
    public void whenUserIsCreatedGlobalStatsAreUpdated() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        apiFacade.createUser(createUserApiRequest);
        GetGlobalStatsApiRequest getGlobalStatsApiRequest = new GetGlobalStatsApiRequest();
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = apiFacade.getGlobalStats(getGlobalStatsApiRequest);
        assertEquals(1, getGlobalStatsApiResponse.userCount);
    }

    @Test
    public void whenUserIsCreatedEventsAreUpdated() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        apiFacade.createUser(createUserApiRequest);
        GetDomainEventsApiRequest getDomainEventsApiRequest = new GetDomainEventsApiRequest();
        GetDomainEventsApiResponse getDomainEventsApiResponse = apiFacade.getDomainEvents(getDomainEventsApiRequest);
        assertEquals(1, getDomainEventsApiResponse.events.size());
    }
}
