package com.stromwise.skilltree.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query(value = "select c.name as name, count(q) as questionsCount" +
            " from Category c " +
            "join c.questions q " +
            "group by name " +
            "order by questionsCount desc")
    List<CategoryNameAndQuestionsCount> getCategoryNamesAndQuestionCounts();
}
