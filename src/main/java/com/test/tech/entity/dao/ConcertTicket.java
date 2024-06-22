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
@Table(name = "concert_ticket")
public class ConcertTicket {

    @Id
    @Column(name = "concert_ticket_code")
    private String concertTicketCode;

    @Column(name = "concert_code")
    private String concertCode;

    @Column(name = "class_ticket")
    private String classTicket;

    @Column(name = "available_ticket")
    private int availableTicket;

    @Column(name = "price")
    private int price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;
}
