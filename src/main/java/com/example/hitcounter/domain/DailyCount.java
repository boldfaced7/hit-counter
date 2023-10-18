package com.example.hitcounter.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class DailyCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_count_id")
    private Long id;
    private Long dailyCount = 0L;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    public Long addDailyCount() {
        return ++dailyCount;
    }

    private DailyCount(LocalDate date) {
        this.date = date;
    }

    public static DailyCount createDailyCount(Url url, LocalDate date) {
        DailyCount dailyCount = new DailyCount(date);
        dailyCount.addUrl(url);
        return dailyCount;
    }

    public void addUrl(Url url) {
        this.url = url;
        url.getDailyCounts().put(LocalDate.now(), this);
    }
}
