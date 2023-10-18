package com.example.hitcounter.service;

import com.example.hitcounter.domain.DailyCount;
import com.example.hitcounter.domain.Url;
import com.example.hitcounter.domain.Visit;
import com.example.hitcounter.repository.DailyCountRepository;
import com.example.hitcounter.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Transactional
@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final DailyCountRepository dailyCountRepository;

    public Url saveUrl(String url) {
        return urlRepository.save(Url.createUrl(url));
    }

    @Transactional(readOnly = true)
    public Url findByUrl(String url) {
        return urlRepository.findByUrl(url)
                .orElseGet(() -> saveUrl(url));
    }

    public void increaseCount(String url) {
        Url foundUrl = findByUrl(url);
        DailyCount dailyCount = addDailyCountAndVisit(foundUrl);
        foundUrl.addTotalCount();
        dailyCount.addDailyCount();
    }

    @Transactional(readOnly = true)
    public Map<LocalDate, Long> getWeeklyCounts(String url) {
        Url foundUrl = findByUrl(url);

        List<DailyCount> dailyCountList = dailyCountRepository
                .findAllByUrlAndDateAfter(foundUrl, LocalDate.now().minusDays(7));

        Map<LocalDate, Long> countMap = dailyCountList.stream()
                .collect(Collectors.toMap(DailyCount::getDate, DailyCount::getDailyCount));

        IntStream.range(0, 7)
                .mapToObj(i -> LocalDate.now().minusDays(i))
                .filter(date -> countMap.get(date) == null)
                .map(date -> countMap.put(date, 0L))
                .count();

        return countMap;
    }

    private DailyCount addDailyCountAndVisit(Url url) {
        DailyCount dailyCount = dailyCountRepository
                .findByUrlAndDate(url, LocalDate.now())
                .orElseGet(() ->
                    DailyCount.createDailyCount(url, LocalDate.now())
                );
        dailyCount.addUrl(url);

        Visit visit = Visit.createVisit(url);
        visit.addUrl(url);

        return dailyCount;
    }
}
