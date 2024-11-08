package com.example.planner.dto;


import com.example.planner.entity.Planner;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PlannerResponseDto {

//    private String message;
    private Long eventId;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDate updateDate;


    public PlannerResponseDto(Planner planner) {
        this.eventId = planner.getEventId();
        this.title = planner.getTitle();
        this.content = planner.getContent();
        this.createDate = planner.getCreateDate();
        this.updateDate = planner.getUpdateDate();
    }
}
