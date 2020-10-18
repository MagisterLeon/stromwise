package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareQuestions;
import static org.assertj.core.api.Assertions.assertThat;

class QuestionConverterTest extends UnitTest {

    @InjectMocks
    private QuestionConverter questionConverter;

    @Test
    public void should_convert_entities_to_payloads() {
        // given
        List<Question> questionList = new ArrayList<>(prepareQuestions(10, prepareCategories(2)));

        // when
        List<QuestionPayload> questionPayloadList = questionConverter.transform(questionList);

        // then
        assertThat(questionPayloadList.size()).isEqualTo(10);
    }

    @Test
    public void should_fail_if_payload_field_has_different_value() {
        // given
        List<Question> questionList = new ArrayList<>(prepareQuestions(10, prepareCategories(2)));

        // when
        List<QuestionPayload> questionPayloadList = questionConverter.transform(questionList);
        questionPayloadList.set(0, new QuestionPayload("new value", "new value", "new value"));

        // then
        assertThat(questionPayloadList).usingElementComparatorOnFields("publicId", "question", "answer").isNotEqualTo(questionList);
    }
}