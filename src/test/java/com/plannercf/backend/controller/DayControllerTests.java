package com.plannercf.backend.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.plannercf.backend.controller.adapter.LocalDateTypeAdapter;
import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.facade.PCFFacade;
import com.plannercf.backend.mapper.DayMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(DayController.class)
@DisplayName("DayController.class Test Suite")
public class DayControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PCFFacade facade;
    @MockBean
    private DayMapper mapper;

    Day day = new Day(1L, LocalDate.of(2023,12,23));
    DayDto dayDto = new DayDto(1L, LocalDate.of(2023,12,23), "SATURDAY");

    @Test
    void shouldSaveDay() throws Exception {
        //Given
        when(facade.saveDay(LocalDate.of(2023,12,23))).thenReturn(day);
        when(mapper.mapToDayDto(day)).thenReturn(dayDto);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        String jsonContent = gson.toJson(dayDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/plannercf/day/one/2023-12-23")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .contentType(jsonContent))
                .andExpect((MockMvcResultMatchers.status().isOk()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date", Matchers.is("2023-12-23")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dayName", Matchers.is("SATURDAY")));
    }
}
