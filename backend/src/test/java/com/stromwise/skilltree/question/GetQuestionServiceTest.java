package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.stromwise.skilltree.question.utils.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetQuestionServiceTest extends UnitTest {

    private final QuestionRepository questionRepository = mock(QuestionRepository.class);

    private final QuestionConverter questionConverter = new QuestionConverter();

    private final GetQuestionService getQuestionService =
            new GetQuestionService(questionConverter, questionRepository);

    private final GetQuestionResponseRateService questionResponseRateService =
            new GetQuestionResponseRateService(questionConverter, questionRepository);

    @Value("${questions.result.limit}")
    private int questionsResultLimit;

    @AfterEach
    public void tearDown() {
        reset(questionRepository);
    }

    @Test
    public void should_get_questions_belong_to_specific_category() {
        // given
        int questionSize = 5;

        List<Question> questions = prepareQuestions(questionSize, prepareCategories(2));

        when(questionRepository.findRandomByCategoryName("programming", questionsResultLimit))
                .thenReturn(questions);

        // when
        List<QuestionPayload> foundQuestionsByCategoryName = getQuestionService
                .getQuestionByCategory("programming");

        // then
        assertThat(foundQuestionsByCategoryName.size()).isEqualTo(5);
        verify(questionRepository).findRandomByCategoryName("programming", questionsResultLimit);
    }

    @Test
    public void should_get_responses_rates_by_publicId() {
        // given
        int questionSize = 5;

        List<Question> questions = prepareQuestions(questionSize, prepareCategories(2));
        List<String> publicIds = questions.stream().map(Question::getPublicId).collect(Collectors.toList());

        when(questionRepository.findByPublicIdIn(publicIds)).thenReturn(questions);

        // when
        List<QuestionResponseRatePayload> foundResponsesRates = questionResponseRateService.getQuestionsResponsesRates(publicIds);

        // then
        assertThat(foundResponsesRates.size()).isEqualTo(5);
        verify(questionRepository).findByPublicIdIn(publicIds);
    }
}
