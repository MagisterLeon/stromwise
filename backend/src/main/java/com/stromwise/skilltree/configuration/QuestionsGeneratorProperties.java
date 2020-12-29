package com.stromwise.skilltree.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "stromwise.question-generator")
public class QuestionsGeneratorProperties {

    private String questionsUrl;
    private String answersUrl;
}
