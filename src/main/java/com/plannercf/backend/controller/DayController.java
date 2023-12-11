package com.plannercf.backend.controller;

import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.facade.PCFFacade;
import com.plannercf.backend.mapper.DayMapper;
import com.plannercf.backend.service.exception.RecordAlreadyExistsException;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("plannercf/day")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DayController {
    private PCFFacade facade;
    private DayMapper mapper;

    @Autowired
    public DayController(PCFFacade facade, DayMapper mapper) {
        this.facade = facade;
        this.mapper = mapper;
    }

    @PostMapping(path = "/one/{date}")
    public ResponseEntity<DayDto> saveDay(@PathVariable("date")LocalDate date) throws RecordAlreadyExistsException {
        return ResponseEntity.ok(mapper.mapToDayDto(facade.saveDay(date)));
    }

    @PostMapping(path = "/many")
    public ResponseEntity<List<DayDto>> createDays(@RequestParam int dayQty, @RequestParam LocalDate startDate) throws RecordAlreadyExistsException {
        return ResponseEntity.ok(mapper.mapToDtoList(facade.createDays(dayQty, startDate)));
    }

    @GetMapping(value = "{date}")
    public ResponseEntity<DayDto> getDay(@PathVariable("date")LocalDate date) throws RecordNotExistsException {
        return ResponseEntity.ok(mapper.mapToDayDto(facade.getDayByDate(date)));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<DayDto>> getAllDays() {
        return ResponseEntity.ok(mapper.mapToDtoList(facade.getAllDays()));
    }

    @GetMapping(path = "/latest")
    public ResponseEntity<List<DayDto>> getLatestDay() {
        return ResponseEntity.ok(mapper.mapToDtoList(facade.getLatestDay()));
    }

    @DeleteMapping(value = "{date}")
    public ResponseEntity<Void> deleteDay(@PathVariable("date") LocalDate date) {
        facade.deleteDay(date);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/old/{date}")
    public ResponseEntity<Void> deleteOldDays(@PathVariable("date") LocalDate date) throws RecordNotExistsException {
        facade.deleteOldDates(date);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllDays() {
        facade.deleteAll();
        return ResponseEntity.ok().build();
    }
}
