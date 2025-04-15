package com.dietcompass.repository;

import com.dietcompass.model.MealPlan;
import com.dietcompass.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    List<MealPlan> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}