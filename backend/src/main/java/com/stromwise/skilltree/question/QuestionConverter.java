package com.stromwise.skilltree.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class QuestionConverter {

    public List<QuestionPayload> transformQuestions(List<Question> questionEntity) {
        log.info("Convert Question objects from DB to payloads: {}", questionEntity);
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

    public List<QuestionResponseRatePayload> transformQuestionsResponsesRates(List<QuestionResponseRate> questionsResponseRatesEntity) {
        log.info("Convert Question Responses Rates objects from DB to payloads: {}", questionsResponseRatesEntity);
        return questionsResponseRatesEntity
                .stream()
                .map(questionsResponse -> QuestionResponseRatePayload
                        .builder()
                        .know(questionsResponse.getKnow())
                        .notSure(questionsResponse.getNotSure())
                        .notKnow(questionsResponse.getNotKnow())
                        .build())
                .collect(Collectors.toList());
    }
}
