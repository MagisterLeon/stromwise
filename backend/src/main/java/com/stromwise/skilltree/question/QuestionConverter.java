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

    public List<QuestionResponseRatePayload> transformQuestionsResponsesRates(List<Question> questionList) {
        log.info("Convert Question Responses Rates objects from DB to payloads: {}", questionList);
        return questionList
                .stream()
                .map(question -> QuestionResponseRatePayload
                        .builder()
                        .know(question.getKnow())
                        .notSure(question.getNotSure())
                        .notKnow(question.getNotKnow())
                        .build())
                .collect(Collectors.toList());
    }
}
