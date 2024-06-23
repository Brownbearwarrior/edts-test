package com.test.tech.service.internal;

import com.test.tech.entity.dao.Ticket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.BookingRequest;

import java.util.List;

public interface TicketInternalService {
    Ticket getTicketByTicketNumber(String ticketNumber);
    List<Ticket> generateTickets(UserAccount userAccount, BookingRequest request);
}
