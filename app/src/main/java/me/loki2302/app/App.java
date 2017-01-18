package me.loki2302.app;

import me.loki2302.be.BackendConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;

@SpringBootApplication
@Import(BackendConfig.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public FilterRegistrationBean rewriteFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setName("rewriteFilter");
        filterRegistrationBean.setFilter(new UrlRewriteFilter());
        filterRegistrationBean.addInitParameter("confPath", "urlrewrite.xml");
        return filterRegistrationBean;
    }
}
