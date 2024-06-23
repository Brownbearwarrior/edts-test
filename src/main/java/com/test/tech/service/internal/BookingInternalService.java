package com.test.tech.service.internal;

import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.BookingRequest;
import com.test.tech.entity.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;

public interface BookingInternalService {
    ResponseEntity<DataResponse<Object>> createBooking(UserAccount userAccount, BookingRequest request);
    ResponseEntity<DataResponse<Object>> findDetailBooking(String bookingCode);
    ResponseEntity<DataResponse<Object>> findDetailBookingByOrder(String orderCode);
    ResponseEntity<DataResponse<Object>> updatePatchStatus(String orderCode, String status);
}
