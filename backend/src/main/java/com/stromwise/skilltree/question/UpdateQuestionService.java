package com.stromwise.skilltree.question;

import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
class UpdateQuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    void updateResponses(UpdateQuestionResponses request) {
        log.info("Updating question responses");

        List<Question> updatedQuestions = Stream.of(
                updateKnownQuestions(request.getKnownQuestions()),
                updateNotSureQuestions(request.getNotSureQuestions()),
                updateNotKnowQuestions(request.getNotKnowQuestions())
        ).flatMap(Collection::stream)
                .peek(q -> q.addCategory(getCategory(request.getCategory())))
                .collect(Collectors.toList());

        questionRepository.saveAll(updatedQuestions);
    }

    private List<Question> updateKnownQuestions(List<String> questions) {
        List<Question> questionEntities = questionRepository.findByQuestionInIgnoreCase(questions);
        enrichWithMissingEntities(new ArrayList<>(questions), questionEntities);
        questionEntities.forEach(entity -> entity.setKnow(entity.getKnow() + 1));
        return questionEntities;
    }

    private List<Question> updateNotSureQuestions(List<String> questions) {
        List<Question> questionEntities = questionRepository.findByQuestionInIgnoreCase(questions);
        enrichWithMissingEntities(new ArrayList<>(questions), questionEntities);
        questionEntities.forEach(entity -> entity.setNotSure(entity.getNotSure() + 1));
        return questionEntities;
    }

    private List<Question> updateNotKnowQuestions(List<String> questions) {
        List<Question> questionEntities = questionRepository.findByQuestionInIgnoreCase(questions);
        enrichWithMissingEntities(new ArrayList<>(questions), questionEntities);
        questionEntities.forEach(entity -> entity.setNotKnow(entity.getNotKnow() + 1));
        return questionEntities;
    }

    private void enrichWithMissingEntities(List<String> questions, List<Question> questionEntities) {
        if (questionEntities.size() < questions.size()) {
            List<String> questionsFromDb = questionEntities.stream()
                    .map(Question::getQuestion)
                    .collect(Collectors.toList());
            questions.removeAll(questionsFromDb);
            List<Question> missingQuestions = questions.stream().map(Question::new).collect(Collectors.toList());
            questionEntities.addAll(missingQuestions);
        }
    }

    private Category getCategory(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElse(new Category(categoryName));
    }
}
