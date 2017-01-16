package me.loki2302.be.be;

import me.loki2302.be.be.controllers.DummyApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackendConfig {
    @Bean
    public DummyApiController dummyApiController() {
        return new DummyApiController();
    }
}
