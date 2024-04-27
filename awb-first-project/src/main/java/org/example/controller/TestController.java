package org.example.controller;

import org.example.controller.payload.request.GetQuestionRequest;
import org.example.controller.payload.request.PostTestResultRequest;
import org.example.controller.payload.response.GetQuestionsResponse;
import org.example.controller.payload.request.PostAnswerRequest;
import org.example.controller.payload.response.GetResultResponse;
import org.example.service.impl.TestService;
import org.example.validation.DuolingoRuntimeException;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duolingo/tests")
@RequiredArgsConstructor
@Slf4j
public class TestController {

  private final TestService testService;

  @PostMapping(path = "/questions")
  public ResponseEntity<GetQuestionsResponse> findQuestionsForTest(@RequestBody @NonNull final
      GetQuestionRequest request) throws DuolingoRuntimeException {
    return ResponseEntity.ok(testService.findQuestionsForTest(request));
  }

  @PostMapping(path = "/answers")
  public ResponseEntity<GetResultResponse> saveResultForTest(@RequestBody @NonNull final
  PostTestResultRequest request) throws DuolingoRuntimeException {
    return ResponseEntity.ok(testService.saveResultForTest(request));
  }

}
