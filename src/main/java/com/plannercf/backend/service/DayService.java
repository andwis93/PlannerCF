package com.plannercf.backend.service;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.repository.DayRepository;
import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DayService {
    private final DayRepository repository;

    @Autowired
    public DayService(final DayRepository repository) {
        this.repository = repository;
    }

    public boolean isDayExistByDate(LocalDate date) {
        return repository.existsDayByDate(date);
    }

    public Day saveDay(LocalDate date) throws RecordAlreadyExistsException {
        if (!isDayExistByDate(date)) {
         Day day = repository.save(new Day(date));
            return day;
        } else {
            throw new RecordAlreadyExistsException("DayService.class -> createDay: Day with date: " + date + " already exists!");
        }
    }

    public List<Day> createDays(int dayQty, LocalDate startDate) throws RecordAlreadyExistsException {
        List<Day> days = new ArrayList<>();
        long dayCount = 0;
        while (days.size() < dayQty) {
           LocalDate date = startDate.plusDays(dayCount);
            if (!isDayExistByDate(date)) {
                Day day = saveDay(date);
                days.add(day);
            }
            dayCount++;
        }
        return days;
    }

    public Day getDayByDate(LocalDate date) throws RecordNotExistsException {
        if (isDayExistByDate(date)) {
            return repository.findDayByDate(date).orElseThrow(RecordNotExistsException::new);
        } else {
            return new Day();
        }
    }

    public List<Day> getAllDays() {
        return repository.findAll();
    }

    public List<Day> getLatestDay() {
        return repository.getLatestDate();
    }

    public Day changeDay(DayDto dayDto) throws RecordNotExistsException {
        if (isDayExistByDate(dayDto.getDate())) {
            Day day = getDayByDate(dayDto.getDate());
            day.setDayName(dayDto.getDayName());
          return repository.save(day);
        } else {
            throw new RecordNotExistsException();
        }
    }

    public void deleteDay(LocalDate date) {
        repository.deleteByDate(date);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteOldDays(LocalDate date) throws RecordNotExistsException {
        try {
            List<Day> days = repository.getOldDays(date);
            repository.deleteAll(days);
        } catch (Exception er) {
            throw new RecordNotExistsException();
        }
    }
}
