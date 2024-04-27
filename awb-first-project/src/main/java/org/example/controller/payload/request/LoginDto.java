package org.example.controller.payload.request;

import jakarta.validation.constraints.Size;
import lombok.NonNull;

public record LoginDto(@NonNull @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") String username,
                       @NonNull  @Size(min = 3, max = 100, message = "Password must be between 6 and 100 characters") String password) {}

