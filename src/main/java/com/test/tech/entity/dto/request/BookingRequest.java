package com.test.tech.entity.dto.request;

import com.test.tech.entity.dto.internal.BookingTicket;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
    private String concertCode;
    private int totalPrice;
    private BookingTicket ticket;
}
