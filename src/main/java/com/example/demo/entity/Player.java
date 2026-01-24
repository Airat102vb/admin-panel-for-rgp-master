package com.example.demo.entity;

import javax.validation.constraints.*;

public class Player {

    Long id;

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
    Long birthday; // TODO внутри приложения LocalDate

    Boolean banned = false;

    @NotNull
    @Min(0)
    @Max(70)
    Integer experience;

    Integer level;
    Integer untilNextLevel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Boolean getBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getUntilNextLevel() {
        return untilNextLevel;
    }

    public void setUntilNextLevel(Integer untilNextLevel) {
        this.untilNextLevel = untilNextLevel;
    }
}
