package com.example.hitcounter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TodayAndTotalCountDto {
    private Long today;
    private Long total;
}
