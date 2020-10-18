package com.stromwise.skilltree.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor
@Builder
@Value
public class QuestionResponseRatePayload {

    int know;
    int notSure;
    int notKnow;
}
