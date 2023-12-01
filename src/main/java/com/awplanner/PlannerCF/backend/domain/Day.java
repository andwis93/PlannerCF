package com.awplanner.PlannerCF.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DAYS")
public class Day extends BaseEntity {
    @Column(name = "DATE")
    private LocalDate date;
    @Column(name = "DAY_OF_THE_WEEK")
    private String dayName;

    public Day(LocalDate date) {
        this.date = date;
        this.dayName = date.getDayOfWeek().toString();
    }
}
