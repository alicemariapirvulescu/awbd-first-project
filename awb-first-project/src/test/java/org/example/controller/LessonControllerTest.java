package org.example.controller;

import jakarta.servlet.http.Cookie;
import org.example.controller.payload.response.GetLessonsResponse;
import org.example.service.impl.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LessonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LessonService lessonService;

    @InjectMocks
    private LessonController lessonController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(lessonController).build();
    }

    @Test
    public void testGetLessons() throws Exception {
        String languageName = "Spanish";
        String mockJWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzE1NTE2ODUwLCJleHAiOjE3MTU2MDMyNTB9.XUxmgJVAqNux_eyFROYqr99WeZ0syBsPUQWDFVrszxSb7BkX0iJk5BbsN0cJfDmG_EQoCSMqulbt9m_kn9jC3w";
        GetLessonsResponse mockResponse = new GetLessonsResponse(Collections.emptyList());
        when(lessonService.getLessons(languageName, mockJWT)).thenReturn(mockResponse);

        mockMvc.perform(get("/duolingo/lesson/" + languageName)
                        .cookie(new Cookie("JWT", mockJWT))
                        .param("languageName", languageName))
                .andExpect(status().isOk())
                .andExpect(view().name("lesson"))
                .andExpect(model().attributeExists("language"))
                .andExpect(model().attributeExists("lesson"));

        verify(lessonService).getLessons(languageName, mockJWT);
    }
}
