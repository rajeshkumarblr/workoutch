package com.workoutch.service;

import com.workoutch.model.Workout;
import com.workoutch.repository.WorkoutRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class WorkoutService {
    @Autowired
    private WorkoutRespository workoutRespository;

    public List<Workout> findAll() {
        return workoutRespository.findAll();
    }

    public Optional<Workout> findById(Long id) {
        return workoutRespository.findById(id);
    }

    public Workout save(Workout stock) {
        return workoutRespository.save(stock);
    }

    public void deleteById(Long id) {
        workoutRespository.deleteById(id);
    }
}
