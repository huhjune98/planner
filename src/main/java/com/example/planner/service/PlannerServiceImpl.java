package com.example.planner.service;


import com.example.planner.dto.PlannerRequestDto;
import com.example.planner.dto.PlannerResponseDto;
import com.example.planner.entity.Planner;
import com.example.planner.repository.PlannerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlannerServiceImpl implements PlannerService {

    private final PlannerRepository plannerRepository;

    public PlannerServiceImpl(PlannerRepository plannerRepository) {
        this.plannerRepository = plannerRepository;
    }

    @Override
    public PlannerResponseDto createPlanner(PlannerRequestDto dto) {

        Planner planner = new Planner(dto.getUserName(), dto.getPassword(), dto.getTitle(), dto.getContent());


        return plannerRepository.createPlanner(planner);
    }

    @Override
    public List<PlannerResponseDto> getAllPlanners() {

        List<PlannerResponseDto> allPlanners = plannerRepository.getAllPlanners();

        return allPlanners;
    }

    @Override
    public PlannerResponseDto findPlannerById(Long id) {

        Planner planner = plannerRepository.findPlannerByIdOrElseThrow(id);

        return new PlannerResponseDto(planner);
    }

    @Override
    public PlannerResponseDto updatePlanner(Long id, String userName, String content, String password) {

        if (password == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot be null");
        }

        int updatedRow = plannerRepository.updatePlanner(id, userName, content, password);

        if (updatedRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist password =" + password);
        }

        Planner planner = plannerRepository.findPlannerByIdOrElseThrow(id);

        return new PlannerResponseDto(planner);
    }

    @Override
    public void deletePlanner(Long id, String password) {
        int deletedRow = plannerRepository.deletePlanner(id, password);

        if (deletedRow == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist password =" + password);
        }
    }
}
