package com.example.demo.service.dto;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDto {

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
