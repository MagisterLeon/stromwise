package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashSet;
import java.util.Set;

import static com.stromwise.skilltree.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.utils.TestDataFactory.prepareQuestions;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetQuestionServiceTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;

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
        when(questionRepository.findRandomQuestionsBelongToSpecificCategory("programming", questionsResultLimit)).thenReturn(questionSet);
        assertThat(questionRepository.findRandomQuestionsBelongToSpecificCategory("programming", questionsResultLimit).size()).isEqualTo(questionSet.size());

        // when
        Set<Question> foundQuestionsByCategoryName = getQuestionService.getQuestionsBelongToSpecificCategory("programming");

        // then
        assertThat(foundQuestionsByCategoryName.size()).isEqualTo(5);
        verify(questionRepository, times(2)).findRandomQuestionsBelongToSpecificCategory("programming", questionsResultLimit);
    }
}
