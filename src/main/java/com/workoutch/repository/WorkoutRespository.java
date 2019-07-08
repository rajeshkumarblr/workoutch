package com.workoutch.repository;

import com.workoutch.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRespository extends JpaRepository<Workout, Long> {
}
