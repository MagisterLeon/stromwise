package com.stromwise.skilltree.question;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final UpdateQuestionService updateQuestionService;
    private final GetRandomQuestionsService getRandomQuestionsService;
    private final GetQuestionsService getQuestionsService;

    @ApiOperation(value = "Update questions rasponses")
    @PutMapping("/responses")
    public ResponseEntity<Void> updateQuestionResponses(@RequestBody @Valid UpdateQuestionResponses request) {
        updateQuestionService.updateResponses(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get random questions belonging to given category with limit defined by questionResultLimit property")
    @GetMapping("/categories/{categoryName}")
    public ResponseEntity<List<QuestionPayload>> getQuestionsSimplified(@PathVariable String categoryName) {
        List<QuestionPayload> questionPayloads = getRandomQuestionsService.getRandomByCategory(categoryName);

        return new ResponseEntity<>(questionPayloads, HttpStatus.OK);
    }

    @ApiOperation(value = "Get answers for question")
    @GetMapping("/answers")
    public ResponseEntity<GetAnswersPayload> getAnswers(@RequestParam String category,
                                                        @RequestParam List<String> questions) {
        var answersPayload = getQuestionsService.getAnswers(category, questions);

        return new ResponseEntity<>(answersPayload, HttpStatus.OK);
    }
}
