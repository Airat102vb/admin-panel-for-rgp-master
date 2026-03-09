package com.example.demo.controller.dto;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlayerRequest {

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
    @Positive
    Long birthday;

    Boolean banned = false;

    @NotNull
    @PositiveOrZero
    Integer experience = 0;

}
