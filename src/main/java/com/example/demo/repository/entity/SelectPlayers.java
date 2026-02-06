package com.example.demo.repository.entity;

import com.example.demo.filter.PlayerOrder;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SelectPlayers {

    String name;
    String title;
    Race race;
    Profession profession;
    LocalDate after;
    LocalDate before;
    Boolean banned = false;
    Integer minExperience;
    Integer maxExperience;
    Integer minLevel;
    Integer maxLevel;
    PlayerOrder order;
    Integer pageNumber;
    Integer pageSize;

}
