package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionResponses {

    @NotNull(message = "category cannot be empty")
    private String category;

    @Size(max = 10, message = "known questions cannot be more than ten")
    private List<String> knownQuestions;
    @Size(max = 10, message = "not sure questions cannot be more than ten")
    private List<String> notSureQuestions;
    @Size(max = 10, message = "don't know questions cannot be more than ten")
    private List<String> notKnowQuestions;
}
