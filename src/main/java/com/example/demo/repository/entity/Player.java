package com.example.demo.repository.entity;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Player {

    Long id;
    String name;
    String title;
    Race race;
    Profession profession;
    LocalDate birthday;
    Boolean banned = false;
    Integer experience;
    Integer level;
    Integer untilNextLevel;

}
