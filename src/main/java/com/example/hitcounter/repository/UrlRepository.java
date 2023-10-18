package com.example.hitcounter.repository;

import com.example.hitcounter.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByUrl(String url);
}
