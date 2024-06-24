package com.test.tech.repository;

import com.test.tech.entity.dao.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Booking findByBookingCode(String bookingCode);
    Booking findByOrderCode(String orderCode);
}
