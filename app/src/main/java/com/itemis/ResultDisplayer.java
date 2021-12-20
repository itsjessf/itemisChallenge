package com.itemis;

import java.util.List;

public class ResultDisplayer {
    private final AnswersRepository answersRepository;

    public ResultDisplayer(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    public void displayResult() {
        List<String> answers = answersRepository.getAnswers();
        for ( Object line : answers){
            System.out.println(line.toString());
        }
    }

}
