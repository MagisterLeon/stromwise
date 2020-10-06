package com.stromwise.skilltree.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class CategoryService {

    private final CategoryRepository categoryRepository;

    Set<String> getAllNames() {
        return categoryRepository.findAll().stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}
