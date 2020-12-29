package com.stromwise.skilltree.question;

import lombok.Data;

import java.util.List;

@Data
public class GetAnswersPayload {
    private List<String> answers;
}
