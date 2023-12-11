package com.plannercf.backend.service;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import com.plannercf.backend.service.exception.TestNotCleaned;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DisplayName("DayService.class Test Suite")
public class DayServiceTests {
    @Autowired
    private DayService service;

    @Test
    void isDayExistsByDateTest() throws TestNotCleaned, RecordAlreadyExistsException {
        //Given & When
        service.saveDay(LocalDate.of(2023,12,24));

        //Then
        assertTrue(service.isDayExistByDate(LocalDate.of(2023,12,24)));

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("isDayExistsByDateTest NOT cleaned");
        }
    }

    @Test
    void saveDayTest() throws RecordNotExistsException, TestNotCleaned, RecordAlreadyExistsException {
        //Given
        service.saveDay(LocalDate.of(2023,12,24));

        //When
        Day dayRetrieved = service.getDayByDate(LocalDate.parse("2023-12-24"));

        //Then
        assertEquals(LocalDate.of(2023,12,24), dayRetrieved.getDate());

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("createDayTest NOT cleaned");
        }
    }

    @Test
    void createDaysTest() throws TestNotCleaned, RecordAlreadyExistsException {
        //Given
        service.saveDay(LocalDate.of(2023,12,24));
        service.saveDay(LocalDate.of(2023,12,26));

        //When
        service.createDays(2, LocalDate.of(2023,12,24));
        List<Day> days = service.getAllDays();

        //Then
        assertEquals(4, days.size());
        assertEquals(LocalDate.of(2023,12,25), days.get(2).getDate());
        assertEquals(LocalDate.of(2023,12,27), days.get(3).getDate());

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("createDaysTest NOT cleaned");
        }
    }

    @Test
    void getLatestDayTest() throws TestNotCleaned, RecordAlreadyExistsException {
        //Given
        service.createDays(3, LocalDate.of(2023,12,24));

        //When
        List<Day> days = service.getLatestDay();

        //Then
        assertEquals(LocalDate.of(2023,12,26), days.get(0).getDate());

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("getLatestDayTest NOT cleaned");
        }
    }

    @Test
    void deleteDayTest() throws TestNotCleaned, RecordAlreadyExistsException {
        //Given
        service.saveDay(LocalDate.of(2023,12,24));
        service.saveDay(LocalDate.of(2023,12,25));

        //When
        service.deleteDay(LocalDate.of(2023, 12, 24));
        int daysNumber = service.getAllDays().size();

        //Then
        assertEquals(1, daysNumber);

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("deleteDayTest NOT cleaned");
        }
    }
    @Test
    void deleteOldDayTest() throws TestNotCleaned, RecordNotExistsException, RecordAlreadyExistsException {
        //Given
        service.saveDay(LocalDate.of(2023,12,24));
        service.saveDay(LocalDate.of(2023,12,25));
        service.saveDay(LocalDate.of(2023,12,26));
        service.saveDay(LocalDate.of(2023,12,27));


        //When
        service.deleteOldDays(LocalDate.of(2023, 12, 25));
        int daysNumber = service.getAllDays().size();

        //Then
        assertEquals(2, daysNumber);

        //Clean
        try {
            service.deleteAll();
        } catch (Exception err) {
            throw new TestNotCleaned("deleteDayTest NOT cleaned");
        }
    }
}
