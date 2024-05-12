package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.controller.payload.request.GetQuestionRequest;
import org.example.controller.payload.request.PostTestResultRequest;
import org.example.controller.payload.request.SaveResultRequest;
import org.example.controller.payload.response.GetLessonsResponse;
import org.example.controller.payload.response.GetQuestionsResponse;
import org.example.controller.payload.request.PostAnswerRequest;
import org.example.controller.payload.response.GetResultResponse;
import org.example.service.impl.TestService;
import org.example.validation.DuolingoRuntimeException;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/duolingo/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final TestService testService;

    @GetMapping("/questions")
    public String showTestForm(Model model, @RequestParam String languageName) throws DuolingoRuntimeException {
        GetQuestionsResponse questionsResponse = testService.findQuestionsForTest(new GetQuestionRequest(languageName));
        var answers = questionsResponse.questions().stream()
                .map(q -> new PostAnswerRequest(q.question(), ""))
                .collect(Collectors.toList());

        PostTestResultRequest resultRequest = new PostTestResultRequest(answers, languageName);

        model.addAttribute("questions", questionsResponse.questions());
        model.addAttribute("resultRequest", resultRequest);
        return "testForm";
    }

    // Handle submission of answers and display results
    @PostMapping("/answers")
    public String submitAnswers(@ModelAttribute PostTestResultRequest resultRequest, HttpServletRequest request, Model model) throws DuolingoRuntimeException {
        Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
        GetResultResponse result = testService.saveResultForTest(resultRequest, jwtCookie.getValue());
        model.addAttribute("result", result);
        return "testResult";
    }

}
