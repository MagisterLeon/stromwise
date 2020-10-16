package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.utils.QuestionConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

import static com.stromwise.skilltree.utils.TestDataFactory.*;
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

        Set<Question> questionSet = new HashSet<>(prepareQuestions(questionSize, prepareCategories(2)));
        Set<QuestionPayload> questionPayloadSet = new HashSet<>(prepareQuestionsPayload(questionSize));

        when(questionRepository.findRandomByCategoryName("programming", questionsResultLimit)).thenReturn(questionSet);
        when(questionConverter.transform(questionSet)).thenReturn(questionPayloadSet);

        // when
        Set<QuestionPayload> foundQuestionsByCategoryName = getQuestionService.getQuestionByCategory("programming");

        // then
        assertThat(foundQuestionsByCategoryName.size()).isEqualTo(5);
        verify(questionRepository, times(1)).findRandomByCategoryName("programming", questionsResultLimit);
        verify(questionConverter, times(1)).transform(questionSet);
    }
}
