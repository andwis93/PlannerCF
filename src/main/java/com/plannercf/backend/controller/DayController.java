package com.plannercf.backend.controller;

import com.plannercf.backend.domain.Day;
import com.plannercf.backend.domain.DayDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("plannercf/day")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DayController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, path = "/one")
    public ResponseEntity<Boolean> createDay(@RequestBody DayDto dayDto) {
        return ResponseEntity.ok(false);
    }

    @PostMapping(path = "/many")
    public ResponseEntity<Void> createDays(@RequestParam int numberOfDays, @RequestParam LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{date}")
    public ResponseEntity<DayDto> getDay(@PathVariable("date")LocalDate date) {
        return ResponseEntity.ok(new DayDto(LocalDate.now()));
    }

    @GetMapping(path = "/latest")
    public ResponseEntity<List<DayDto>> getDays() {
        return ResponseEntity.ok(new ArrayList<>());
    }

    @PutMapping(value = "/change/{date}")
    public ResponseEntity<Day> updateDay(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok(new Day(LocalDate.now()));
    }

    @DeleteMapping(value = "{date}")
    public ResponseEntity<Void> deleteDay(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/old/{date}")
    public ResponseEntity<Void> deleteOldDays(@PathVariable("date") LocalDate date) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/all")
    public ResponseEntity<Void> deleteAllDay() {
        return ResponseEntity.ok().build();
    }

}
