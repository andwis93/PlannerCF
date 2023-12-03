package com.plannercf.backend.controller;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import com.plannercf.backend.facade.PCFFacade;
import com.plannercf.backend.mapper.DayMapper;
import com.plannercf.backend.service.exception.RecordNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/one")
    public ResponseEntity<Boolean> createDay(@RequestBody DayDto dayDto) {
        return ResponseEntity.ok(facade.createDay(dayDto));
    }

    @PostMapping(path = "/many")
    public ResponseEntity<Void> createDays(@RequestParam int numberOfDays, @RequestParam LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{date}")
    public ResponseEntity<DayDto> getDay(@PathVariable("date")LocalDate date) throws RecordNotExistsException {
        return ResponseEntity.ok(mapper.mapToDayDto(facade.getDayByDate(date)));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<DayDto>> getAllDays() {
        return ResponseEntity.ok(mapper.mapToDtoList(facade.getAllDays()));
    }

    @PutMapping(value = "/change/{date}")
    public ResponseEntity<Day> updateDay(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(new Day(LocalDate.now()));
    }

    @DeleteMapping(value = "{date}")
    public ResponseEntity<Void> deleteDay(@PathVariable("date") LocalDate date) {
        facade.deleteDay(date);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/old/{date}")
    public ResponseEntity<Void> deleteOldDays(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllDay() {
        facade.deleteAll();
        return ResponseEntity.ok().build();
    }
}
