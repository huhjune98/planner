package com.example.planner.repository;

import com.example.planner.dto.PlannerRequestDto;
import com.example.planner.dto.PlannerResponseDto;
import com.example.planner.entity.Planner;

import java.util.List;
import java.util.Optional;

public interface PlannerRepository {

    PlannerResponseDto createPlanner(Planner planner);

    List<PlannerResponseDto> getAllPlanners();

    Planner findPlannerByIdOrElseThrow(Long id);

    int updatePlanner(Long Id, String userName, String content, String password);

    int deletePlanner(Long Id, String password);

}
