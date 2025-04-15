package com.dietcompass.service;

import com.dietcompass.model.MealPlan;
import com.dietcompass.model.User;
import com.dietcompass.repository.MealPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealPlanService {

    private final MealPlanRepository mealPlanRepo;

    public List<MealPlan> getMonthlyPlans(User user, YearMonth month) {
        LocalDate start = month.atDay(1);
        LocalDate end = month.atEndOfMonth();
        return mealPlanRepo.findByUserAndDateBetween(user, start, end);
    }

    public MealPlan savePlan(MealPlan plan) {
        return mealPlanRepo.save(plan);
    }

    public void deletePlan(Long id) {
        mealPlanRepo.deleteById(id);
    }
}
