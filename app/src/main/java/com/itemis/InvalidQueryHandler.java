package com.itemis;

public class InvalidQueryHandler {
    private ResultDisplayer resultDisplayer;

    public InvalidQueryHandler(ResultDisplayer resultDisplayer) {
        this.resultDisplayer = resultDisplayer;
    }

    public void addInvalidQueryToResult() {
        resultDisplayer.addResult("I have no idea what you are talking about");
    }
}
