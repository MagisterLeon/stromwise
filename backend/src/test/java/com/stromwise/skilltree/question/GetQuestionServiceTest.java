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

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionConverter questionConverter;

    @InjectMocks
    private GetQuestionService getQuestionService;
    @InjectMocks
    private GetQuestionResponseRateService questionResponseRateService;

    @Value("${questions.result.limit}")
    private String questionsResultLimit;

    @AfterEach
    public void tearDown() {
        reset(questionRepository);
    }

    @Test
    public void should_get_questions_belong_to_specific_category() {
        // given
        int questionSize = 5;

        List<Question> questionSet = new ArrayList<>(prepareQuestions(questionSize, prepareCategories(2)));
        List<QuestionPayload> questionPayloadSet = new ArrayList<>(prepareQuestionsPayload(questionSize));

        when(questionRepository.findRandomByCategoryName("programming", questionsResultLimit)).thenReturn(questionSet);
        when(questionConverter.transformQuestions(questionSet)).thenReturn(questionPayloadSet);

        // when
        List<QuestionPayload> foundQuestionsByCategoryName = getQuestionService.getQuestionByCategory("programming");

        // then
        assertThat(foundQuestionsByCategoryName.size()).isEqualTo(5);
        verify(questionRepository).findRandomByCategoryName("programming", questionsResultLimit);
        verify(questionConverter).transformQuestions(questionSet);
    }

    @Test
    public void should_get_responses_rates_by_publicId() {
        // given
        int questionSize = 5;

        List<Question> questionList = new ArrayList<>(prepareQuestions(questionSize, prepareCategories(2)));
        List<QuestionResponseRatePayload> questionResponseRatePayloadList = new ArrayList<>(prepareQuestionResponseRatePayload(questionSize));
        List<String> publicIdList = questionList.stream().map(Question::getPublicId).collect(Collectors.toList());

        when(questionRepository.findResponseRatesByPublicId(publicIdList)).thenReturn(questionList);
        when(questionConverter.transformQuestionsResponsesRates(questionList)).thenReturn(questionResponseRatePayloadList);

        // when
        List<QuestionResponseRatePayload> foundResponsesRates = questionResponseRateService.getQuestionsResponsesRates(publicIdList);

        // then
        assertThat(foundResponsesRates.size()).isEqualTo(5);
        verify(questionRepository).findResponseRatesByPublicId(publicIdList);
        verify(questionConverter).transformQuestionsResponsesRates(questionList);
    }
}
