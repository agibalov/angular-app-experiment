package me.loki2302.be;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({SecurityConfig.class, WebConfig.class})
public class BackendConfig {
}
