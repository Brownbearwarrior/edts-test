package com.test.tech.service.delegate;

import com.test.tech.entity.dao.SoldTicket;

import java.util.List;

public interface SoldTicketDelegateService {
    SoldTicket save(SoldTicket soldTicket);
    List<SoldTicket> saveBatch(List<SoldTicket> soldTickets);
    SoldTicket getSpecificSoldTicket(String concertCode, String soldTicketId);
    List<SoldTicket> findSoldTicketByConcertCode(String concertCode);
}
