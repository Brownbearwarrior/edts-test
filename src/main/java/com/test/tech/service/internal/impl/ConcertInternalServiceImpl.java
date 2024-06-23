package com.test.tech.service.internal.impl;

import com.test.tech.entity.dao.Concert;
import com.test.tech.entity.dao.ConcertTicket;
import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.ConcertPage;
import com.test.tech.entity.dto.request.ConcertRequest;
import com.test.tech.entity.dto.response.ConcertResponse;
import com.test.tech.entity.dto.response.DataResponse;
import com.test.tech.service.delegate.ConcertDelegateService;
import com.test.tech.service.delegate.ConcertTicketDelegateService;
import com.test.tech.service.internal.ConcertInternalService;
import com.test.tech.service.internal.SoldTicketInternalService;
import com.test.tech.utils.ConcertUtils;
import com.test.tech.utils.ResponseUtils;
import com.test.tech.utils.SoldTicketUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConcertInternalServiceImpl implements ConcertInternalService {
    private final ConcertDelegateService delegateService;
    private final ConcertTicketDelegateService ticketDelegateService;
    private final SoldTicketInternalService soldTicketInternalService;

    @Override
    public ResponseEntity<DataResponse<Object>> createConcert(UserAccount userAccount, ConcertRequest request) {
        //initiate concert data
        Concert concert = ConcertUtils.toConcert(userAccount, request);
        List<ConcertTicket> concertTickets = request.getTicket().stream()
                .map(ticketInternal -> ConcertUtils.toConcertTicket(userAccount, ticketInternal, concert.getConcertCode()))
                .toList();

        ConcertResponse response = this.processConcertSaved(userAccount, concert, concertTickets);

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", response));
    }

    @Override
    public ResponseEntity<DataResponse<Object>> findDetailConcert(String concertCode) {
        //find concert data
        Concert concert = this.findConcert(concertCode);

        if (Objects.isNull(concert)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.getDetailConcertTicket(concertCode, concert)));
        }
    }

    @Override
    public ResponseEntity<DataResponse<Object>> findConcert(ConcertPage page) {
        return null;
    }

    @Override
    public ResponseEntity<DataResponse<Object>> update(String concertCode, UserAccount userAccount, ConcertRequest request) {
        //find concert data
        Concert exist = this.findConcert(concertCode);

        if (Objects.isNull(exist)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.processUpdateConcert(userAccount, concertCode, exist, request)));
        }
    }

    @Override
    public ResponseEntity<DataResponse<Object>> delete(String concertCode, UserAccount userAccount) {
        //find concert data
        Concert exist = this.findConcert(concertCode);

        if (Objects.isNull(exist)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        } else {
            //delete data
            this.deleteConcert(exist);
            List<ConcertTicket> concertTickets = this.findConcertTicket(concertCode);
            this.deleteConcertTickets(concertTickets);

            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", true));
        }
    }

    private ConcertResponse processConcertSaved(UserAccount userAccount, Concert concert, List<ConcertTicket> concertTickets){
        //saved all data
        Concert savedConcert = this.saveConcert(concert);
        List<ConcertTicket> savedTickets = this.saveBatchConvertTicket(concertTickets);
        this.processSoldTicket(userAccount, savedTickets, savedConcert.getConcertCode());

        return ConcertUtils.toResponse(savedConcert, savedTickets);
    }

    private void processSoldTicket(UserAccount userAccount, List<ConcertTicket> savedTickets, String concertCode){
        //initiate sold ticket availability
        List<SoldTicket> soldTickets = SoldTicketUtils.toListSoldTickets(userAccount, savedTickets);
        this.saveSoldTicket(soldTickets, concertCode);
    }

    private ConcertResponse getDetailConcertTicket(String concertCode, Concert concert){
        List<ConcertTicket> tickets = this.findConcertTicket(concertCode);
        return ConcertUtils.toResponse(concert, tickets);
    }

    private ConcertResponse processUpdateConcert(UserAccount userAccount, String concertCode, Concert exist, ConcertRequest request){
        //get detail concert ticket data and available sold ticket
        List<ConcertTicket> tickets = this.findConcertTicket(concertCode);
        List<ConcertTicket> newTickets = request.getTicket().stream()
                .map(ticketInternal -> ConcertUtils.toConcertTicket(userAccount, ticketInternal, concertCode))
                .toList();
        List<SoldTicket> soldTickets = this.findSoldTicketsByConcertCode(concertCode);

        //update value all data
        Concert updateConcert = ConcertUtils.updateConcert(exist, ConcertUtils.toConcert(userAccount, request));
        List<ConcertTicket> updateTickets = ConcertUtils.updateTickets(tickets, newTickets, soldTickets);
        List<SoldTicket> updateSoldTickets = SoldTicketUtils.updateSoldTicket(soldTickets, updateTickets);

        //save all data
        Concert savedConcert = this.saveConcert(updateConcert);
        List<ConcertTicket> savedTickets = this.saveBatchConvertTicket(updateTickets);
        this.saveSoldTicket(updateSoldTickets, concertCode);

        return ConcertUtils.toResponse(savedConcert, savedTickets);
    }

    private Concert saveConcert(Concert concert){
        return delegateService.saveConcert(concert);
    }

    private Concert findConcert(String concertCode){
        return delegateService.findConcert(concertCode);
    }

    private void deleteConcert(Concert concert){
        delegateService.deleteConcert(concert);
    }

    private ConcertTicket saveConcertTicket(ConcertTicket concertTicket){
        return ticketDelegateService.saveConcertTicket(concertTicket);
    }

    private List<ConcertTicket> saveBatchConvertTicket(List<ConcertTicket> concertTickets){
        return ticketDelegateService.saveBatchConcertTicket(concertTickets);
    }

    private List<ConcertTicket> findConcertTicket(String concertCode){
        return ticketDelegateService.findConcertTicket(concertCode);
    }

    private void deleteConcertTickets(List<ConcertTicket> concertTickets){
        ticketDelegateService.deleteConcertTickets(concertTickets);
    }

    private void saveSoldTicket(List<SoldTicket> soldTickets, String concertCode){
        soldTicketInternalService.createSoldTicketData(soldTickets, concertCode);
    }

    private List<SoldTicket> findSoldTicketsByConcertCode(String concertCode){
        return soldTicketInternalService.findSoldTicketDataByConcertCode(concertCode);
    }
}
