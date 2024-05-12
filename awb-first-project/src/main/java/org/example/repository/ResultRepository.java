package org.example.repository;

import org.example.domain.entities.Language;
import org.example.domain.entities.Result;
import org.example.domain.entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long>{

  Page<Result> findAllByLanguageAndUser(Language language, User user, Pageable pageable);
}
