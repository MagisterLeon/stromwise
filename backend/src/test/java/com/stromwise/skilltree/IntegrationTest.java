package com.stromwise.skilltree;

import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import com.stromwise.skilltree.question.Question;
import com.stromwise.skilltree.question.QuestionRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.List;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareQuestions;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SkilltreeApplication.class)
@ExtendWith(value = {
        SpringExtension.class,
        MockitoExtension.class
})
public abstract class IntegrationTest {

    @Autowired
    protected CategoryRepository categoryRepository;
    @Autowired
    protected QuestionRepository questionRepository;

    protected void prepareTestData(int categoriesAmount, int questionsAmount) {
        List<Category> categoryList = prepareCategories(categoriesAmount);

        List<Question> questionList = prepareQuestions(questionsAmount, categoryList);

        categoryRepository.saveAll(categoryList);
        questionRepository.saveAll(questionList);

        assertThat(categoryRepository.findAll().size()).isEqualTo(categoriesAmount);
        assertThat(questionRepository.findAll().size()).isEqualTo(questionsAmount);
    }

    protected void prepareTestData(Category category, int questionsAmount) {
        List<Question> questionList = prepareQuestions(questionsAmount, category);

        categoryRepository.save(category);
        questionRepository.saveAll(questionList);
    }
}
