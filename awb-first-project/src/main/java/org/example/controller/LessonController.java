package org.example.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.payload.response.GetLessonsResponse;
import org.example.service.impl.LessonService;
import org.example.validation.DuolingoRuntimeException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/duolingo/lesson")
@Slf4j
@RequiredArgsConstructor
public class LessonController {

  private final LessonService lessonService;

  @GetMapping(path = "/{languageName}")
  public String getLessons(Model model, @PathVariable @NonNull String languageName, HttpServletRequest request, RedirectAttributes redirectAttributes)  throws DuolingoRuntimeException {
      Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
      GetLessonsResponse response = lessonService.getLessons(languageName, jwtCookie.getValue());
      model.addAttribute("language", languageName);
      model.addAttribute("lesson", response);
      return "lesson";
  }

}
