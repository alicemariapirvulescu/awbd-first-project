package org.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.val;
import org.example.controller.payload.response.GetLanguageResponse;
import org.example.domain.entities.Language;
import org.example.domain.entities.User;
import org.example.repository.LanguageRepository;
import org.example.service.impl.LanguageService;
import org.example.service.impl.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class LanguageServiceTest {


  public static final String MOCK_LANGUAGE = "MOCK_LANGUAGE";
  @Mock
  private LanguageRepository languageRepository;

  @Mock
  private UserService userService;

  @InjectMocks
  private LanguageService languageService;


  @Test
  void getLanguagesTest() {
    val languagesList = List.of(getOneLanguage().get());
    val expectedResponse = languagesList
        .stream().map(l -> new GetLanguageResponse(l.getName())).collect(
        Collectors.toList());
    when(languageRepository.findAll()).thenReturn(languagesList);
    val response = languageService.getLanguages();
    val actualResponse = response.languages();
    Assertions.assertIterableEquals(expectedResponse,actualResponse);
  }



  private Optional<Language> getOneLanguage(){
    val language = new Language();
    language.setName(MOCK_LANGUAGE);
    return Optional.of(language);
  }
  private Optional<User> getOneUser(){
    return Optional.of(new User());
  }

}
