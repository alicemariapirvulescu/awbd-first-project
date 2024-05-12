package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.controller.payload.response.GetLessonResponse;
import org.example.controller.payload.response.GetLessonsResponse;
import org.example.domain.entities.Lesson;
import org.example.repository.LanguageRepository;
import org.example.repository.LessonRepository;
import org.example.validation.DuolingoRuntimeException;

import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonService {

    private final UserService userService;
    private final LessonRepository lessonRepository;
    private final LanguageRepository languageRepository;

    public GetLessonsResponse getLessons(final String languageName, String jwt) throws DuolingoRuntimeException {

        val currentUser = userService.getCurrentUser(jwt).orElseThrow(
                () -> new DuolingoRuntimeException(403, "User not found")
        );
        ;
        val userLanguages = currentUser.getLanguages();
        if (userLanguages.isEmpty()) {
            log.debug("User not enrolled in any languages");
            throw new DuolingoRuntimeException(404, "User not enrolled in any languages");
        }
        val language = languageRepository.findByName(languageName).orElseThrow(
                () -> new DuolingoRuntimeException(400, "Language does not exist")
        );
        if (!userLanguages.contains(language)) {
            log.debug("User is not enrolled in the language {}", language);
            throw new DuolingoRuntimeException(400, "User not enrolled in this language");
        }
        val lessons = lessonRepository.findAllByLanguage(language)
                .stream()
                .map(lesson -> new GetLessonResponse(lesson.getTitle(), lesson.getText()))
                .toList();
        return new GetLessonsResponse(lessons);
    }

}
