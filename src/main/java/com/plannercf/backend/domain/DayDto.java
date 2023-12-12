package com.plannercf.backend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@Getter
public class DayDto extends BaseEntityDto{
    private LocalDate date;
    private String dayName;

    public DayDto(LocalDate date) {
        this.date = date;
    }

    public DayDto(Long id, LocalDate date, String dayName) {
        super(id);
        this.date = date;
        this.dayName = dayName;
    }
}
