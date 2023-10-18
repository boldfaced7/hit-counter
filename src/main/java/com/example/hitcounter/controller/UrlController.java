package com.example.hitcounter.controller;

import com.example.hitcounter.domain.Url;
import com.example.hitcounter.dto.TodayAndTotalCountDto;
import com.example.hitcounter.dto.WeeklyCountDto;
import com.example.hitcounter.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class UrlController {

    private final UrlService urlService;

    // url의 카운트를 증가하는 api
    @GetMapping("/incr")
    public ResponseEntity<Void> increaseCount(@RequestParam("url") String url) {
        urlService.increaseCount(url);
        return ResponseEntity.ok().build();
    }

    // 일간 조회수와 누적 조회수를 응답하는 api
    @GetMapping("/counts")
    public  ResponseEntity<TodayAndTotalCountDto> getTodayAndTotalCount(@RequestParam("url") String url) {
        Url savedUrl = urlService.findByUrl(url);
        TodayAndTotalCountDto dto = new TodayAndTotalCountDto(
                savedUrl.getTodayCount(), savedUrl.getTotalCount());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // 7일간의 조회수 통계 데이터를 응답하는 api
    @GetMapping("/weekly")
    public ResponseEntity<WeeklyCountDto> getWeeklyCount(@RequestParam("url") String url) {
        WeeklyCountDto dto = new WeeklyCountDto(urlService.getWeeklyCounts(url));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
