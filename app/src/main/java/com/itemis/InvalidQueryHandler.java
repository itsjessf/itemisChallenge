package com.itemis;

public class InvalidQueryHandler {
    private AnswersRepository answersRepository;

    public InvalidQueryHandler(AnswersRepository answersRepository) {
        this.answersRepository = answersRepository;
    }

    public void addInvalidQueryToResult(String message) {
        answersRepository.addAnswer(message);
    }
}
