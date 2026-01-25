package com.example.demo.controller.dto;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPlayerResponse {

    Long id;
    String name;
    String title;
    Race race;
    Profession profession;
    Long birthday;
    Boolean banned = false;
    Integer experience;
    Integer level;
    Integer untilNextLevel;

}
