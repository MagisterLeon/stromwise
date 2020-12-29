package com.stromwise.skilltree.question;

import com.stromwise.skilltree.configuration.QuestionsGeneratorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetRandomQuestionsService {

    private final QuestionConverter questionConverter;
    private final RestTemplate restTemplate;
    private final QuestionsGeneratorProperties questionsGeneratorProperties;


    @Transactional
    List<QuestionPayload> getRandomByCategory(String categoryName) {
        log.info("Searching questions by category name: {}", categoryName);

        GetQuestionsPayload getQuestionsPayload = restTemplate
                .getForObject(questionsGeneratorProperties.getQuestionsUrl() + categoryName, GetQuestionsPayload.class);

        return questionConverter.transformQuestions(getQuestionsPayload.getQuestions());
    }
}
