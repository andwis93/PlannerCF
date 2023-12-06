package com.plannercf.backend.mapper;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayMapper {

    public DayDto mapToDayDto(Day day){
        return new DayDto(
                day.getId(),
                day.getDate(),
                day.getDayName()
        );
    }

    public Day mapToDay(DayDto dayDto) {
        return new Day(dayDto.getDate());
    }

    public List<DayDto> mapToDtoList(List<Day> days) {
        return days.stream().map(this::mapToDayDto).collect(Collectors.toList());
    }
}