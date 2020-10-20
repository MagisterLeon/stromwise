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
    private final GetQuestionService getQuestionService;
    private final GetQuestionResponseRateService getQuestionResponseRateService;

    @ApiOperation(value = "Add question to DB")
    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid AddQuestionRequest request) {
        addQuestionService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update weights one of questions")
    @PatchMapping("/weights")
    public ResponseEntity<Void> updateQuestionWeights(@RequestBody @Valid UpdateQuestionWeightsRequest request) {
        updateQuestionService.updateWeights(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get random questions belonging to given category with limit defined by questionResultLimit property")
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<QuestionPayload>> getQuestionsSimplified(@PathVariable String categoryName) {
        List<QuestionPayload> questionPayloads = getQuestionService.getQuestionByCategory(categoryName);

        return new ResponseEntity<>(questionPayloads, HttpStatus.OK);
    }

    @ApiOperation(value = "Get question response rates (how other people voted)")
    @GetMapping
    public ResponseEntity<List<QuestionResponseRatePayload>> getQuestionResponseRates(@RequestParam List<String> publicIds) {
        var questionResponseRatePayloadList =
                getQuestionResponseRateService.getQuestionsResponsesRates(publicIds);

        return new ResponseEntity<>(questionResponseRatePayloadList, HttpStatus.OK);
    }
}
