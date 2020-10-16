package com.stromwise.skilltree.utils;

import com.stromwise.skilltree.question.Question;
import com.stromwise.skilltree.question.QuestionPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QuestionConverter {

    public Set<QuestionPayload> transform(Set<Question> questionEntity) {
        log.info("Convert Question object from DB to payload: {}", questionEntity);
        return questionEntity
                .stream()
                .map(questionPojo -> new QuestionPayload(questionPojo.getPublicId(), questionPojo.getAnswer(), questionPojo.getQuestion()))
                .collect(Collectors.toSet());
    }
}
