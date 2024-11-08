package com.example.planner.service;

import com.example.planner.dto.PlannerRequestDto;
import com.example.planner.dto.PlannerResponseDto;

import java.util.List;

public interface PlannerService {

    PlannerResponseDto createPlanner(PlannerRequestDto plannerRequestDto);

    List<PlannerResponseDto> getAllPlanners();

    PlannerResponseDto findPlannerById(Long id);

    PlannerResponseDto updatePlanner(Long id, String userName, String content, String password);

    void deletePlanner(Long id, String password);

}
