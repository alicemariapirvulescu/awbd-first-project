package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.controller.payload.request.SaveResultRequest;
import org.example.controller.payload.response.GetResultResponse;
import org.example.controller.payload.response.GetResultsResponse;
import org.example.domain.entities.Result;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
  public String getResults(@PathVariable String languageName,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "timestamp") String sortBy,
                           @RequestParam(defaultValue = "desc") String dir,
                           HttpServletRequest request, Model model) throws DuolingoRuntimeException {
    Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
    Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.fromString(dir), sortBy));

    Page<Result> resultPage = resultService.findResultsForLanguage(languageName, jwtCookie.getValue(), pageable);
    model.addAttribute("results", resultPage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("sort", sortBy);
    model.addAttribute("dir", dir);
    model.addAttribute("totalPages", resultPage.getTotalPages());
    model.addAttribute("language", languageName);
    return "resultsView";
  }

}