package org.example.controller;

import jakarta.servlet.http.Cookie;
import org.example.controller.payload.request.GetQuestionRequest;
import org.example.controller.payload.request.PostTestResultRequest;
import org.example.controller.payload.response.GetQuestionsResponse;
import org.example.controller.payload.response.GetResultResponse;
import org.example.service.impl.TestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TestService testService;

    @InjectMocks
    private TestController testController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(testController).build();
    }

    @Test
    public void testShowTestForm() throws Exception {
        String languageName = "Spanish";
        GetQuestionsResponse questionsResponse = new GetQuestionsResponse(Collections.emptyList());
        when(testService.findQuestionsForTest(new GetQuestionRequest(languageName))).thenReturn(questionsResponse);

        mockMvc.perform(get("/duolingo/test/questions")
                        .param("languageName", languageName))
                .andExpect(status().isOk())
                .andExpect(view().name("testForm"))
                .andExpect(model().attributeExists("questions"))
                .andExpect(model().attributeExists("resultRequest"));

        verify(testService).findQuestionsForTest(new GetQuestionRequest(languageName));
    }

    @Test
    public void testSubmitAnswers() throws Exception {
        String languageName = "Spanish";
        String mockJWT = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNzE1NTE2ODUwLCJleHAiOjE3MTU2MDMyNTB9.XUxmgJVAqNux_eyFROYqr99WeZ0syBsPUQWDFVrszxSb7BkX0iJk5BbsN0cJfDmG_EQoCSMqulbt9m_kn9jC3w";

        PostTestResultRequest resultRequest = new PostTestResultRequest(Collections.emptyList(), languageName);
        GetResultResponse resultResponse = new GetResultResponse(20.0, "Spanish", "2024-05-12T12:00:00");
        when(testService.saveResultForTest(any(), anyString())).thenReturn(resultResponse);

        mockMvc.perform(post("/duolingo/test/answers")
                        .cookie(new Cookie("JWT", mockJWT))
                        .flashAttr("resultRequest", resultRequest))
                .andExpect(status().isOk())
                .andExpect(view().name("testResult"));

        verify(testService).saveResultForTest(any(), anyString());
    }


}
