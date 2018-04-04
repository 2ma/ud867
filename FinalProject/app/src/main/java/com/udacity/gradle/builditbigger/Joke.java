package com.udacity.gradle.builditbigger;

public class Joke {
    private final String question;
    private final String answer;

    public Joke(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
