package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetQuestionResponseRateService {

    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    @Transactional
    List<QuestionResponseRatePayload> getQuestionsResponsesRates(List<String> publicId) {
        log.info("Searching questions by publicId: {}", publicId);

        List<Question> questionList = questionRepository.findByPublicIdIn(publicId);

        return questionConverter.transformQuestionsResponsesRates(questionList);
    }
}
