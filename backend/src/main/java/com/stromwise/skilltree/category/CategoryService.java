package com.stromwise.skilltree.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CategoryService {

    private final CategoryRepository categoryRepository;

    @Value("${category.mostPopular.limit}")
    private int categoriesLimit;

    Set<String> getAllNames() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }

    public Set<String> getMostPopularNames() {
        return categoryRepository.getCategoryNamesAndQuestionCounts().stream()
                .limit(categoriesLimit)
                .map(CategoryNameAndQuestionsCount::getName)
                .collect(Collectors.toSet());
    }
}
