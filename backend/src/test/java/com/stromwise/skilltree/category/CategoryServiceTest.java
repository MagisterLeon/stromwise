package com.stromwise.skilltree.category;

import com.stromwise.skilltree.IntegrationTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static org.assertj.core.api.Assertions.assertThat;

class CategoryServiceTest extends IntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @AfterEach
    public void setup() {
        questionRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void should_get_most_popular_categories() {
        // given
        List<Category> categories = prepareCategories(5);
        prepareTestData(categories.get(0), 0);
        prepareTestData(categories.get(1), 6);
        prepareTestData(categories.get(2), 5);
        prepareTestData(categories.get(3), 1);
        prepareTestData(categories.get(4), 4);

        // when
        Set<String> categoryNames = categoryService.getMostPopularNames();

        // then
        assertThat(categoryNames).containsExactlyInAnyOrder("category 2", "category 3", "category 5");
    }

    @Test
    public void should_get_most_popular_categories_when_thay_has_same_questions_amount() {
        // given
        List<Category> categories = prepareCategories(3);
        prepareTestData(categories.get(0), 5);
        prepareTestData(categories.get(1), 5);
        prepareTestData(categories.get(2), 5);

        // when
        Set<String> categoryNames = categoryService.getMostPopularNames();

        // then
        assertThat(categoryNames).containsExactlyInAnyOrder("category 1", "category 2", "category 3");
    }

}