package com.test.tech.service.internal;

import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.entity.dao.SoldTicketDetail;

import java.util.List;

public interface SoldTicketInternalService {
    void createSoldTicketData(List<SoldTicket> soldTickets, String concertCode);
    void processIssuesTicket(SoldTicket soldTicket, SoldTicketDetail soldTicketDetail);
    SoldTicket getSpecificSoldTicket(String concertCode, String soldTicketId);
    List<SoldTicket> findSoldTicketDataByConcertCode(String concertCode);

}
