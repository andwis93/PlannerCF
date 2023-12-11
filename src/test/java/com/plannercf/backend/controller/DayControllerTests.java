package com.plannercf.backend.controller;

import com.plannercf.backend.facade.PCFFacade;
import com.plannercf.backend.mapper.DayMapper;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

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

}
