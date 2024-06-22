package com.test.tech.entity.dao;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "regulation")
public class Regulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regulation_id")
    private long regulationId;

    @Column(name = "limit_booking")
    private int limitBooking;

    @Column(name = "start_limit")
    private LocalTime startLimit;

    @Column(name = "end_limit")
    private LocalTime endLimit;

    @Column(name = "concert_code")
    private String concertCode;
}
