package com.workoutch.controller;

import com.workoutch.model.Activity;
import com.workoutch.model.Workout;
import com.workoutch.service.ActivityService;
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
@RequestMapping("/api/v1/activities")
@Slf4j
@RequiredArgsConstructor
public class ActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private WorkoutService workoutService;

    @GetMapping
    public ResponseEntity<List<Activity>> findAll() {

        return ResponseEntity.ok(activityService.findAll());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Activity activity) {
        return ResponseEntity.ok(activityService.save(activity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> findById(@PathVariable Long id) {
        Optional<Activity> stock = activityService.findById(id);
        if (!stock.isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(stock.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Activity> update(@PathVariable Long id, @Valid @RequestBody Activity activity) {
        if (!activityService.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(activityService.save(activity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!activityService.findById(id).isPresent()) {
            //log.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        activityService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
