package com.test.tech.service.internal.impl;

import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.entity.dao.SoldTicketDetail;
import com.test.tech.service.delegate.SoldTicketDelegateService;
import com.test.tech.service.delegate.SoldTicketDetailDelegateService;
import com.test.tech.service.internal.SoldTicketInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SoldTicketInternalServiceImpl implements SoldTicketInternalService {
    private final SoldTicketDelegateService delegateService;
    private final SoldTicketDetailDelegateService detailDelegateService;

    @Override
    public void createSoldTicketData(List<SoldTicket> soldTickets, String concertCode) {
        List<SoldTicket> exist = this.findSoldTicketByConcertCode(concertCode);
        if (!exist.isEmpty()){
            //update
        }

        for (SoldTicket soldTicket:soldTickets){
            this.saveSoldTicket(soldTicket);
        }
    }

    @Override
    public void processIssuesTicket(SoldTicket soldTicket, SoldTicketDetail soldTicketDetail) {
        this.saveSoldTicket(soldTicket);
        this.saveSoldTicketDetail(soldTicketDetail);
    }

    @Override
    public SoldTicket getSpecificSoldTicket(String concertCode, String soldTicketId) {
        return this.findSpecificSoldTicket(concertCode, soldTicketId);
    }

    @Override
    public List<SoldTicket> findSoldTicketDataByConcertCode(String concertCode) {
        return this.findSoldTicketByConcertCode(concertCode);
    }

    private void saveSoldTicket(SoldTicket soldTicket){
        delegateService.save(soldTicket);
    }

    private SoldTicket findSpecificSoldTicket(String concertCode, String soldTicketId){
        return delegateService.getSpecificSoldTicket(concertCode, soldTicketId);
    }

    private List<SoldTicket> findSoldTicketByConcertCode(String concertCode) {
        return delegateService.findSoldTicketByConcertCode(concertCode);
    }

    private void saveSoldTicketDetail(SoldTicketDetail soldTicketDetail){
        detailDelegateService.saveDetail(soldTicketDetail);
    }
}
