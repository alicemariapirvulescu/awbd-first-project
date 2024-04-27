package org.example.controller.payload.request;

import java.util.List;
import lombok.NonNull;

public record PostTestResultRequest(@NonNull List<PostAnswerRequest> answers, @NonNull String languageName) {

}
