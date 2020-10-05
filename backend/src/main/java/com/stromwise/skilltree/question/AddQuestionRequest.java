package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddQuestionRequest {

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    @NotEmpty
    private List<String> categories;

}
