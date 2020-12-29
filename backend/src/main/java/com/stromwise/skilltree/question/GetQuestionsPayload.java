package com.stromwise.skilltree.question;

import lombok.Data;

import java.util.List;

@Data
public class GetQuestionsPayload {
    private List<String> questions;
}
