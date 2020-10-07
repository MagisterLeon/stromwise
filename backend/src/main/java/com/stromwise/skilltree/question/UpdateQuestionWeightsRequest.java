package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionWeightsRequest {

    @Size(max = 10, message = "question ids cannot be more than ten")
    private List<String> questionPublicIds;
}
