package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.configuration.SkilltreeProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UpdateQuestionServiceTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SkilltreeProperties skilltreeProperties;

    @InjectMocks
    private UpdateQuestionService uut;

    @BeforeEach
    public void setUp() {
        when(skilltreeProperties.getPointsToDistribute()).thenReturn(1000);
    }

    @Test
    public void should_give_all_points_to_one_question() {
        // given
        Question question = new Question();
        UpdateQuestionWeightsRequest request = new UpdateQuestionWeightsRequest(List.of("id"));
        when(questionRepository.findByPublicId(anyString())).thenReturn(Optional.of(question));

        // when
        uut.updateWeights(request);

        // then
        assertThat(question.getValue()).isEqualTo(1000);
    }

    @Test
    public void should_split_points_between_two_question() {
        // given
        Question question = new Question();
        Question question2 = new Question();
        UpdateQuestionWeightsRequest request = new UpdateQuestionWeightsRequest(List.of("id", "id2"));
        when(questionRepository.findByPublicId("id")).thenReturn(Optional.of(question));
        when(questionRepository.findByPublicId("id2")).thenReturn(Optional.of(question2));

        // when
        uut.updateWeights(request);

        // then
        assertThat(question.getValue()).isEqualTo(333);
        assertThat(question2.getValue()).isEqualTo(666);
    }

    @Test
    public void should_split_points_between_five_question() {
        // given
        Question question = new Question();
        Question question2 = new Question();
        Question question3 = new Question();
        Question question4 = new Question();
        Question question5 = new Question();

        UpdateQuestionWeightsRequest request = new UpdateQuestionWeightsRequest(
                List.of("id", "id2", "id3", "id4", "id5"));
        when(questionRepository.findByPublicId("id")).thenReturn(Optional.of(question));
        when(questionRepository.findByPublicId("id2")).thenReturn(Optional.of(question2));
        when(questionRepository.findByPublicId("id3")).thenReturn(Optional.of(question3));
        when(questionRepository.findByPublicId("id4")).thenReturn(Optional.of(question4));
        when(questionRepository.findByPublicId("id5")).thenReturn(Optional.of(question5));

        // when
        uut.updateWeights(request);

        // then
        assertThat(question.getValue()).isEqualTo(67);
        assertThat(question2.getValue()).isEqualTo(134);
        assertThat(question3.getValue()).isEqualTo(201);
        assertThat(question4.getValue()).isEqualTo(268);
        assertThat(question5.getValue()).isEqualTo(335);
    }
}