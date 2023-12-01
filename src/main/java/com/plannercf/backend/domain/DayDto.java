package com.plannercf.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DayDto {
    private Long id;
    private LocalDate date;
    private String dayName;

    public DayDto(LocalDate date) {
        this.date = date;
    }
}
