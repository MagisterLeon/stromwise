package com.stromwise.skilltree.question;

import com.stromwise.skilltree.question.utils.QuestionConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetQuestionService {

    private final QuestionConverter questionConverter;
    private final QuestionRepository questionRepository;

    @Value("${questions.result.limit}")
    private String questionsResultLimit;

    @Transactional
    Set<QuestionPayload> getQuestionByCategory(String categoryName) {
        log.info("Searching questions by category name: {}", categoryName);

        Set<Question> questionSet = questionRepository.findRandomByCategoryName(categoryName, questionsResultLimit);

        return questionConverter.transform(questionSet);
    }
}
