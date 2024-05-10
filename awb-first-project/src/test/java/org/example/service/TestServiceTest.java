//package org.example.service;
//
//import org.example.controller.payload.request.GetQuestionRequest;
//import org.example.controller.payload.request.PostAnswerRequest;
//import org.example.controller.payload.request.PostTestResultRequest;
//import org.example.controller.payload.response.GetResultResponse;
//import org.example.domain.entities.Language;
//import org.example.domain.entities.Question;
//import org.example.domain.entities.Result;
//import org.example.domain.entities.User;
//import org.example.repository.LanguageRepository;
//import org.example.repository.QuestionRepository;
//import org.example.repository.ResultRepository;
//import org.example.service.impl.TestService;
//import org.example.service.impl.UserService;
//import org.example.validation.DuolingoRuntimeException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//public class TestServiceTest {
//
//    @Mock
//    private QuestionRepository questionRepository;
//
//    @Mock
//    private LanguageRepository languageRepository;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private ResultRepository resultRepository;
//
//    @InjectMocks
//    private TestService testService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void findQuestionsForTest_LanguageNotFound_ThrowsException() {
//        GetQuestionRequest request = new GetQuestionRequest("Spanish");
//        when(languageRepository.findByName("Spanish")).thenReturn(Optional.empty());
//
//        assertThrows(DuolingoRuntimeException.class, () -> {
//            testService.findQuestionsForTest(request);
//        });
//    }
//
//    @Test
//    public void saveResultForTest_LanguageDoesNotExist_ThrowsException() {
//        PostTestResultRequest request = new PostTestResultRequest(Collections.emptyList(), "Spanish");
//        when(languageRepository.findByName("Spanish")).thenReturn(Optional.empty());
//
//        assertThrows(DuolingoRuntimeException.class, () -> {
//            testService.saveResultForTest(request);
//        });
//    }
//
//    @Test
//    public void saveResultForTest_Successful_ReturnsResultResponse() throws DuolingoRuntimeException {
//        // Assume necessary setup for PostTestResultRequest, Language, User, and Result
//        PostAnswerRequest pan1 = new PostAnswerRequest("Translate to Spanish: Good morning.", "Buenos dias");
//        PostAnswerRequest pan2 = new PostAnswerRequest("Translate to Spanish: Good evening.", "Buenas tardes");
//        PostTestResultRequest request = new PostTestResultRequest(List.of(pan1, pan2), "Spanish");
//        Language language = new Language(1L, "Spanish", Collections.emptyList(), Collections.emptyList());
//        User user = new User();
//        Question question = Question.builder().question("Translate to Spanish: Good evening.").answer("Buenas tardes").build();
//        Result result = Result.builder().grade(10D).language(language).build();
//
//        when(languageRepository.findByName("Spanish")).thenReturn(Optional.of(language));
//        when(userService.getCurrentUser()).thenReturn(Optional.of(user));
//        when(questionRepository.findByQuestion(any())).thenReturn(question);
//        when(resultRepository.save(any(Result.class))).thenReturn(result);
//
//        GetResultResponse response = testService.saveResultForTest(request);
//
//        assertNotNull(response);
//        assertEquals(response.grade(), 10D);
//    }
//}
