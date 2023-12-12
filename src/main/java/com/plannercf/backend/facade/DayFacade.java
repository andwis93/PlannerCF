package com.plannercf.backend.facade;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.service.DayService;
import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DayFacade {
    private final DayService dayService;

    @Autowired
    public DayFacade(final DayService dayService) {
        this.dayService = dayService;
    }

    public Day saveDay(LocalDate date) throws RecordAlreadyExistsException {
        return dayService.saveDay(date);
    }

    public List<Day> createDays(int dayQty, LocalDate startDate) throws RecordAlreadyExistsException {
      return dayService.createDays(dayQty, startDate);
    }

    public Day getDayByDate(LocalDate date) throws RecordNotExistsException {
        return dayService.getDayByDate(date);
    }

    public List<Day> getAllDays() {
        return dayService.getAllDays();
    }

    public List<Day> getLatestDay() {
        return dayService.getLatestDay();
    }

    public void deleteDay(LocalDate date) {
        dayService.deleteDay(date);
    }
    public void deleteAll() {
        dayService.deleteAll();
    }

    public void deleteOldDates(LocalDate date) throws RecordNotExistsException {
        dayService.deleteOldDays(date);
    }
}
