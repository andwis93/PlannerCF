package com.plannercf.backend.service;

import com.plannercf.backend.mapper.DayMapper;
import com.plannercf.backend.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DayService {
    private final DayRepository repository;
    private final DayMapper mapper;

    @Autowired
    public DayService(DayRepository repository, DayMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
