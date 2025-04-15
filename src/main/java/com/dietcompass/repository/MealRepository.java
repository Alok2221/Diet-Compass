package com.dietcompass.repository;

import com.dietcompass.model.Meal;
import com.dietcompass.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByUser(User user);
}
