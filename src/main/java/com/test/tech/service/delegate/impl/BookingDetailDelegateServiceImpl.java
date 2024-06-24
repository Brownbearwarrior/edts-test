package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.BookingDetail;
import com.test.tech.repository.BookingDetailRepository;
import com.test.tech.service.delegate.BookingDetailDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingDetailDelegateServiceImpl implements BookingDetailDelegateService {
    private final BookingDetailRepository repository;

    @Override
    public List<BookingDetail> saveBatch(List<BookingDetail> bookingDetails) {
        return repository.saveAll(bookingDetails);
    }

    @Override
    public List<BookingDetail> findByBookingCode(String bookingCode) {
        return repository.findByBookingCode(bookingCode);
    }
}
