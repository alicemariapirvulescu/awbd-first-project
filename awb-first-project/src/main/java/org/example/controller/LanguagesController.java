package org.example.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.example.controller.payload.response.GetLanguagesResponse;
import org.example.service.impl.LanguageService;
import org.example.validation.DuolingoRuntimeException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/duolingo/language")
@Slf4j
@RequiredArgsConstructor
public class LanguagesController {

  private final LanguageService languageService;

//  @GetMapping()
//  public ResponseEntity<GetLanguagesResponse> getUserLanguages() throws DuolingoRuntimeException {
//    return ResponseEntity.ok(languageService.getUserLanguages());
//  }

  @GetMapping(path = "/all")
  public String getLanguages(Model model) throws DuolingoRuntimeException {
    model.addAttribute("languages", languageService.getLanguages().languages() );

    return "allLanguages";
  }

  @GetMapping(path = "/enroll/{languageName}")
  public String enroll(Model model) {
    return "enroll";
  }
  @PostMapping("/enroll/{languageName}")
  public String addLanguage(@PathVariable String languageName, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
    try {
      Cookie jwtCookie = WebUtils.getCookie(request, "JWT");
      GetLanguagesResponse response = languageService.addLanguageToUser(languageName, jwtCookie.getValue());
      model.addAttribute("language", response);
      return "enroll";  // Redirect to a view page displaying success and language details
    } catch (DuolingoRuntimeException ex) {
      redirectAttributes.addFlashAttribute("error", "An error occurred: " + ex.getMessage());
      return "redirect:/duolingo/language/all";  // Redirect back to the form if there's an exception
    }
  }



}
