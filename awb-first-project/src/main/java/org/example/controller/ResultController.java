package org.example.controller;

import org.example.controller.payload.request.SaveResultRequest;
import org.example.controller.payload.response.GetResultResponse;
import org.example.controller.payload.response.GetResultsResponse;
import org.example.service.impl.ResultService;
import org.example.validation.DuolingoRuntimeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/duolingo/results")
@RequiredArgsConstructor
@Slf4j
public class ResultController {

  private final ResultService resultService;

//  @GetMapping(path = "/{languageName}")
//  public ResponseEntity<GetResultsResponse> getResults(
//      @PathVariable final String languageName) throws DuolingoRuntimeException {
//
//    return ResponseEntity.ok(resultService.findResultsForLanguage(languageName));
//  }
  @GetMapping(path = "/get-result-details/{resultId}")
  public ResponseEntity<GetResultResponse> getResultDetails(
      @PathVariable final Long resultId) throws DuolingoRuntimeException {

    return ResponseEntity.ok(resultService.getResultDetails(resultId));
  }


}
