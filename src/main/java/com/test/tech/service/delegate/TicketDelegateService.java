package com.test.tech.service.delegate;

import com.test.tech.entity.dao.Ticket;

import java.util.List;

public interface TicketDelegateService {
    List<Ticket> saveBatchTicket(List<Ticket> tickets);
    Ticket getTicketByTicketNumber(String ticketNumber);
}
