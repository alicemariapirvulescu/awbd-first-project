package org.example.controller.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTestResultRequest {
    private List<PostAnswerRequest> answers;
    private String languageName;

    public List<PostAnswerRequest> answers() {
        return answers;
    }

    public String languageName() {
        return languageName;
    }
}