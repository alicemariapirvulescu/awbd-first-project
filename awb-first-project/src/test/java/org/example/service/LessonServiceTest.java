//package org.example.service;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.example.controller.payload.response.GetLessonsResponse;
//import org.example.domain.entities.Language;
//import org.example.domain.entities.Lesson;
//import org.example.domain.entities.User;
//import org.example.repository.LanguageRepository;
//import org.example.repository.LessonRepository;
//import org.example.service.impl.LessonService;
//import org.example.service.impl.UserService;
//import org.example.validation.DuolingoRuntimeException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//import java.util.Optional;
//
//public class LessonServiceTest {
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private LessonRepository lessonRepository;
//
//    @Mock
//    private LanguageRepository languageRepository;
//
//    @InjectMocks
//    private LessonService lessonService;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void getLessons_UserNotFound_ThrowsException() {
//        String languageName = "Spanish";
//        when(userService.getCurrentUser()).thenReturn(Optional.empty());
//
//        assertThrows(DuolingoRuntimeException.class, () -> {
//            lessonService.getLessons(languageName);
//        });
//    }
//
//    @Test
//    public void getLessons_UserNotEnrolledInAnyLanguages_ThrowsException() {
//        String languageName = "Spanish";
//        User mockUser = mock(User.class);
//        when(userService.getCurrentUser()).thenReturn(Optional.of(mockUser));
//        when(mockUser.getLanguages()).thenReturn(List.of());
//        assertThrows(DuolingoRuntimeException.class, () -> {
//            lessonService.getLessons(languageName);
//        });
//    }
//
//    @Test
//    public void getLessons_Successful_ReturnsLessons() throws DuolingoRuntimeException {
//        String languageName = "es";
//        User mockUser = mock(User.class);
//        Language mockLanguage = Language.builder().name(languageName).id(1L).build();
//        Lesson mockLesson = Lesson.builder().id(1L).title("Greetings").text("Hola means Hi").build();
//
//        when(userService.getCurrentUser()).thenReturn(Optional.of(mockUser));
//        when(mockUser.getLanguages()).thenReturn(List.of(mockLanguage));
//        when(languageRepository.findByName(languageName)).thenReturn(Optional.of(mockLanguage));
//        when(lessonRepository.findAllByLanguage(mockLanguage)).thenReturn(List.of(mockLesson));
//
//        GetLessonsResponse response = lessonService.getLessons(languageName);
//        assertNotNull(response);
//        assertFalse(response.lessons().isEmpty());
//        assertEquals("Greetings", response.lessons().get(0).title());
//        assertEquals("Hola means Hi", response.lessons().get(0).text());
//    }
//}
