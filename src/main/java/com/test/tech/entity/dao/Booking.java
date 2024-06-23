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
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "booking_code")
    private String bookingCode;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "concert_code")
    private String concertCode;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_at")
    private Date updatedAt;
}
