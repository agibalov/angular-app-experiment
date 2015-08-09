package me.loki2302;

import me.loki2302.api.*;
import me.loki2302.api.create_post.CreatePostApiRequest;
import me.loki2302.api.create_post.CreatePostApiResponse;
import me.loki2302.api.create_post.PostCreatedCreatePostApiResponse;
import me.loki2302.api.create_user.CreateUserApiRequest;
import me.loki2302.api.create_user.CreateUserApiResponse;
import me.loki2302.api.create_user.FailedToCreateUserCreateUserApiResponse;
import me.loki2302.api.create_user.UserCreatedCreateUserApiResponse;
import me.loki2302.api.get_domain_events.GetDomainEventsApiRequest;
import me.loki2302.api.get_domain_events.GetDomainEventsApiResponse;
import me.loki2302.api.get_global_stats.GetGlobalStatsApiRequest;
import me.loki2302.api.get_global_stats.GetGlobalStatsApiResponse;
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
    public void globalStatsAreEmptyByDefault() {
        GetGlobalStatsApiRequest getGlobalStatsApiRequest = new GetGlobalStatsApiRequest();
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = apiFacade.getGlobalStats(getGlobalStatsApiRequest);
        assertEquals(0, getGlobalStatsApiResponse.userCount);
        assertEquals(0, getGlobalStatsApiResponse.postCount);
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
        assertTrue(createUserApiResponse instanceof UserCreatedCreateUserApiResponse);
        UserCreatedCreateUserApiResponse userCreatedCreateUserApiResponse =
                (UserCreatedCreateUserApiResponse)createUserApiResponse;
        assertEquals(1, userCreatedCreateUserApiResponse.userId);
        assertEquals("loki2302", userCreatedCreateUserApiResponse.name);
        assertEquals(0, userCreatedCreateUserApiResponse.postCount);
    }

    @Test
    public void itIsNotPossibleToCreateAUserWhenNameIsNotValid() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "";
        CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
        assertTrue(createUserApiResponse instanceof FailedToCreateUserCreateUserApiResponse);
    }

    @Test
    public void itIsNotPossibleToCreateAUserWhenItAlreadyExists() {
        if(true) {
            CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
            createUserApiRequest.name = "loki2302";
            apiFacade.createUser(createUserApiRequest);
        }

        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
        assertTrue(createUserApiResponse instanceof FailedToCreateUserCreateUserApiResponse);
    }

    @Test
    public void whenUserIsCreatedGlobalStatsAreUpdated() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        apiFacade.createUser(createUserApiRequest);
        GetGlobalStatsApiRequest getGlobalStatsApiRequest = new GetGlobalStatsApiRequest();
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = apiFacade.getGlobalStats(getGlobalStatsApiRequest);
        assertEquals(1, getGlobalStatsApiResponse.userCount);
        assertEquals(0, getGlobalStatsApiResponse.postCount);
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

    @Test
    public void itIsPossibleToCreateAPost() {
        CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
        createUserApiRequest.name = "loki2302";
        CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
        assertTrue(createUserApiResponse instanceof UserCreatedCreateUserApiResponse);
        UserCreatedCreateUserApiResponse userCreatedCreateUserApiResponse =
                (UserCreatedCreateUserApiResponse)createUserApiResponse;

        long userId = userCreatedCreateUserApiResponse.userId;

        CreatePostApiRequest createPostApiRequest = new CreatePostApiRequest();
        createPostApiRequest.userId = userId;
        createPostApiRequest.title = "hello";
        createPostApiRequest.text = "world";
        CreatePostApiResponse createPostApiResponse = apiFacade.createPost(createPostApiRequest);
        assertTrue(createPostApiResponse instanceof PostCreatedCreatePostApiResponse);
        PostCreatedCreatePostApiResponse postCreatedCreatePostApiResponse =
                (PostCreatedCreatePostApiResponse)createPostApiResponse;

        assertEquals(1, postCreatedCreatePostApiResponse.postId);
        assertEquals(userId, postCreatedCreatePostApiResponse.userId);
        assertEquals("hello", postCreatedCreatePostApiResponse.title);
        assertEquals("world", postCreatedCreatePostApiResponse.text);
    }

    @Test
    public void whenPostIsCreatedGlobalStatsAreUpdated() {
        if(true) {
            CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
            createUserApiRequest.name = "loki2302";
            CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
            assertTrue(createUserApiResponse instanceof UserCreatedCreateUserApiResponse);
            UserCreatedCreateUserApiResponse userCreatedCreateUserApiResponse =
                    (UserCreatedCreateUserApiResponse)createUserApiResponse;

            long userId = userCreatedCreateUserApiResponse.userId;

            CreatePostApiRequest createPostApiRequest = new CreatePostApiRequest();
            createPostApiRequest.userId = userId;
            createPostApiRequest.title = "hello";
            createPostApiRequest.text = "world";
            apiFacade.createPost(createPostApiRequest);
        }

        GetGlobalStatsApiRequest getGlobalStatsApiRequest = new GetGlobalStatsApiRequest();
        GetGlobalStatsApiResponse getGlobalStatsApiResponse = apiFacade.getGlobalStats(getGlobalStatsApiRequest);
        assertEquals(1, getGlobalStatsApiResponse.userCount);
        assertEquals(1, getGlobalStatsApiResponse.postCount);
    }

    @Test
    public void whenPostIsCreatedEventsAreUpdated() {
        if(true) {
            CreateUserApiRequest createUserApiRequest = new CreateUserApiRequest();
            createUserApiRequest.name = "loki2302";
            CreateUserApiResponse createUserApiResponse = apiFacade.createUser(createUserApiRequest);
            assertTrue(createUserApiResponse instanceof UserCreatedCreateUserApiResponse);
            UserCreatedCreateUserApiResponse userCreatedCreateUserApiResponse =
                    (UserCreatedCreateUserApiResponse)createUserApiResponse;

            long userId = userCreatedCreateUserApiResponse.userId;

            CreatePostApiRequest createPostApiRequest = new CreatePostApiRequest();
            createPostApiRequest.userId = userId;
            createPostApiRequest.title = "hello";
            createPostApiRequest.text = "world";
            apiFacade.createPost(createPostApiRequest);
        }

        GetDomainEventsApiRequest getDomainEventsApiRequest = new GetDomainEventsApiRequest();
        GetDomainEventsApiResponse getDomainEventsApiResponse = apiFacade.getDomainEvents(getDomainEventsApiRequest);
        assertEquals(2, getDomainEventsApiResponse.events.size());
    }

    @Test
    public void whenPostIsCreatedUserProfileIsUpdated() {
        // TODO: add GetUserProfile API
    }
}
