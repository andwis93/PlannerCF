package com.plannercf.backend.service;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import com.plannercf.backend.service.exception.TestNotCleaned;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@DisplayName("DayService.class Test Suite")
public class DayServiceTests {
    @Autowired
    private DayService service;

    @Test
    void createDayTest() throws RecordNotExistsException, TestNotCleaned {
        //Given
        service.createDay(LocalDate.of(2023,12,24));

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


}
