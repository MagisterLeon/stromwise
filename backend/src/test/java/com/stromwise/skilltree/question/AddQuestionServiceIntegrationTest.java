package com.stromwise.skilltree.question;

import com.stromwise.skilltree.IntegrationTest;
import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AddQuestionServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AddQuestionService addQuestionService;

    private AddQuestionRequest request;

    @BeforeEach
    void setUp() {
        request = new AddQuestionRequest(
                "test question", "test answer", List.of("category 1", "category 2"));
    }

    @Test
    @Transactional
    public void should_add_question_with_existing_categories() {
        // given
        Category category1 = categoryRepository.save(new Category("category 1"));
        Category category2 = categoryRepository.save(new Category("category 2"));

        // when
        addQuestionService.add(request);

        // then
        assertQuestion();
        assertCategories(category1.getId(), category2.getId());
    }

    @Test
    @Transactional
    public void should_add_question_with_new_categories() {
        // when
        addQuestionService.add(request);

        // then
        assertQuestion();
        assertCategories();
    }


    @Test
    @Transactional
    public void should_add_question_with_one_new_category_and_one_existing() {
        // given
        Category category = categoryRepository.save(new Category("category 1"));

        // when
        addQuestionService.add(request);

        // then
        assertQuestion();
        assertCategories(category.getId());
    }


    private void assertQuestion() {
        List<Question> questions = questionRepository.findAll();
        Question question = questions.get(0);

        assertAll("question",
                () -> assertThat(question.getQuestion()).isEqualTo("test question"),
                () -> assertThat(question.getAnswer()).isEqualTo("test answer"),
                () -> assertThat(question.getValue()).isEqualTo(0),
                () -> assertThat(question.getCategories())
                        .extracting("name")
                        .containsExactlyInAnyOrder("category 1", "category 2")
        );
    }

    private void assertCategories(Long... ids) {
        List<Category> categories = categoryRepository.findAll();

        assertThat(categories).hasSize(2);
            assertThat(categories)
                    .extracting("name")
                    .containsExactlyInAnyOrder("category 1", "category 2");

            if (ids.length > 0) {
                assertThat(categories)
                        .extracting("id")
                        .contains(ids);
            }
    }
}
