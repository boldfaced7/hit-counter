package com.example.hitcounter.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class DailyCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_count_id")
    private Long id;
    private Long dailyCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Url url;

    private Long addDailyCount() {
        return ++dailyCount;
    }
}
