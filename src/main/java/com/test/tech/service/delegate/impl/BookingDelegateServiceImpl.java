package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.Booking;
import com.test.tech.repository.BookingRepository;
import com.test.tech.service.delegate.BookingDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingDelegateServiceImpl implements BookingDelegateService {
    private final BookingRepository repository;

    @Override
    public Booking saveBooking(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking findBookingByBookingCode(String bookingCode) {
        return repository.findByBookingCode(bookingCode);
    }

    @Override
    public Booking findBookingByOrderCode(String orderCode) {
        return repository.findByOrderCode(orderCode);
    }
}
