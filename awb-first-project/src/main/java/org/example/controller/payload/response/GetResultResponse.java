package org.example.controller.payload.response;

import lombok.NonNull;

public record GetResultResponse(@NonNull Double grade,@NonNull String language, @NonNull String timestamp) {

}
