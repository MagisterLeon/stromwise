package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.category.CategoryRepository;
import com.stromwise.skilltree.configuration.SkilltreeProperties;
import com.stromwise.skilltree.infastructure.rest.RestExceptionHandler;
import com.stromwise.skilltree.utils.QuestionConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.stromwise.skilltree.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.utils.TestDataFactory.prepareQuestions;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerTest extends UnitTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SkilltreeProperties skilltreeProperties;
    @InjectMocks
    private QuestionConverter questionConverter;

    private final String URL = "/v1/questions";

    @Value("${questions.result.limit}")
    private String questionsResultLimit;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        var addQuestionService = new AddQuestionService(questionRepository, categoryRepository);
        var updateQuestionService = new UpdateQuestionService(questionRepository, skilltreeProperties);
        var getQuestionService = new GetQuestionService(questionConverter, questionRepository);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new QuestionController(addQuestionService, updateQuestionService, getQuestionService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void should_save_question() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(asJsonString(AddQuestionRequest.builder()
                                .question("question")
                                .answer("answer")
                                .categories(List.of("category"))
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_throw_bad_request_on_invalid_content_when_add_question() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(URL)
                        .content(asJsonString(AddQuestionRequest.builder()
                                .categories(List.of())
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("01002")))
                .andExpect(jsonPath("$.message", is("Bad request")))
                .andExpect(jsonPath("$.details", containsString("categories: categories cannot be empty")))
                .andExpect(jsonPath("$.details", containsString("question: question cannot be blank")));
    }

    @Test
    void should_update_question_weights() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch(URL + "/weights")
                        .content(asJsonString(new UpdateQuestionWeightsRequest(List.of("id"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_throw_bad_request_on_invalid_content_when_update_weights() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch(URL + "/weights")
                        .content(asJsonString(new UpdateQuestionWeightsRequest(
                                (List.of(
                                        "id1",
                                        "id2",
                                        "id3",
                                        "id4",
                                        "id5",
                                        "id6",
                                        "id7",
                                        "id8",
                                        "id9",
                                        "id10",
                                        "id11"
                                )))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("01002")))
                .andExpect(jsonPath("$.message", is("Bad request")))
                .andExpect(jsonPath("$.details", is("[questionPublicIds: question ids cannot be more than ten]")));
    }

    @Test
    void should_return_questions_belong_to_specific_category() throws Exception {
        int questionSize = 5;

        Set<Question> questionSet = new HashSet<>(prepareQuestions(questionSize, prepareCategories(2)));

        when(questionRepository.findRandomByCategoryName("programming", questionsResultLimit)).thenReturn(questionSet);
        Set<QuestionPayload> questionPayloadSet = questionConverter.transform(questionSet);


        mockMvc.perform(
                MockMvcRequestBuilders.get(URL + "/programming")
                        .accept("application/json")
                        .content((asJsonString(
                                (List.of(
                                        questionPayloadSet
                                )))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(questionSize)))
                .andExpect(status().isOk());
    }
}