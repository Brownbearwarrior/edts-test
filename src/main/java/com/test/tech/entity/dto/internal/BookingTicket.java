package com.test.tech.entity.dto.internal;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingTicket {
    private String concertTicketCode;
    private String ticketNumber;
    private String fullName;
    private String idNumber;
    private String seatCode;
    private String classTicket;
    private int price;
}
