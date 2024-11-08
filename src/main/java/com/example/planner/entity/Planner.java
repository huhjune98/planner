package com.example.planner.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Planner {


    private Long eventId;
    private String userName;
    private String title;
    private String content;
    private String password;
    private LocalDateTime createDate;
    private LocalDate updateDate;


    public Planner(String userName, String password, String title, String content) {
        this.userName = userName;
        this.password = password;
        this.title = title;
        this.content = content;
        this.createDate = LocalDateTime.now();
        this.eventId = null;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}



