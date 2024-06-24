package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.repository.SoldTicketRepository;
import com.test.tech.service.delegate.SoldTicketDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoldTicketDelegateServiceImpl implements SoldTicketDelegateService {
    private final SoldTicketRepository repository;

    @Override
    public SoldTicket save(SoldTicket soldTicket) {
        return repository.save(soldTicket);
    }

    @Override
    public List<SoldTicket> saveBatch(List<SoldTicket> soldTickets) {
        return repository.saveAll(soldTickets);
    }

    @Override
    public SoldTicket getSpecificSoldTicket(String concertCode, String soldTicketId) {
        return repository.findByConcertCodeAndSoldTicketId(concertCode, soldTicketId);
    }

    @Override
    public List<SoldTicket> findSoldTicketByConcertCode(String concertCode) {
        return repository.findByConcertCode(concertCode);
    }
}
