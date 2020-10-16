package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@AllArgsConstructor
@Value
@Data
public class QuestionPayload {

    String publicId;
    String question;
    String answer;
}
