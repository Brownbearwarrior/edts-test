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
@Table(name = "sold_ticket_detail")
public class SoldTicketDetail {

    @Id
    @Column(name = "sold_ticket_detail_id")
    private String soldTicketDetailId;

    @Column(name = "sold_ticket_id")
    private String soldTicketId;

    @Column(name = "ticket_number")
    private String ticketNumber;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;
}
