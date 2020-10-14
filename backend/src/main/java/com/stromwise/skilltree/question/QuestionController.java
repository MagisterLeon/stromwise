package com.stromwise.skilltree.question;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final AddQuestionService addQuestionService;
    private final UpdateQuestionService updateQuestionService;
    private final GetQuestionService getQuestionService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody @Valid AddQuestionRequest request) {
        addQuestionService.add(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/weights")
    public ResponseEntity<Void> updateQuestionWeights(@RequestBody @Valid UpdateQuestionWeightsRequest request) {
        updateQuestionService.updateWeights(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Get max to ten random questions belong to specific category")
    @GetMapping("/{categoryName}")
    public ResponseEntity<Set<Question>> getQuestionsSimplified(@PathVariable("categoryName") String categoryName) {
        Set<Question> questionSet = getQuestionService.getQuestionsBelongToSpecificCategory(categoryName);

        return new ResponseEntity<>(questionSet, HttpStatus.OK);
    }
}
