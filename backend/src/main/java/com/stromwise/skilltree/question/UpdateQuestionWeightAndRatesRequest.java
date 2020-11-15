package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionWeightAndRatesRequest {

    @Size(max = 10, message = "known question ids cannot be more than ten")
    private List<String> knownQuestionPublicIds;
    @Size(max = 10, message = "not sure question ids cannot be more than ten")
    private List<String> notSureQuestionPublicIds;
    @Size(max = 10, message = "don't know question ids cannot be more than ten")
    private List<String> dontKnowQuestionPublicIds;
}
