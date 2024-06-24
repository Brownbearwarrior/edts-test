package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.Ticket;
import com.test.tech.repository.TicketRepository;
import com.test.tech.service.delegate.TicketDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketDelegateServiceImpl implements TicketDelegateService {
    private final TicketRepository repository;

    @Override
    public List<Ticket> saveBatchTicket(List<Ticket> tickets) {
        return repository.saveAll(tickets);
    }

    @Override
    public Ticket getTicketByTicketNumber(String ticketNumber) {
        return repository.findByTicketNumber(ticketNumber);
    }
}
