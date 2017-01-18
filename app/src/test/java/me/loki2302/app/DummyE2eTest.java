package me.loki2302.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        App.class,
        WebDriverConfiguration.class
}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DummyE2eTest {
    private final Logger LOGGER = LoggerFactory.getLogger(DummyE2eTest.class);

    @Autowired
    private WebDriver webDriver;

    @Test
    public void titleShouldSayHelloWorld() throws InterruptedException {
        webDriver.get("http://localhost:8080/");
        assertEquals("Hello World", webDriver.getTitle());
    }
}
