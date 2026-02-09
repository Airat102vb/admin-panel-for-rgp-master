package com.example.demo.repository.entity;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "title")
    String title;

    @Column(name = "race")
    Race race;

    @Column(name = "profession")
    Profession profession;

    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "banned")
    Boolean banned;

    @Column(name = "experience")
    Integer experience;

    @Column(name = "level")
    Integer level;

    @Column(name = "untilNextLevel")
    Integer untilNextLevel;

}
