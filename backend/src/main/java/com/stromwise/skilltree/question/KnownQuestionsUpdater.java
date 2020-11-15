package com.stromwise.skilltree.question;

import com.stromwise.skilltree.configuration.SkilltreeProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
class KnownQuestionsUpdater {

    private final QuestionRepository questionRepository;
    private final SkilltreeProperties skilltreeProperties;

    List<Question> update(List<String> publicIds) {
        Map<String, Question> knownQuestionsByPublicIds = questionRepository.findByPublicIdIn(publicIds)
                .stream()
                .collect(Collectors.toMap(Question::getPublicId, Function.identity()));

        long minimumValueToDistribute = countMinimumValueToDistribute(skilltreeProperties.getPointsToDistribute(),
                knownQuestionsByPublicIds.size());

        IntStream.range(0, knownQuestionsByPublicIds.size()).forEach(i -> {
            String publicId = publicIds.get(i);
            Question question = knownQuestionsByPublicIds.get(publicId);
            updateQuestionValue(question, minimumValueToDistribute, i);
            question.setKnow(question.getKnow() + 1);
        });

        return new ArrayList<>(knownQuestionsByPublicIds.values());
    }

    private long countMinimumValueToDistribute(double pointsToDistribute, int idsListSize) {
        int summedIdsOrdinalNumbers = IntStream.iterate(idsListSize, i -> i > 0, i -> i - 1)
                .sum();

        double minimumValueToDistribute = pointsToDistribute / summedIdsOrdinalNumbers;
        return Math.round(minimumValueToDistribute);
    }

    private void updateQuestionValue(Question question, long minimumValueToDistribute, int ordinal) {
        long updatedValue = countNewValue(question.getValue(), minimumValueToDistribute, ordinal);
        question.setValue(updatedValue);
    }

    private long countNewValue(long oldValue, long minimumValueToDistribute, int ordinal) {
        return oldValue + minimumValueToDistribute * (ordinal + 1);
    }
}
