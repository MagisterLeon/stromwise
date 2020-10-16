package com.stromwise.skilltree.utils;

import com.stromwise.skilltree.category.Category;
import com.stromwise.skilltree.question.Question;
import com.stromwise.skilltree.question.QuestionPayload;

import java.util.ArrayList;
import java.util.List;

public class TestDataFactory {

    public static List<Category> prepareCategories(int categoriesAmount) {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 1; i < categoriesAmount + 1; i++) {
            categoryList.add(new Category("category " + i));
        }

        return categoryList;
    }

    public static List<Question> prepareQuestions(int questionsAmount, List<Category> categoryList) {
        List<Question> questionList = new ArrayList<>();
        for (int i = 1; i < questionsAmount + 1; i++) {
            Question question = new Question("question " + i, "answer " + i);
            question.addCategories(categoryList);

            questionList.add(question);
        }

        return questionList;
    }

    public static List<QuestionPayload> prepareQuestionsPayload(int questionsAmount) {
        List<QuestionPayload> questionList = new ArrayList<>();
        for (int i = 1; i < questionsAmount + 1; i++) {
            QuestionPayload question = new QuestionPayload("public id" + i, "question " + i, "answer " + i);

            questionList.add(question);
        }

        return questionList;
    }
}
