package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.configuration.SkilltreeProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class UpdateQuestionServiceTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private SkilltreeProperties skilltreeProperties;

    private UpdateQuestionService uut;

    @BeforeEach
    public void setUp() {
        var knownQuestionsUpdater = new KnownQuestionsUpdater(questionRepository, skilltreeProperties);
        uut = new UpdateQuestionService(questionRepository, knownQuestionsUpdater);
        when(skilltreeProperties.getPointsToDistribute()).thenReturn(1000);
    }

    @Test
    public void should_give_all_points_to_one_question() {
        // given
        Question question = new Question();
        question.setPublicId("id");
        var request = new UpdateQuestionWeightAndRatesRequest(List.of("id"), List.of(), List.of());
        when(questionRepository.findByPublicIdIn(anyList())).thenReturn(List.of(question));

        // when
        uut.updateWeightsAndRates(request);

        // then
        assertThat(question.getValue()).isEqualTo(1000);
    }

    @Test
    public void should_split_points_between_two_question() {
        // given
        Question question1 = new Question();
        question1.setPublicId("id1");
        Question question2 = new Question();
        question2.setPublicId("id2");

        var request = new UpdateQuestionWeightAndRatesRequest(List.of("id1", "id2"), List.of(), List.of());
        when(questionRepository.findByPublicIdIn(List.of("id1", "id2"))).thenReturn(List.of(question1, question2));

        // when
        uut.updateWeightsAndRates(request);

        // then
        assertThat(question1.getValue()).isEqualTo(333);
        assertThat(question2.getValue()).isEqualTo(666);
    }

    @Test
    public void should_split_points_between_five_question() {
        // given
        Question question1 = new Question();
        question1.setPublicId("id1");
        Question question2 = new Question();
        question2.setPublicId("id2");
        Question question3 = new Question();
        question3.setPublicId("id3");
        Question question4 = new Question();
        question4.setPublicId("id4");
        Question question5 = new Question();
        question5.setPublicId("id5");

        var request = new UpdateQuestionWeightAndRatesRequest(
                List.of("id1", "id2", "id3", "id4", "id5"), List.of(), List.of());
        when(questionRepository.findByPublicIdIn(List.of("id1", "id2", "id3", "id4", "id5")))
                .thenReturn(List.of(question1, question2, question3, question4, question5));

        // when
        uut.updateWeightsAndRates(request);

        // then
        assertThat(question1.getValue()).isEqualTo(67);
        assertThat(question2.getValue()).isEqualTo(134);
        assertThat(question3.getValue()).isEqualTo(201);
        assertThat(question4.getValue()).isEqualTo(268);
        assertThat(question5.getValue()).isEqualTo(335);
    }

    @Test
    public void should_update_rates() {
        // given
        Question question1 = new Question();
        question1.setPublicId("id1");
        Question question2 = new Question();
        question2.setPublicId("id2");
        question2.setKnow(2);
        Question question3 = new Question();
        question3.setPublicId("id3");
        Question question4 = new Question();
        question4.setPublicId("id4");
        question4.setNotSure(1);
        Question question5 = new Question();
        question5.setPublicId("id5");

        var request = new UpdateQuestionWeightAndRatesRequest(
                List.of("id1", "id2"), List.of("id3", "id4"), List.of("id5"));
        when(questionRepository.findByPublicIdIn(List.of("id1", "id2")))
                .thenReturn(List.of(question1, question2));
        when(questionRepository.findByPublicIdIn(List.of("id3", "id4")))
                .thenReturn(List.of(question3, question4));
        when(questionRepository.findByPublicIdIn(List.of("id5")))
                .thenReturn(List.of(question5));

        // when
        uut.updateWeightsAndRates(request);

        // then
        assertThat(question1.getKnow()).isEqualTo(1);
        assertThat(question2.getKnow()).isEqualTo(3);
        assertThat(question3.getNotSure()).isEqualTo(1);
        assertThat(question4.getNotSure()).isEqualTo(2);
        assertThat(question5.getNotKnow()).isEqualTo(1);
    }
}