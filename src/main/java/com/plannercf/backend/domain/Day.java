package com.plannercf.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@NamedQuery(
        name = "Day.getOldDays",
        query = "FROM Day WHERE date < :REFERENCE_DATE"
)

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return Objects.equals(date, day.date) && Objects.equals(dayName, day.dayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, dayName);
    }
}
