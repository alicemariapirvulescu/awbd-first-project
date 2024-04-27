package org.example.repository;

import org.example.domain.entities.Language;
import org.example.domain.entities.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findAllByLanguage(Language language);

  Question findByQuestion(String question);
}