package com.dietcompass.controller;

import com.dietcompass.model.*;
import com.dietcompass.repository.MealRepository;
import com.dietcompass.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.*;

@RestController
@RequestMapping("/meal-plan")
@RequiredArgsConstructor
public class MealPlanController {

    private final MealPlanService planService;
    private final UserService userService;
    private final MealRepository mealRepo;

    @GetMapping
    public List<MealPlan> getMonthlyPlans(
            @RequestParam("month") @DateTimeFormat(pattern = "yyyy-MM") YearMonth month,
            @AuthenticationPrincipal UserDetails userDetails) {

        var user = userService.findByUsername(userDetails.getUsername());
        return planService.getMonthlyPlans(user, month);
    }

    @PostMapping
    public MealPlan planMeal(@RequestBody MealPlan plan,
                             @AuthenticationPrincipal UserDetails userDetails) {
        var user = userService.findByUsername(userDetails.getUsername());
        plan.setUser(user);
        return planService.savePlan(plan);
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
    }
}
