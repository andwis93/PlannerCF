package com.plannercf.backend.repository;

import com.plannercf.backend.domain.Day;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional
public interface DayRepository extends CrudRepository<Day, Long> {
    Optional<Day> findDayByDate(LocalDate date);

    void deleteByDate(LocalDate date);
}
