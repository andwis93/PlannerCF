package com.plannercf.backend.service;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.mapper.DayMapper;
import com.plannercf.backend.repository.DayRepository;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DayService {
    private final DayRepository repository;
    private final DayMapper mapper;

    @Autowired
    public DayService(final DayRepository repository, final DayMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public boolean isDayExistByDate(LocalDate date) {
        return repository.existsDayByDate(date);
    }

    public boolean createDay(LocalDate date) {
        if (!isDayExistByDate(date)) {
            repository.save(new Day(date));
            return true;
        } else {
            return false;
        }
    }

    public void createDays(int dayQty, LocalDate startDate) {
        int counter = 0;
        long days = 0;
        while (counter < dayQty) {
           LocalDate date = startDate.plusDays(days);
            if (createDay(date)) {
                counter++;
            }
            days++;
        }
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

    public void changeDay(DayDto dayDto) throws RecordNotExistsException {
        if (isDayExistByDate(dayDto.getDate())) {
            Day day = getDayByDate(dayDto.getDate());
            day.setDayName(dayDto.getDayName());
            repository.save(day);
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