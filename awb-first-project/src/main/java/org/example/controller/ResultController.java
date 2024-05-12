package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/duolingo/results")
@RequiredArgsConstructor
@Slf4j
public class ResultController {

  private final ResultService resultService;

  @GetMapping("/{languageName}")
  public String getResults(@PathVariable String languageName, HttpServletRequest request, Model model) throws DuolingoRuntimeException {
    Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
    GetResultsResponse results = resultService.findResultsForLanguage(languageName, jwtCookie.getValue());
    model.addAttribute("results", results.getResults());
    model.addAttribute("language", languageName);
    return "resultsView"; // Name of the Thymeleaf template
  }
}