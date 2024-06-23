package com.test.tech.utils;

import com.test.tech.entity.dao.Ticket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.internal.BookingTicket;

import java.util.Date;

public class TicketUtils {
    private TicketUtils(){}

    public static Ticket toTicket(UserAccount userAccount, String concertCode,
                                  BookingTicket bookingTicket){
        return Ticket.builder()
                .ticketNumber(bookingTicket.getTicketNumber())
                .fullName(bookingTicket.getFullName())
                .idNumber(bookingTicket.getIdNumber())
                .concertCode(concertCode)
                .concertTicketCode(bookingTicket.getConcertTicketCode())
                .seatCode(bookingTicket.getSeatCode())
                .classTicket(bookingTicket.getClassTicket())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }
}
