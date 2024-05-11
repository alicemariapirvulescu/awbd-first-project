package org.example.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.example.controller.ResultController;
import org.example.controller.payload.response.GetResultResponse;
import org.example.controller.payload.response.GetResultsResponse;
import org.example.service.impl.ResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

public class ResultControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ResultService resultService;

    @InjectMocks
    private ResultController resultController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(resultController).build();
    }

    @Test
    public void testGetResults() throws Exception {
        String languageName = "Spanish";
        String jwtMock = "adsafsa";
        GetResultsResponse mockResponse = new GetResultsResponse(Collections.emptyList() );
        when(resultService.findResultsForLanguage(languageName, jwtMock)).thenReturn(mockResponse);

        mockMvc.perform(get("/duolingo/results/" + languageName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(resultService).findResultsForLanguage(languageName, jwtMock);
    }

    @Test
    public void testGetResultDetails() throws Exception {
        Long resultId = 1L;
        GetResultResponse mockResponse = new GetResultResponse(5D, "Spanish", "21/10/2023");
        when(resultService.getResultDetails(resultId)).thenReturn(mockResponse);

        mockMvc.perform(get("/duolingo/results/get-result-details/" + resultId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(resultService).getResultDetails(resultId);
    }
}