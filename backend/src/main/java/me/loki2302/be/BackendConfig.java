package me.loki2302.be;

import me.loki2302.be.controllers.DummyApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SecurityConfig.class)
public class BackendConfig {
    @Bean
    public DummyApiController dummyApiController() {
        return new DummyApiController();
    }
}
