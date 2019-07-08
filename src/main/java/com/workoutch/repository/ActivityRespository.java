package com.workoutch.repository;

import com.workoutch.model.Activity;
import com.workoutch.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRespository extends JpaRepository<Activity, Long> {
    List<Activity> findByDescription(String activity);
}
