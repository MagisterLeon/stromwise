package com.stromwise.skilltree.question;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
class QuestionConverter {

    List<QuestionPayload> transformQuestions(List<Question> questions) {
        log.info("Convert Question objects from DB to QuestionPayloads: {}", questions);
        return questions
                .stream()
                .map(question -> QuestionPayload
                        .builder()
                        .publicId(question.getPublicId())
                        .answer(question.getAnswer())
                        .question(question.getQuestion())
                        .build())
                .collect(Collectors.toList());
    }

    List<QuestionWithRatesPayload> transformQuestionsWithResponseRates(List<Question> questions) {
        log.info("Convert Question objects from DB to ResponseRatePayloads: {}", questions);
        return questions
                .stream()
                .map(question -> QuestionWithRatesPayload
                        .builder()
                        .publicId(question.getPublicId())
                        .answer(question.getAnswer())
                        .question(question.getQuestion())
                        .know(question.getKnow())
                        .notSure(question.getNotSure())
                        .notKnow(question.getNotKnow())
                        .build())
                .collect(Collectors.toList());
    }
}
