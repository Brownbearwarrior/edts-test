package com.test.tech.service.internal.impl;

import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.entity.dao.Ticket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.internal.BookingTicket;
import com.test.tech.entity.dto.request.BookingRequest;
import com.test.tech.service.delegate.TicketDelegateService;
import com.test.tech.service.internal.SoldTicketInternalService;
import com.test.tech.service.internal.TicketInternalService;
import com.test.tech.utils.TicketUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketInternalServiceImpl implements TicketInternalService {
    private final TicketDelegateService delegateService;
    private final SoldTicketInternalService soldTicketInternalService;

    @Override
    public Ticket getTicketByTicketNumber(String ticketNumber) {
        return this.getTicket(ticketNumber);
    }

    @Override
    public List<Ticket> generateTickets(UserAccount userAccount, BookingRequest request) {
        List<Ticket> bookedTicket = new ArrayList<>();

        for (BookingTicket bookingTicket:request.getTicket()){
            SoldTicket soldTicket = this.getSpecificSoldTicket(request.getConcertCode(), bookingTicket.getConcertTicketCode());

            if (soldTicket.getRemainingTicket() > 0){
                Ticket ticket = TicketUtils.toTicket(userAccount, request.getConcertCode(), bookingTicket);
                bookedTicket.add(ticket);
            }
        }

        return this.saveBatchTicket(bookedTicket);
    }

    private List<Ticket> saveBatchTicket(List<Ticket> tickets){
        return delegateService.saveBatchTicket(tickets);
    }

    private Ticket getTicket(String ticketNumber){
        return delegateService.getTicketByTicketNumber(ticketNumber);
    }

    private SoldTicket getSpecificSoldTicket(String concertCode, String concertTicketCode){
        return soldTicketInternalService.getSpecificSoldTicket(concertCode, concertTicketCode);
    }
}
