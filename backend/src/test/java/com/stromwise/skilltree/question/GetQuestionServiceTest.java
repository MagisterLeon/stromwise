package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class GetQuestionServiceTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private GetQuestionService getQuestionService;

    @AfterEach
    public void tearDown() {
        reset(questionRepository);
    }

    @Test
    public void should_get_questions_belong_to_specific_category() {
        // given
        Set<Question> questionSet = new HashSet<>(Arrays.asList(new Question(), new Question(), new Question(), new Question(), new Question()));
        when(questionRepository.findRandomQuestionsBelongToSpecificCategory("programming")).thenReturn(questionSet);
        assertThat(questionRepository.findRandomQuestionsBelongToSpecificCategory("programming").size()).isEqualTo(questionSet.size());

        // when
        Set<Question> foundQuestionsByCategoryName = getQuestionService.getQuestionsBelongToSpecificCategory("programming");

        // then
        assertThat(foundQuestionsByCategoryName.size()).isEqualTo(questionSet.size());
        verify(questionRepository, times(2)).findRandomQuestionsBelongToSpecificCategory("programming");
    }
}
