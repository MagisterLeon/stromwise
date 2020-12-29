package com.stromwise.skilltree.question;

import com.stromwise.skilltree.IntegrationTest;
import com.stromwise.skilltree.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateQuestionServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UpdateQuestionService updateQuestionService;

    private Question question1;
    private Question question2;
    private Question question3;

    @BeforeEach
    public void setUp() {
        question1 = new Question("question1");
        question2 = new Question("question2");
        question3 = new Question("question3");

        questionRepository.saveAll(List.of(question1, question2, question3));
    }

    @Test
    public void should_update_question_responses() {
        // given
        UpdateQuestionResponses request = new UpdateQuestionResponses(
                "programming",
                List.of(question1.getQuestion()),
                List.of(question2.getQuestion()),
                List.of(question3.getQuestion()));

        // when
        updateQuestionService.updateResponses(request);

        // then
        assertAll(() -> {
            var question1FromDb = questionRepository.findByPublicId(question1.getPublicId()).get();
            assertAll("question1",
                    () -> assertTrue(question1FromDb.getCategories().contains(new Category("programming"))),
                    () -> assertThat(question1FromDb.getKnow()).isEqualTo(1),
                    () -> assertThat(question1FromDb.getNotSure()).isEqualTo(0),
                    () -> assertThat(question1FromDb.getNotKnow()).isEqualTo(0)
            );
            var question2FromDb = questionRepository.findByPublicId(question2.getPublicId()).get();
            assertAll("question2",
                    () -> assertTrue(question2FromDb.getCategories().contains(new Category("programming"))),
                    () -> assertThat(question2FromDb.getKnow()).isEqualTo(0),
                    () -> assertThat(question2FromDb.getNotSure()).isEqualTo(1),
                    () -> assertThat(question2FromDb.getNotKnow()).isEqualTo(0)
            );
            var question3FromDb = questionRepository.findByPublicId(question3.getPublicId()).get();
            assertAll("question3",
                    () -> assertTrue(question3FromDb.getCategories().contains(new Category("programming"))),
                    () -> assertThat(question3FromDb.getKnow()).isEqualTo(0),
                    () -> assertThat(question3FromDb.getNotSure()).isEqualTo(0),
                    () -> assertThat(question3FromDb.getNotKnow()).isEqualTo(1)
            );
        });
    }

    @Test
    public void should_add_new_question_when_not_found_in_db() {
        // given
        UpdateQuestionResponses request = new UpdateQuestionResponses(
                "programming",
                List.of("new question"),
                List.of(),
                List.of());

        // when
        updateQuestionService.updateResponses(request);

        // then
        var question1FromDb = questionRepository.findByQuestionInIgnoreCase(List.of("new question")).get(0);
        assertTrue(question1FromDb.getCategories().contains(new Category("programming")));
        assertThat(question1FromDb.getKnow()).isEqualTo(1);
        assertThat(question1FromDb.getNotSure()).isEqualTo(0);
        assertThat(question1FromDb.getNotKnow()).isEqualTo(0);
    }
}
