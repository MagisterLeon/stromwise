package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

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
        verify(questionConverter).transformQuestions(questionSet);
        verify(questionRepository).findRandomByCategoryName("programming", questionsResultLimit);
    }
}
