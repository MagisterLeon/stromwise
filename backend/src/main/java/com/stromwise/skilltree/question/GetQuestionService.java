package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetQuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    Set<Question> getQuestionsBelongToSpecificCategory(String categoriesName) {
        log.info("Searching questions by category name: {}", categoriesName);

        return questionRepository.findRandomQuestionsBelongToSpecificCategory(categoriesName);
    }
}
