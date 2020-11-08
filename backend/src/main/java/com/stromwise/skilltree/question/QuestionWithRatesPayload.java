package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class QuestionWithRatesPayload {

    String publicId;
    String question;
    String answer;
    int know;
    int notSure;
    int dontKnow;
}
