package com.workoutch.service;

import com.workoutch.model.Activity;
import com.workoutch.repository.ActivityRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@RequiredArgsConstructor
public class ActivityService {
    @Autowired
    private ActivityRespository activityRespository;

    public List<Activity> findAll() {
        return activityRespository.findAll();
    }

    public Optional<Activity> findById(Long id) {
        return activityRespository.findById(id);
    }

    public Activity save(Activity stock) {
        return activityRespository.save(stock);
    }

    public void deleteById(Long id) {
        activityRespository.deleteById(id);
    }
}
