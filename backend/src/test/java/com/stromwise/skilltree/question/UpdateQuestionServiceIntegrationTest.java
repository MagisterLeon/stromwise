package com.stromwise.skilltree.question;

import com.stromwise.skilltree.IntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateQuestionServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UpdateQuestionService updateQuestionService;

    private Question question1;
    private Question question2;

    @BeforeEach
    public void setUp() {
        question1 = new Question("question1", "answer");
        question2 = new Question("question2", "answer");

        questionRepository.saveAll(List.of(question1, question2));
    }

    @Test
    public void should_update_question_weights() {
        // given
        UpdateQuestionWeightAndRatesRequest request = new UpdateQuestionWeightAndRatesRequest(
                List.of(question1.getPublicId(), question2.getPublicId()), List.of(), List.of());

        // when
        updateQuestionService.updateWeightsAndRates(request);

        // then
        var question1FromDb = questionRepository.findByPublicId(question1.getPublicId()).get();
        assertThat(question1FromDb.getValue()).isEqualTo(333);
        var question2FromDb = questionRepository.findByPublicId(question2.getPublicId()).get();
        assertThat(question2FromDb.getValue()).isEqualTo(666);
    }
}
