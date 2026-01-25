package com.example.demo.controller.dto;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PutPlayerRequest {

    @Size(max = 12)
    String name;

    @Size(max = 30)
    String title;

    Race race;

    Profession profession;

    @Min(0)
    @Digits(integer = 19, fraction = 0)
    Long birthday;

    Boolean banned;

    @Min(0)
    @Max(70)
    Integer experience;

}
