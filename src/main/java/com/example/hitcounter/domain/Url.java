package com.example.hitcounter.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
@Entity
public class Url extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Long id;
    private String url;
    private Long totalCount = 0L;

    @OneToMany(mappedBy = "url")
    private Map<LocalDate, DailyCount> dailyCounts = new HashMap<>();

    @OneToMany(mappedBy = "url")
    private List<Visit> visits = new ArrayList<>();

    public Long addTotalCount() {
        return ++totalCount;
    }

    public Long getTodayCount() {
        LocalDate now = LocalDate.now();
        DailyCount dailyCount = getDailyCounts().get(now);
        if (dailyCount == null) {
            return 0L;
        }

        return dailyCount.getDailyCount();
    }

    private Url(String url) {
        this.url = url;
    }

    public static Url createUrl(String url) {
        Url savedUrl = new Url(url);
        return savedUrl;
    }

}
