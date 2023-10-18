package com.example.hitcounter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class WeeklyCountDto {
    Map<LocalDate, Long> dailyCounts;
}
