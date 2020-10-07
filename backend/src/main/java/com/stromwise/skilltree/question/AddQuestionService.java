package com.stromwise.skilltree.question;

import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class AddQuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    void add(AddQuestionRequest request) {
        log.info("Adding question: {}", request.getQuestion());

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
