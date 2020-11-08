package com.stromwise.skilltree.question;

import com.stromwise.skilltree.IntegrationTest;
import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import javax.transaction.Transactional;
import java.util.List;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareQuestions;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class GetRandomQuestionsServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GetRandomQuestionsService getRandomQuestionsService;

    @AfterEach
    public void setup() {
        questionRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void should_get_ten_questions_of_eleven_with_limit_of_ten() {
        // given
        prepareTestData(2, 11);

        // when
        List<QuestionPayload> questions = getRandomQuestionsService.getRandomByCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(10);
    }

    @Test
    public void should_get_nine_questions_of_ten_with_limit_of_ten() {
        // given
        prepareTestData(1, 9);

        // when
        List<QuestionPayload> questions = getRandomQuestionsService.getRandomByCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(9);
    }

    @Test
    public void should_get_zero_questions_if_questions_not_exists() {
        // given
        prepareTestData(1, 0);

        // when
        List<QuestionPayload> questions = getRandomQuestionsService.getRandomByCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(0);
    }

    @Test
    public void should_get_zero_questions_if_category_not_exists() {
        // given
        prepareTestData(0, 1);

        // when
        List<QuestionPayload> questions = getRandomQuestionsService.getRandomByCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(0);
    }

    private void prepareTestData(int categoriesAmount, int questionsAmount) {
        List<Category> categoryList = prepareCategories(categoriesAmount);

        List<Question> questionList = prepareQuestions(questionsAmount, categoryList);

        categoryRepository.saveAll(categoryList);
        questionRepository.saveAll(questionList);

        assertThat(categoryRepository.findAll().size()).isEqualTo(categoriesAmount);
        assertThat(questionRepository.findAll().size()).isEqualTo(questionsAmount);
    }
}
