package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UpdateQuestionServiceTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private CategoryRepository categoryRepository;

    private UpdateQuestionService uut;

    @BeforeEach
    public void setUp() {
        uut = new UpdateQuestionService(questionRepository, categoryRepository);
    }

    @Test
    public void should_update_responses() {
        // given
        Question question1 = new Question();
        question1.setQuestion("question1");
        Question question2 = new Question();
        question2.setQuestion("question2");
        question2.setKnow(2);
        Question question3 = new Question();
        question3.setQuestion("question3");
        Question question4 = new Question();
        question4.setQuestion("question4");
        question4.setNotSure(1);
        Question question5 = new Question();
        question5.setQuestion("question5");

        var request = new UpdateQuestionResponses("programming",
                List.of("question1", "question2"), List.of("question3", "question4"), List.of("question5"));
        when(questionRepository.findByQuestionInIgnoreCase(List.of("question1", "question2")))
                .thenReturn(List.of(question1, question2));
        when(questionRepository.findByQuestionInIgnoreCase(List.of("question3", "question4")))
                .thenReturn(List.of(question3, question4));
        when(questionRepository.findByQuestionInIgnoreCase(List.of("question5")))
                .thenReturn(List.of(question5));

        // when
        uut.updateResponses(request);

        // then
        assertThat(question1.getKnow()).isEqualTo(1);
        assertThat(question2.getKnow()).isEqualTo(3);
        assertThat(question3.getNotSure()).isEqualTo(1);
        assertThat(question4.getNotSure()).isEqualTo(2);
        assertThat(question5.getNotKnow()).isEqualTo(1);
    }
}