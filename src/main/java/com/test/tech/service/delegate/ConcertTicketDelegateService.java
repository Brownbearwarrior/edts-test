package com.test.tech.service.delegate;

import com.test.tech.entity.dao.ConcertTicket;

import java.util.List;

public interface ConcertTicketDelegateService {
    ConcertTicket saveConcertTicket(ConcertTicket concertTicket);
    List<ConcertTicket> saveBatchConcertTicket(List<ConcertTicket> concertTickets);
    List<ConcertTicket> findConcertTicket(String concertCode);
    void deleteConcertTickets(List<ConcertTicket> concertTickets);
}