package com.example.demo.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Generated;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "audit")
public class Audit {

    @Id
    @Generated
    private Long id;

    @Column
    private String login;

    @Column
    private String action;

    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
