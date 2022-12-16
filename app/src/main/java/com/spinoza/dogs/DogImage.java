package com.spinoza.dogs;

import androidx.annotation.NonNull;

public class DogImage {
    private final String message;
    private final String status;

    public DogImage(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("message=[%s]\nstatus=[%s]", message, status);
    }
}
