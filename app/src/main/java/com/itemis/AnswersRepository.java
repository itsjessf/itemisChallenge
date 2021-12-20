package com.itemis;

import java.util.ArrayList;

public class AnswersRepository {

    private final ArrayList<String> answers = new ArrayList<>();

    public void addAnswer(String result) {
        answers.add(result);
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }
}
