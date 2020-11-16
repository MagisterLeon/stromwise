package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
class UpdateQuestionService {

    private final QuestionRepository questionRepository;
    private final KnownQuestionsUpdater knownQuestionsUpdater;

    void updateWeightsAndRates(UpdateQuestionWeightAndRatesRequest request) {
        log.info("Updating question weights and rates");

        List<Question> updatedQuestions = Stream.of(
                updateKnownQuestions(request.getKnownQuestionPublicIds()),
                updateNotSureQuestions(request.getNotSureQuestionPublicIds()),
                updateNotKnowQuestions(request.getNotKnowQuestionPublicIds())
        ).flatMap(Collection::stream)
                .collect(Collectors.toList());

        questionRepository.saveAll(updatedQuestions);
    }

    private List<Question> updateKnownQuestions(List<String> publicIds) {
        return knownQuestionsUpdater.update(publicIds);
    }

    private List<Question> updateNotKnowQuestions(List<String> publicIds) {
        List<Question> questions = questionRepository.findByPublicIdIn(publicIds);
        questions.forEach(question -> {
            question.setNotKnow(question.getNotKnow() + 1);
        });
        return questions;
    }

    private List<Question> updateNotSureQuestions(List<String> publicIds) {
        List<Question> questions = questionRepository.findByPublicIdIn(publicIds);
        questions.forEach(question -> {
            question.setNotSure(question.getNotSure() + 1);
        });
        return questions;
    }
}
