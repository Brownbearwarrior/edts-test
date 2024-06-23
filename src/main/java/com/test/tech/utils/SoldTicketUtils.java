package com.test.tech.utils;

import com.test.tech.entity.dao.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class SoldTicketUtils {
    private SoldTicketUtils(){}

    public static SoldTicket toSoldTicket(UserAccount userAccount, ConcertTicket concertTicket){
        return SoldTicket.builder()
                .soldTicketId(concertTicket.getConcertTicketCode())
                .classTicket(concertTicket.getClassTicket())
                .remainingTicket(concertTicket.getAvailableTicket())
                .concertCode(concertTicket.getConcertCode())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }

    public static SoldTicketDetail toSoldTicketDetail(Ticket ticket, SoldTicket soldTicket){
        return SoldTicketDetail.builder()
                .soldTicketDetailId(generateDetailId(ticket, soldTicket))
                .soldTicketId(soldTicket.getSoldTicketId())
                .ticketNumber(ticket.getTicketNumber())
                .createdBy("SYSTEM")
                .createdAt(new Date())
                .updatedBy("SYSTEM")
                .updatedAt(new Date())
                .build();
    }

    public static List<SoldTicket> toListSoldTickets(UserAccount userAccount, List<ConcertTicket> concertTickets){
        return concertTickets.stream()
                .map(concertTicket -> toSoldTicket(userAccount, concertTicket))
                .collect(Collectors.toList());
    }

    public static List<SoldTicket> updateSoldTicket(List<SoldTicket> exist, List<ConcertTicket> concertTickets){
        return exist;
    }

    public static SoldTicket getSoldTicketSpecific(List<SoldTicket> soldTickets, String soldTicketId){
        return soldTickets.stream()
                .filter(soldTicket -> soldTicket.getSoldTicketId().equalsIgnoreCase(soldTicketId))
                .findFirst()
                .orElse(null);
    }

    public static void decreaseRemainingTicket(SoldTicket soldTicket){
        soldTicket.setRemainingTicket(soldTicket.getRemainingTicket() - 1);
        soldTicket.setUpdatedBy("SYSTEM");
        soldTicket.setUpdatedAt(new Date());
    }

    private static String generateDetailId(Ticket ticket, SoldTicket soldTicket){
        return soldTicket.getSoldTicketId().toUpperCase().concat(ticket.getTicketNumber().toUpperCase()).trim();
    }
}
