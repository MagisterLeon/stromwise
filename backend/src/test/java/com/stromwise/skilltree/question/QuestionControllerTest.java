package com.stromwise.skilltree.question;

import com.stromwise.skilltree.UnitTest;
import com.stromwise.skilltree.category.CategoryRepository;
import com.stromwise.skilltree.infastructure.rest.RestExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QuestionControllerTest extends UnitTest {

    private MockMvc mockMvc;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(new QuestionController(questionService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
    }

    @Test
    void should_save_question() throws Exception {
        // given

        // then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/questions")
                        .content(asJsonString(AddQuestionRequest.builder()
                                .question("question")
                                .answer("answer")
                                .categories(List.of("category"))
                        .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void should_throw_bad_request_on_invalid_content() throws Exception {
        // given

        // then
        mockMvc.perform(
                MockMvcRequestBuilders.post("/v1/questions")
                        .content(asJsonString(AddQuestionRequest.builder()
                                .question("question")
                                .answer("answer")
                                .categories(List.of())
                        .build()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code", is("01002")))
                .andExpect(jsonPath("$.message", is("Bad request")));
    }
}
