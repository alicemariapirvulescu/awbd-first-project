package org.example.controller.payload.response;

import java.util.List;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import lombok.NonNull;

public class GetResultsResponse {
    private List<GetResultResponse> results;

    public GetResultsResponse() {
        this.results = new ArrayList<>();  // Ensure the list is never null
    }

    public GetResultsResponse(@NonNull List<GetResultResponse> results) {
        if (results == null) {
            throw new NullPointerException("Results must not be null");
        }
        this.results = results;
    }

    public List<GetResultResponse> getResults() {
        return results;
    }

    public void setResults(List<GetResultResponse> results) {
        if (results == null) {
            throw new NullPointerException("Results must not be null");
        }
        this.results = results;
    }
}
