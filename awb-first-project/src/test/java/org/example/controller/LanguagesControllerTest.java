package org.example.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import jakarta.servlet.http.Cookie;
import org.example.controller.payload.response.GetLanguagesResponse;
import org.example.service.impl.LanguageService;
import org.example.validation.DuolingoRuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class LanguagesControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LanguageService languageService;

    @InjectMocks
    private LanguagesController languageController;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(languageController).build();
    }

    @Test
    void testEnroll() throws Exception {
        mockMvc.perform(get("/duolingo/language/enroll/spanish"))
                .andExpect(status().isOk())
                .andExpect(view().name("enroll"));
    }

    @Test
    void testAddLanguage() throws Exception {
        // Setup
        MockHttpServletRequest request = new MockHttpServletRequest();
        Model mockModel = mock(Model.class);
        RedirectAttributes mockRedirectAttributes = mock(RedirectAttributes.class);
        String jwtString ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzE1NTE2ODUwLCJleHAiOjE3MTU2MDMyNTB9.XUxmgJVAqNux_eyFROYqr99WeZ0syBsPUQWDFVrszxSb7BkX0iJk5BbsN0cJfDmG_EQoCSMqulbt9m_kn9jC3w";
        Cookie jwtCookie = new Cookie("JWT", jwtString);
        request.setCookies(jwtCookie);

        when(languageService.addLanguageToUser("spanish", jwtString)).thenReturn(new GetLanguagesResponse(new ArrayList<>()));

        mockMvc.perform(post("/duolingo/language/enroll/spanish")
                .cookie(jwtCookie))
                .andExpect(status().isOk())
                .andExpect(view().name("mainForm"));

        verify(languageService).addLanguageToUser("spanish", jwtString);
    }

}