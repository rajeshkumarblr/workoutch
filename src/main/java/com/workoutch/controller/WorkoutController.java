package com.workoutch.controller;

import com.workoutch.model.Workout;
import com.workoutch.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/workouts")
@Slf4j
@RequiredArgsConstructor
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<Workout>> findAll() {
        return ResponseEntity.ok(workoutService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Workout workout) {
        return ResponseEntity.ok(workoutService.save(workout));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workout> findById(@PathVariable Long id) {
        Optional<Workout> stock = workoutService.findById(id);
        if (!stock.isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workout> update(@PathVariable Long id, @Valid @RequestBody Workout workout) {
        if (!workoutService.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(workoutService.save(workout));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!workoutService.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        workoutService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
