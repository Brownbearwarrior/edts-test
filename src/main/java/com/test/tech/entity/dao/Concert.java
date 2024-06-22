package com.test.tech.entity.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "concert")
public class Concert {

    @Id
    @Column(name = "concert_code")
    private String concertCode;

    @Column(name = "concert_name")
    private String concertName;

    @Column(name = "main_performer")
    private String mainPerformer;

    @Column(name = "city")
    private String city;

    @Column(name = "location")
    private String location;

    @Column(name = "held_Date")
    private Date heldDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;
}
