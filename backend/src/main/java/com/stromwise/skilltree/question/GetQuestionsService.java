package com.stromwise.skilltree.question;

import com.stromwise.skilltree.configuration.QuestionsGeneratorProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class GetQuestionsService {

    private final RestTemplate restTemplate;
    private final QuestionsGeneratorProperties questionsGeneratorProperties;

    GetAnswersPayload getAnswers(String category, List<String> questions) {
        log.info("Searching for questions: {}", questions);

        String questionsJoined = String.join("|", questions);

        URI targetUrl = UriComponentsBuilder.fromUriString(questionsGeneratorProperties.getAnswersUrl())
                .queryParam("category", category)
                .queryParam("questions", questionsJoined)
                .build()
                .encode()
                .toUri();


        var getAnswersPayload =
                restTemplate.getForObject(targetUrl, GetAnswersPayload.class);


        return getAnswersPayload;
    }
}
