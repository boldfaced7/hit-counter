package com.example.hitcounter.repository;

import com.example.hitcounter.domain.DailyCount;
import com.example.hitcounter.domain.Url;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DailyCountRepository extends JpaRepository<DailyCount, Long> {
    @EntityGraph(attributePaths = {"url"})
    List<DailyCount> findAllByUrlAndDateAfter(Url url, LocalDate date);

    @EntityGraph(attributePaths = {"url"})
    Optional<DailyCount> findByUrlAndDate(Url url, LocalDate date);
}
