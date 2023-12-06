package com.plannercf.backend.repository;

import com.plannercf.backend.domain.Day;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface DayRepository extends CrudRepository<Day, Long> {
    Optional<Day> findDayByDate(LocalDate date);

    void deleteByDate(LocalDate date);

    boolean existsDayByDate(LocalDate date);

    @Nonnull
    List<Day> findAll();

    @Query()
    List<Day> getOldDays(@Param("REFERENCE_DATE") LocalDate date);

    @Query(nativeQuery = true)
    List<Day> getLatestDate();
}
