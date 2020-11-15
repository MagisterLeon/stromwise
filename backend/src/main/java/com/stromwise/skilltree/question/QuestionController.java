package com.stromwise.skilltree.question;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final AddQuestionService addQuestionService;
    private final UpdateQuestionService updateQuestionService;
    private final GetRandomQuestionsService getRandomQuestionsService;
    private final GetQuestionsService getQuestionsService;

    @ApiOperation(value = "Add question to DB")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid AddQuestionRequest request) {
        addQuestionService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update questions weights and rates")
    @PatchMapping("/weights-and-rates")
    public ResponseEntity<Void> updateQuestionWeightsAndRates(@RequestBody @Valid UpdateQuestionWeightAndRatesRequest request) {
        updateQuestionService.updateWeightsAndRates(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get random questions belonging to given category with limit defined by questionResultLimit property")
    @GetMapping("/categories/{categoryName}")
    public ResponseEntity<List<QuestionPayload>> getQuestionsSimplified(@PathVariable String categoryName) {
        List<QuestionPayload> questionPayloads = getRandomQuestionsService.getRandomByCategory(categoryName);

        return new ResponseEntity<>(questionPayloads, HttpStatus.OK);
    }

    @ApiOperation(value = "Get question response rates (how other people voted)")
    @GetMapping
    public ResponseEntity<List<QuestionWithRatesPayload>> getQuestions(@RequestParam List<String> publicIds) {
        var questions =
                getQuestionsService.getQuestions(publicIds);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
}
