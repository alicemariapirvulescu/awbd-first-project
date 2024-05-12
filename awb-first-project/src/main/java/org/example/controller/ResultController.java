package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.domain.entities.Result;
import org.example.service.impl.ResultService;
import org.example.validation.DuolingoRuntimeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                           @RequestParam(defaultValue = "timestamp") String sort,
                           @RequestParam(defaultValue = "desc") String dir,
                           HttpServletRequest request, Model model) throws DuolingoRuntimeException {
    Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
    Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.fromString(dir), sort));

    Page<Result> resultPage = resultService.findResultsForLanguage(languageName, jwtCookie.getValue(), pageable);
    model.addAttribute("results", resultPage.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("sort", sort);
    model.addAttribute("dir", dir);
    model.addAttribute("totalPages", resultPage.getTotalPages());
    model.addAttribute("language", languageName);
    return "resultsView";
  }

}