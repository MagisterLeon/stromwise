package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetQuestionService {

    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    @Value("${questions.result.limit}")
    private String questionsResultLimit;

    @Transactional
    List<QuestionPayload> getQuestionByCategory(String categoryName) {
        log.info("Searching questions by category name: {}", categoryName);

        List<Question> questionSet = questionRepository.findRandomByCategoryName(categoryName, questionsResultLimit);

        return questionConverter.transform(questionSet);
    }
}
