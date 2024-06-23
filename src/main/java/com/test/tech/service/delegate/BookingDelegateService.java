package com.test.tech.service.delegate;

import com.test.tech.entity.dao.Booking;

public interface BookingDelegateService {
    Booking saveBooking(Booking booking);
    Booking findBookingByBookingCode(String bookingCode);
    Booking findBookingByOrderCode(String orderCode);
}
