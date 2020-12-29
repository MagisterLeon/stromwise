package com.stromwise.skilltree.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StromwiseConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
