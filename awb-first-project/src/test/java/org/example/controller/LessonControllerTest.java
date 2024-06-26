package org.example.controller;
import org.example.controller.LessonController;
import org.example.controller.payload.response.GetLessonsResponse;
import org.example.service.impl.LessonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class LessonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LessonService lessonService;

    @InjectMocks
    private LessonController lessonController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(lessonController).build();
    }

    @Test
    public void testGetLessons() throws Exception {
        String languageName = "Spanish";
        String jwtMock = "adsafsa";
        GetLessonsResponse mockResponse = new GetLessonsResponse(Collections.emptyList()); // Assuming you have a default constructor
        when(lessonService.getLessons(languageName, jwtMock)).thenReturn(mockResponse);

        mockMvc.perform(get("/duolingo/lesson/" + languageName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(lessonService).getLessons(languageName, null);
    }
}
