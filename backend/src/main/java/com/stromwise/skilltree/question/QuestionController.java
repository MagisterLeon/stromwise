package com.stromwise.skilltree.question;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final AddQuestionService addQuestionService;
    private final UpdateQuestionService updateQuestionService;

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
}
