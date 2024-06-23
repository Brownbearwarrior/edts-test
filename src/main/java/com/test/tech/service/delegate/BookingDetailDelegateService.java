package com.test.tech.service.delegate;

import com.test.tech.entity.dao.BookingDetail;

import java.util.List;

public interface BookingDetailDelegateService {
    List<BookingDetail> saveBatch(List<BookingDetail> bookingDetails);
    List<BookingDetail> findByBookingCode(String bookingCode);
}
