package com.example.demo.controller.dto;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class PostPlayerRequest {

    @NotBlank
    @Size(max = 12)
    String name;

    @NotBlank
    @Size(max = 30)
    String title;

    @NotNull
    Race race;

    @NotNull
    Profession profession;

    @NotNull
    @Min(0)
    @Digits(integer = 19, fraction = 0)
    Long birthday;

    Boolean banned = false;

    @NotNull
    @Digits(integer = 7, fraction = 0)
    Integer experience = 0;

}
