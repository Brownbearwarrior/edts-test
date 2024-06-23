package com.test.tech.service.internal.impl;

import com.test.tech.entity.dao.*;
import com.test.tech.entity.dto.internal.BookingTicket;
import com.test.tech.entity.dto.request.BookingRequest;
import com.test.tech.entity.dto.response.BookingResponse;
import com.test.tech.entity.dto.response.DataResponse;
import com.test.tech.service.delegate.BookingDelegateService;
import com.test.tech.service.delegate.BookingDetailDelegateService;
import com.test.tech.service.internal.BookingInternalService;
import com.test.tech.service.internal.SoldTicketInternalService;
import com.test.tech.service.internal.TicketInternalService;
import com.test.tech.utils.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingInternalServiceImpl implements BookingInternalService {
    private final BookingDelegateService delegateService;
    private final BookingDetailDelegateService detailDelegateService;
    private final TicketInternalService ticketInternalService;
    private final SoldTicketInternalService soldTicketInternalService;

    @Override
    public ResponseEntity<DataResponse<Object>> createBooking(UserAccount userAccount, BookingRequest request) {
        //check validasi ke regulasi

        //generate data booking and ticket
        Booking savedBooking = this.savedBooking(BookingUtils.toBooking(userAccount, request));
        this.generateTickets(userAccount, request);
        this.saveBatchBookingDetail(BookingUtils.generateBookingDetails(userAccount, request, savedBooking.getBookingCode()));

        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", BookingUtils.toResponse(savedBooking, request.getTicket())));
    }

    @Override
    public ResponseEntity<DataResponse<Object>> findDetailBooking(String bookingCode) {
        Booking booking = this.findBookingByBookingCode(bookingCode);

        if (Objects.isNull(booking)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.getDetailBooking(booking)));
        }
    }

    @Override
    public ResponseEntity<DataResponse<Object>> findDetailBookingByOrder(String orderCode) {
        Booking booking = this.findBookingByOrderCode(orderCode);

        if (Objects.isNull(booking)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.getDetailBooking(booking)));
        }
    }

    @Override
    public ResponseEntity<DataResponse<Object>> updatePatchStatus(String bookingCode, String status) {
        Booking booking = this.findBookingByBookingCode(bookingCode);

        if (Objects.isNull(booking)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseUtils.toResponse(0, "not found", null));
        }

        if (status.equalsIgnoreCase("ISSUED")){
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.processIssued(booking, status)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ResponseUtils.toResponse(1, "Success", this.processUpdateStatus(booking, status)));
        }
    }

    private BookingResponse getDetailBooking(Booking booking){
        List<BookingDetail> details = this.findBookingDetailByBookingCode(booking.getBookingCode());
        List<BookingTicket> bookingTickets = this.generateBookingTicket(details);

        return BookingUtils.toResponse(booking, bookingTickets);
    }

    private BookingResponse processIssued(Booking booking, String status){
        Booking saved = this.savedBooking(BookingUtils.updateStatus(booking, status));
        List<BookingDetail> details = this.findBookingDetailByBookingCode(saved.getBookingCode());
        List<BookingTicket> bookingTickets = details.stream()
                .map(detail -> {
                    Ticket ticket = this.getTicketByTicketNumber(detail.getTicketNumber());
                    this.decreaseRemainingTicket(ticket);
                     return BookingUtils.toBookingTicket(detail, ticket);
                })
                .toList();

        return BookingUtils.toResponse(booking, bookingTickets);
    }

    private BookingResponse processUpdateStatus(Booking booking, String status){
        Booking saved = this.savedBooking(BookingUtils.updateStatus(booking, status));

        return this.getDetailBooking(saved);
    }

    private List<BookingTicket> generateBookingTicket(List<BookingDetail> details){
        return details.stream()
                .map(detail -> {
                    Ticket ticket = this.getTicketByTicketNumber(detail.getTicketNumber());

                    return BookingUtils.toBookingTicket(detail, ticket);
                })
                .toList();
    }

    private void decreaseRemainingTicket(Ticket ticket){
        SoldTicket soldTicket = this.getSpecificSoldTicket(ticket.getConcertCode(), ticket.getConcertTicketCode());
        SoldTicketUtils.decreaseRemainingTicket(soldTicket);
        SoldTicketDetail soldTicketDetail = SoldTicketUtils.toSoldTicketDetail(ticket, soldTicket);
        this.processIssuesTicket(soldTicket, soldTicketDetail);
    }

    private Booking savedBooking(Booking booking){
        return delegateService.saveBooking(booking);
    }

    private Booking findBookingByBookingCode(String bookingCode){
        return delegateService.findBookingByBookingCode(bookingCode);
    }

    private Booking findBookingByOrderCode(String orderCode){
        return delegateService.findBookingByOrderCode(orderCode);
    }

    private void saveBatchBookingDetail(List<BookingDetail> bookingDetails){
        detailDelegateService.saveBatch(bookingDetails);
    }

    private List<BookingDetail> findBookingDetailByBookingCode(String bookingCode){
        return detailDelegateService.findByBookingCode(bookingCode);
    }

    private SoldTicket getSpecificSoldTicket(String concertCode, String concertTicketCode){
        return soldTicketInternalService.getSpecificSoldTicket(concertCode, concertTicketCode);
    }

    private void processIssuesTicket(SoldTicket soldTicket, SoldTicketDetail soldTicketDetail){
        soldTicketInternalService.processIssuesTicket(soldTicket, soldTicketDetail);
    }

    private void generateTickets(UserAccount userAccount, BookingRequest request){
        ticketInternalService.generateTickets(userAccount, request);
    }

    private Ticket getTicketByTicketNumber(String ticketNumber){
        return ticketInternalService.getTicketByTicketNumber(ticketNumber);
    }
}
