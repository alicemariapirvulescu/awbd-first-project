package org.example.controller;

import org.example.controller.payload.response.GetLanguagesResponse;
import org.example.service.impl.LanguageService;
import org.example.validation.DuolingoRuntimeException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/duolingo/language")
@Slf4j
@RequiredArgsConstructor
public class LanguagesController {

  private final LanguageService languageService;

  @GetMapping()
  public ResponseEntity<GetLanguagesResponse> getUserLanguages() throws DuolingoRuntimeException {
    return ResponseEntity.ok(languageService.getUserLanguages());
  }

  @GetMapping(path = "/all")
  public ResponseEntity<GetLanguagesResponse> getLanguages() throws DuolingoRuntimeException {
    return ResponseEntity.ok(languageService.getLanguages());
  }

  @PostMapping(path = "/enroll/{languageName}")
  public ResponseEntity<GetLanguagesResponse> addLanguage(@PathVariable  @NonNull String languageName) throws DuolingoRuntimeException {
    return ResponseEntity.ok(languageService.addLanguageToUser(languageName));
  }

}
