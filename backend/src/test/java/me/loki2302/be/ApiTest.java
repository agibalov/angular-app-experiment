package me.loki2302.be;

import me.loki2302.be.be.App;
import me.loki2302.be.be.controllers.UserAttributesDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = App.class
)
@RunWith(SpringRunner.class)
public class ApiTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void canCreateUser() {
        RestTemplate restTemplate = new RestTemplate();

        UserAttributesDto userAttributesDto = new UserAttributesDto();
        userAttributesDto.username = "loki2302";
        ResponseEntity responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/users",
                userAttributesDto,
                Void.class);

        assertNotNull(responseEntity.getHeaders().getLocation());
    }
}
