package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddQuestionRequest {
    private String question;
    private String answer;
    private List<String> categories;

}
