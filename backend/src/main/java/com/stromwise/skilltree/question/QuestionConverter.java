package com.stromwise.skilltree.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QuestionConverter {

    public List<QuestionPayload> transform(List<Question> questionEntity) {
        log.info("Convert Question object from DB to payload: {}", questionEntity);
        return questionEntity
                .stream()
                .map(questions -> QuestionPayload
                        .builder()
                        .publicId(questions.getPublicId())
                        .answer(questions.getAnswer())
                        .question(questions.getQuestion())
                        .build())
                .collect(Collectors.toList());
    }
}
