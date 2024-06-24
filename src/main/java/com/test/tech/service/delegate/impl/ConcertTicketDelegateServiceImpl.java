package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.ConcertTicket;
import com.test.tech.repository.ConcertTicketRepository;
import com.test.tech.service.delegate.ConcertTicketDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertTicketDelegateServiceImpl implements ConcertTicketDelegateService {
    private final ConcertTicketRepository repository;

    @Override
    public ConcertTicket saveConcertTicket(ConcertTicket concertTicket) {
        return repository.save(concertTicket);
    }

    @Override
    public List<ConcertTicket> saveBatchConcertTicket(List<ConcertTicket> concertTickets) {
        return repository.saveAll(concertTickets);
    }

    @Override
    public List<ConcertTicket> findConcertTicket(String concertCode) {
        return repository.findByConcertCode(concertCode);
    }

    @Override
    public void deleteConcertTickets(List<ConcertTicket> concertTickets) {
        repository.deleteAll(concertTickets);
    }
}
