package com.example.planner.dto;


import lombok.Getter;

@Getter
public class PlannerRequestDto {
    private String userName;
    private String password;
    private String title;
    private String content;
}
