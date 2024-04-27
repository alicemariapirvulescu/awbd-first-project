package org.example.repository;

import org.example.domain.entities.Language;
import org.example.domain.entities.Result;
import org.example.domain.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{


  List<Result> findAllByLanguageAndUserOrderByTimestamp(Language language, User user);


}
