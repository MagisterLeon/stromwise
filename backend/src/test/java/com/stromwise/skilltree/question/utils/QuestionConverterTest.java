package com.stromwise.skilltree.question.utils;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.question.Question;
import com.stromwise.skilltree.question.QuestionPayload;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.HashSet;
import java.util.Set;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareQuestions;
import static org.assertj.core.api.Assertions.assertThat;

class QuestionConverterTest extends UnitTest {

    @InjectMocks
    private com.stromwise.skilltree.question.utils.QuestionConverter questionConverter;

    @Test
    public void should_convert_entities_to_payloads() {
        // given
        Set<Question> questionSet = new HashSet<>(prepareQuestions(10, prepareCategories(2)));

        // when
        Set<QuestionPayload> questionPayloadSet = questionConverter.transform(questionSet);

        // then
        assertThat(questionPayloadSet.size()).isEqualTo(10);
    }
}