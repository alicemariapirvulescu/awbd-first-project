package org.example.controller.payload.request;

import lombok.Data;

@Data
public class PostAnswerRequest {
    private String question;
    private String answer;

    public String question() {
        return question;
    }

    public String answer() {
        return answer;
    }

    public PostAnswerRequest(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public PostAnswerRequest() {
    }
}