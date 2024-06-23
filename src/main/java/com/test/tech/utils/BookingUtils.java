package com.test.tech.utils;

import com.test.tech.entity.dao.Booking;
import com.test.tech.entity.dao.BookingDetail;
import com.test.tech.entity.dao.Ticket;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.internal.BookingTicket;
import com.test.tech.entity.dto.request.BookingRequest;
import com.test.tech.entity.dto.response.BookingResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingUtils {
    private BookingUtils(){}

    public static Booking toBooking(UserAccount userAccount, BookingRequest request){
        return Booking.builder()
                .bookingCode(CommonUtils.getRandomValue(5))
                .bookingStatus("WAITING_PAYMENT")
                .orderCode(CommonUtils.getRandomValue(9))
                .concertCode(request.getConcertCode())
                .totalPrice(request.getTotalPrice())
                .userName(userAccount.getUserName())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }

    public static BookingDetail toBookingDetail(UserAccount userAccount, BookingTicket bookingTicket, String bookingCode, String bookingDetailId){
        return BookingDetail.builder()
                .bookingDetailId(bookingDetailId)
                .bookingCode(bookingCode)
                .ticketNumber(bookingTicket.getTicketNumber())
                .price(bookingTicket.getPrice())
                .createdBy(userAccount.getUserName())
                .createdAt(new Date())
                .updatedBy(userAccount.getUserName())
                .updatedAt(new Date())
                .build();
    }

    public static BookingResponse toResponse(Booking booking, List<BookingTicket> tickets){
        return BookingResponse.builder()
                .bookingCode(booking.getBookingStatus().equalsIgnoreCase("ISSUED") ?booking.getBookingCode():null)
                .orderCode(booking.getOrderCode())
                .bookingStatus(booking.getBookingStatus())
                .concertCode(booking.getConcertCode())
                .totalPrice(booking.getTotalPrice())
                .ticket(tickets)
                .createdAt(booking.getCreatedAt())
                .createdBy(booking.getCreatedBy())
                .updatedAt(booking.getUpdatedAt())
                .updatedBy(booking.getUpdatedBy())
                .build();
    }

    public static BookingTicket toBookingTicket(BookingDetail bookingDetail, Ticket ticket){
        return BookingTicket.builder()
                .concertTicketCode(ticket.getConcertTicketCode())
                .ticketNumber(bookingDetail.getTicketNumber())
                .idNumber(ticket.getIdNumber())
                .fullName(ticket.getFullName())
                .seatCode(ticket.getSeatCode())
                .classTicket(ticket.getClassTicket())
                .price(bookingDetail.getPrice())
                .build();
    }

    public static List<BookingDetail> generateBookingDetails(UserAccount userAccount, BookingRequest request, String bookingCode){
        List<BookingDetail> bookingDetails = new ArrayList<>();
        for (int i = 0; i < request.getTicket().size(); i++){
            String bookingDetailId = bookingCode.concat(Integer.toString(i));
            bookingDetails.add(toBookingDetail(userAccount, request.getTicket().get(i), bookingCode, bookingDetailId));
        }

        return bookingDetails;
    }

    public static Booking updateStatus(Booking booking, String status){
        booking.setBookingStatus(status);
        booking.setUpdatedBy("SYSTEM");
        booking.setUpdatedAt(new Date());

        return booking;
    }
}
