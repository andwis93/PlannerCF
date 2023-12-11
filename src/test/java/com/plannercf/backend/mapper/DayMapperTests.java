package com.plannercf.backend.mapper;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DisplayName("DayMapperTests.class Test Suite")
public class DayMapperTests {
    @Autowired
    private DayMapper mapper;

    @Test
    void mapToDayDtoTest() {
        //Given
        Day day = new Day(LocalDate.of(2023,12,23));

        //When
        DayDto dayDto = mapper.mapToDayDto(day);

        //Then
        assertEquals(LocalDate.of(2023,12,23), dayDto.getDate());
    }
}
