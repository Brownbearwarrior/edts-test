package com.test.tech.utils;

import com.test.tech.entity.dao.Concert;
import com.test.tech.entity.dao.ConcertTicket;
import com.test.tech.entity.dao.SoldTicket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.internal.ConcertTicketInternal;
import com.test.tech.entity.dto.request.ConcertRequest;
import com.test.tech.entity.dto.response.ConcertResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConcertUtils {
    private ConcertUtils(){}

    public static Concert toConcert(UserAccount userAccount, ConcertRequest request){
        return Concert.builder()
                .concertCode(generateConcertCode(request))
                .concertName(request.getConcertName())
                .city(request.getCity())
                .location(request.getLocation())
                .mainPerformer(request.getMainPerformer())
                .heldDate(request.getHeldDate())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }

    public static ConcertTicket toConcertTicket(UserAccount userAccount, ConcertTicketInternal ticketInternal, String concertCode){
        return ConcertTicket.builder()
                .concertTicketCode(generateTicketCode(concertCode, ticketInternal))
                .concertCode(concertCode)
                .classTicket(ticketInternal.getClassTicket())
                .availableTicket(ticketInternal.getAvailableTicket())
                .price(ticketInternal.getPrice())
                .currency(ticketInternal.getCurrency())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }

    public static ConcertResponse toResponse(Concert concert, List<ConcertTicket> tickets){
        return ConcertResponse.builder()
                .concertCode(concert.getConcertCode())
                .concertName(concert.getConcertName())
                .city(concert.getCity())
                .location(concert.getLocation())
                .mainPerformer(concert.getMainPerformer())
                .heldDate(concert.getHeldDate())
                .ticket(toTicketResponse(tickets))
                .createdAt(concert.getCreatedAt())
                .createdBy(concert.getCreatedBy())
                .updatedAt(concert.getUpdatedAt())
                .updatedBy(concert.getUpdatedBy())
                .build();
    }

    public static Concert updateConcert(Concert exist, Concert news){
        exist.setLocation(news.getLocation());
        exist.setHeldDate(news.getHeldDate());
        exist.setUpdatedBy(news.getUpdatedBy());
        exist.setUpdatedAt(news.getUpdatedAt());

        return exist;
    }

    public static List<ConcertTicket> updateTickets(List<ConcertTicket> exist, List<ConcertTicket> news, List<SoldTicket> soldTickets){
        for (ConcertTicket ticket:exist){
            for (ConcertTicket newTicket:news){
                if (ticket.getConcertTicketCode().equalsIgnoreCase(newTicket.getConcertTicketCode())){
                    SoldTicket soldTicket = SoldTicketUtils.getSoldTicketSpecific(soldTickets, ticket.getConcertTicketCode());

                    //available ticket same with remaining ticket
                    if ((ticket.getAvailableTicket() == soldTicket.getRemainingTicket())) {
                        updateConcertTicket(ticket, newTicket);
                    }

                    //available ticket not same with remaining ticket
                    if (!validationRemainingTicket(ticket, newTicket, soldTicket)){
                        updateConcertTicket(ticket, newTicket);
                    }
                }
            }
        }

        return exist;
    }

    private static String generateConcertCode(ConcertRequest request){
        DateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
        String dateHeld = dateFormat.format(request.getHeldDate());
        String performer = request.getMainPerformer().toUpperCase().trim();
        String city = request.getCity().toUpperCase().trim();

        return dateHeld.concat(performer.concat(city));
    }

    private static String generateTicketCode(String concertCode, ConcertTicketInternal ticketInternal){
        return concertCode.concat(ticketInternal.getClassTicket().toUpperCase().trim());
    }

    private static List<ConcertTicketInternal> toTicketResponse(List<ConcertTicket> tickets){
        return tickets.stream()
                .map(ConcertUtils::toTicketInternal)
                .toList();
    }

    private static ConcertTicketInternal toTicketInternal(ConcertTicket ticket){
        return ConcertTicketInternal.builder()
                .availableTicket(ticket.getAvailableTicket())
                .classTicket(ticket.getClassTicket())
                .price(ticket.getPrice())
                .currency(ticket.getCurrency())
                .build();
    }

    private static boolean validationRemainingTicket(ConcertTicket ticket, ConcertTicket newTicket, SoldTicket soldTicket){
        return (ticket.getAvailableTicket() > soldTicket.getRemainingTicket()) && (newTicket.getAvailableTicket() < soldTicket.getRemainingTicket());
    }

    private static void updateConcertTicket(ConcertTicket ticket, ConcertTicket newTicket){
        ticket.setAvailableTicket(newTicket.getAvailableTicket());
        ticket.setPrice(newTicket.getPrice());
        ticket.setCurrency(newTicket.getCurrency());
        ticket.setUpdatedBy(newTicket.getUpdatedBy());
        ticket.setUpdatedAt(new Date());
    }
}
