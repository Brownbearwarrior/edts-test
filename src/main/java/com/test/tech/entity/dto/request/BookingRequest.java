package com.test.tech.entity.dto.request;

import com.test.tech.entity.dto.internal.BookingTicket;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingRequest {
    private String concertCode;
    private int totalPrice;
    private List<BookingTicket> ticket;
}
