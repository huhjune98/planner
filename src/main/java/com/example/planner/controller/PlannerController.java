package com.example.planner.controller;


import com.example.planner.dto.PlannerRequestDto;
import com.example.planner.dto.PlannerResponseDto;
import com.example.planner.entity.Planner;
import com.example.planner.service.PlannerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/planners")
public class PlannerController {

    private final PlannerService plannerService;


    /**
     * 생성자 주입
     * 클래스가 필요로 하는 의존성을 생성자를 통해 전달하는 방식
     * @param plannerService @Service로 등록된 plannerService 구현체인 Impl
     */
    public PlannerController(PlannerService plannerService) {
        this.plannerService = plannerService;
    }


    /**
     * 플래너 생성 API
     * @param : {@link PlannerRequestDto} 플래서 생성 요청 객체
     * @return : {@link ResponseEntity<PlannerResponseDto>} JSON 응답
     */
    @PostMapping
    public ResponseEntity<PlannerResponseDto> createPlanner(@RequestBody PlannerRequestDto dto) {

        //요청받은 데이터 토대로 planner객체 생성
        return new ResponseEntity<>(plannerService.createPlanner(dto), HttpStatus.CREATED);
    }



    /**
     * 플래너 전체 조회 API
     * @return : {@link List<PlannerResponseDto>} JSON 응답
     */
    @GetMapping
    public List<PlannerResponseDto> getAllPlanners(){

        return plannerService.getAllPlanners();
    }


    /**
     * 플래너 단건 조회 API
     * @param id 식별자
     * @return : {@link ResponseEntity<PlannerResponseDto>} JSON 응답
     * @exception ResponseStatusException 식별자로 조회된 Memo가 없는 경우 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity findPlannerById(@PathVariable Long id) {

        return new ResponseEntity<>(plannerService.findPlannerById(id), HttpStatus.OK);
    }

    /**
     * 플래너 수정 API
     * @param id 식별자
     * @param : {@link PlannerRequestDto} 메모 수정 요청 객체
     * @return : {@link ResponseEntity<PlannerResponseDto>} JSON 응답
     * @exception ResponseStatusException 요청 필수값이 없는 경우 400 Bad Request, 식별자로 조회된 Memo가 없는 경우 404 Not Found
     */
    @PutMapping("/{id}")
    public ResponseEntity<PlannerResponseDto> updateMemo(
            @PathVariable Long id,
            @RequestBody PlannerRequestDto requestDto
    ) {

        return new ResponseEntity<>(plannerService.updatePlanner(id, requestDto.getUserName(), requestDto.getContent(), requestDto.getPassword()), HttpStatus.OK);
    }



    /**
     * 플래너 삭제 API
     * @param id 식별자
     * @return {@link ResponseEntity<Void>} 성공시 Data 없이 200OK 상태코드만 응답.
     * @exception ResponseStatusException 식별자로 조회된 Memo가 없는 경우 404 Not Found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemo(@PathVariable Long id, String password) {

        plannerService.deletePlanner(id, password);
        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
