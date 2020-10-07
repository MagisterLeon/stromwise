package com.stromwise.skilltree.question;

import com.stromwise.skilltree.configuration.SkilltreeProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
class UpdateQuestionService {

    private final QuestionRepository questionRepository;
    private final SkilltreeProperties skilltreeProperties;

    void updateWeights(UpdateQuestionWeightsRequest request) {
        log.info("Updating question weights");

        List<Question> updatedQuestions = new ArrayList<>();

        List<String> questionPublicIds = request.getQuestionPublicIds();

        long minimumValueToDistribute = countMinimumValueToDistribute(skilltreeProperties.getPointsToDistribute(),
                questionPublicIds.size());

        IntStream.range(0, questionPublicIds.size()).forEach(i -> {
            String publicId = questionPublicIds.get(i);
            questionRepository.findByPublicId(publicId)
                    .ifPresent(question -> {
                        updateQuestionValue(question, minimumValueToDistribute, i);
                        updatedQuestions.add(question);
                    });
        });

        questionRepository.saveAll(updatedQuestions);
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
