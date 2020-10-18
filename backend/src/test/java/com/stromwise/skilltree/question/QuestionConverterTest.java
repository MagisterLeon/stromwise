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
        List<QuestionPayload> questionPayloadList = questionConverter.transformQuestions(questionList);

        // then
        assertThat(questionPayloadList.size()).isEqualTo(10);
    }

    @Test
    public void should_converted_payload_be_equal_to_entity() {
        // given
        List<Question> questionList = new ArrayList<>(prepareQuestions(10, prepareCategories(2)));

        // when
        List<QuestionPayload> questionPayloadList = questionConverter.transformQuestions(questionList);

        // then
        assertThat(questionPayloadList).usingElementComparatorOnFields("publicId", "question", "answer").isEqualTo(questionList);
    }
}