package com.example.hitcounter.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Visit extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_id")
    private Url url;

    private Visit(Url url) {
        this.url = url;
    }

    public static Visit createVisit(Url url) {
        Visit visit = new Visit();
        visit.addUrl(url);
        return visit;
    }

    public void addUrl(Url url) {
        this.url = url;
        url.getVisits().add(this);
    }

}
