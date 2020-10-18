package com.stromwise.skilltree.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class QuestionResponseRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    int know;
    int notSure;
    int notKnow;

    @OneToOne(mappedBy = "question_response_rate")
    private Question question;
}
