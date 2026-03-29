package com.example.demo.repository.entity;

import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.time.LocalDate;

@Data
@Entity(name = "player")
public class Player {

    @Id
    @Generated
    Long id;

    @Column
    String name;

    @Column
    String title;

    @Column
    @Enumerated(EnumType.STRING)
    Race race;

    @Column
    @Enumerated(EnumType.STRING)
    Profession profession;

    @Column
    LocalDate birthday;

    @Column
    Boolean banned;

    @Column
    Integer experience;

    @Column
    Integer level;

    @Column(name = "until_next_level")
    Integer untilNextLevel;

}
