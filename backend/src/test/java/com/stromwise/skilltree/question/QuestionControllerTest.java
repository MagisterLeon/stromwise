package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.category.CategoryRepository;
import com.stromwise.skilltree.configuration.SkilltreeProperties;
import com.stromwise.skilltree.infastructure.rest.RestExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareCategories;
import static com.stromwise.skilltree.question.utils.TestDataFactory.prepareQuestions;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;
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

    private final QuestionConverter questionConverter = new QuestionConverter();

    private final String QUESTIONS_URL = "/api/v1/questions";

    private final String BAD_REQUEST_ERROR_CODE = "01002";
    private final String BAD_REQUEST_ERROR_MESSAGE = "Bad request";

    @Value("${questions.result.limit}")
    private int questionsResultLimit;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        var addQuestionService = new AddQuestionService(questionRepository, categoryRepository);
        var updateQuestionService = new UpdateQuestionService(questionRepository, skilltreeProperties);
        var getQuestionService = new GetRandomQuestionsService(questionConverter, questionRepository);
        var getQuestionResponseRateService = new GetQuestionsService(questionConverter, questionRepository);

        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new QuestionController(addQuestionService, updateQuestionService, getQuestionService, getQuestionResponseRateService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void should_save_question() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(QUESTIONS_URL)
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
                MockMvcRequestBuilders.post(QUESTIONS_URL)
                        .content(asJsonString(AddQuestionRequest.builder()
                                .categories(List.of())
                                .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is(BAD_REQUEST_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(BAD_REQUEST_ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", containsString("categories: categories cannot be empty")))
                .andExpect(jsonPath("$.details", containsString("question: question cannot be blank")));
    }

    @Test
    void should_update_question_weights() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch(QUESTIONS_URL + "/weights")
                        .content(asJsonString(new UpdateQuestionWeightsRequest(List.of("id"))))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_throw_bad_request_on_invalid_content_when_update_weights() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.patch(QUESTIONS_URL + "/weights")
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
                .andExpect(jsonPath("$.code", is(BAD_REQUEST_ERROR_CODE)))
                .andExpect(jsonPath("$.message", is(BAD_REQUEST_ERROR_MESSAGE)))
                .andExpect(jsonPath("$.details", is("[questionPublicIds: question ids cannot be more than ten]")));
    }

    @Test
    void should_return_questions_belong_to_specific_category() throws Exception {
        int questionSize = 5;

        List<Question> questions = prepareQuestions(questionSize, prepareCategories(2));

        when(questionRepository.findRandomByCategoryName("programming", questionsResultLimit)).thenReturn(questions);

        mockMvc.perform(
                MockMvcRequestBuilders.get(QUESTIONS_URL + "/categories/programming")
                        .accept("application/json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(questionSize)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].publicId", notNullValue()))
                .andExpect(jsonPath("$[0].question", is("question 1")))
                .andExpect(jsonPath("$[0].answer", is("answer 1")))
                .andExpect(jsonPath("$[1].publicId", notNullValue()))
                .andExpect(jsonPath("$[1].question", is("question 2")))
                .andExpect(jsonPath("$[1].answer", is("answer 2")));

        verify(questionRepository).findRandomByCategoryName("programming", questionsResultLimit);
    }

    @Test
    void should_return_questions_responses_rates() throws Exception {
        int questionListSize = 2;

        List<Question> questions = prepareQuestions(questionListSize, prepareCategories(2));
        String publicIdFirst = questions.get(0).getPublicId();
        String publicIdSecond = questions.get(1).getPublicId();

        when(questionRepository.findByPublicIdIn(List.of(publicIdFirst, publicIdSecond))).thenReturn(questions);

        mockMvc.perform(
                MockMvcRequestBuilders.get(QUESTIONS_URL)
                        .accept("application/json")
                        .param("publicIds", publicIdFirst, publicIdSecond)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].know", is(1)))
                .andExpect(jsonPath("$[0].notSure", is(1)))
                .andExpect(jsonPath("$[0].notKnow", is(1)))
                .andExpect(jsonPath("$[1].know", is(2)))
                .andExpect(jsonPath("$[1].notSure", is(2)))
                .andExpect(jsonPath("$[1].notKnow", is(2)));
    }
}