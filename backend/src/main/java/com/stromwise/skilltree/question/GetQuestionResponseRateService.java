package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class GetQuestionResponseRateService {

    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    List<QuestionResponseRatePayload> getQuestionsResponsesRates(List<String> publicIds) {
        log.info("Searching questions by publicIds: {}", publicIds);

        List<Question> questions = questionRepository.findByPublicIdIn(publicIds);

        return questionConverter.transformQuestionResponseRates(questions);
    }
}
