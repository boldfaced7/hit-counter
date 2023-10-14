package com.example.hitcounter.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Url extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private Long id;
    private String url;
    private Long totalCount;

    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL)
    private List<DailyCount> dailyCounts = new ArrayList<>();

    @OneToMany(mappedBy = "url", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    public Long addTotalCount() {
        return ++totalCount;
    }
}
