package com.stromwise.skilltree.question;

import com.google.common.collect.Ordering;
import com.stromwise.skilltree.IntegrationTest;
import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetQuestionServiceIntegrationTest extends IntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private GetQuestionService getQuestionService;


    @BeforeEach
    public void clearData() {
        questionRepository.deleteAll();
        categoryRepository.deleteAll();

        assertThat(questionRepository.findAll()).hasSize(0);
        assertThat(categoryRepository.findAll()).hasSize(0);
    }

    @Transactional
    @Test
    public void should_get_ten_questions_of_eleven_with_limit_of_ten() {
        // given
        prepareTestData(2, 11);

        // when
        Set<Question> questions = getQuestionService.getQuestionsBelongToSpecificCategory("category 1");

        // then
        assertFalse(areQuestionsSorted(questions));
        assertThat(questions.size()).isEqualTo(10);
    }

    @Transactional
    @Test
    public void should_get_nine_questions_of_four_with_limit_of_ten() {
        // given
        prepareTestData(1, 9);

        // when
        Set<Question> questions = getQuestionService.getQuestionsBelongToSpecificCategory("category 1");

        // then
        assertFalse(areQuestionsSorted(questions));
        assertThat(questions.size()).isEqualTo(9);
    }

    @Transactional
    @Test
    public void should_get_zero_questions_if_questions_not_exists() {
        // given
        prepareTestData(1, 0);

        // when
        Set<Question> questions = getQuestionService.getQuestionsBelongToSpecificCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(0);
    }

    @Transactional
    @Test
    public void should_get_zero_questions_if_category_not_exists() {
        // given
        prepareTestData(0, 1);

        // when
        Set<Question> questions = getQuestionService.getQuestionsBelongToSpecificCategory("category 1");

        // then
        assertThat(questions.size()).isEqualTo(0);
    }

    private boolean areQuestionsSorted(Set<Question> questions) {
        return Ordering.natural().isOrdered(questions.stream().map(Question::getId).collect(Collectors.toList()));
    }

    private void prepareTestData(int categoriesAmount, int questionsAmount) {
        List<Category> categoryList = prepareCategories(categoriesAmount);

        List<Question> questionList = prepareQuestions(questionsAmount, categoryList);

        categoryRepository.saveAll(categoryList);
        questionRepository.saveAll(questionList);

        assertThat(categoryRepository.findAll().size()).isEqualTo(categoriesAmount);
        assertThat(questionRepository.findAll().size()).isEqualTo(questionsAmount);
    }

    List<Category> prepareCategories(int categoriesAmount) {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 1; i < categoriesAmount + 1; i++) {
            categoryList.add(new Category("category " + i));
        }

        return categoryList;
    }

    List<Question> prepareQuestions(int questionsAmount, List<Category> categoryList) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 1; i < questionsAmount + 1; i++) {
            Question question = new Question("question " + i, "answer " + i);
            question.addCategories(categoryList);

            questionList.add(question);
        }

        return questionList;
    }
}
