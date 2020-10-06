package com.stromwise.skilltree.question;

import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class QuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    void add(AddQuestionRequest request) {
        Question question = new Question(request.getQuestion(), request.getAnswer());

        List<Category> categories = request.getCategories().stream()
                .map(this::getCategory)
                .collect(Collectors.toList());

        question.addCategories(categories);
        questionRepository.save(question);
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElse(new Category(categoryName));
    }
}
