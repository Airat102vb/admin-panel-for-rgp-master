package com.example.demo.controller.dto;

import com.example.demo.filter.PlayerOrder;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPlayersRequest {

    String name;
    String title;
    Race race;
    Profession profession;
    Long after;
    Long before;
    Boolean banned;
    Integer minExperience;
    Integer maxExperience;
    Integer minLevel;
    Integer maxLevel;
    PlayerOrder order = PlayerOrder.ID;
    Integer pageNumber = 0;
    Integer pageSize = 3;

}
