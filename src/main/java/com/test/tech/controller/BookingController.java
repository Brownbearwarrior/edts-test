package com.test.tech.controller;

import com.test.tech.entity.constant.DummyObject;
import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.BookingRequest;
import com.test.tech.entity.dto.response.DataResponse;
import com.test.tech.service.internal.BookingInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingInternalService internalService;

    @PostMapping("/order")
    public ResponseEntity<DataResponse<Object>> createBooking(@RequestBody BookingRequest request){
        //dummy header for username
        UserAccount user = DummyObject.dummyUser();

        return internalService.createBooking(user, request);
    }

    @GetMapping("/find/{bookingCode}")
    public ResponseEntity<DataResponse<Object>> findDetailBooking(@PathVariable String bookingCode){
        return internalService.findDetailBooking(bookingCode);
    }

    @GetMapping("/find/order/{orderCode}")
    public ResponseEntity<DataResponse<Object>> findDetailBookingByOrder(@PathVariable String orderCode){
        return internalService.findDetailBookingByOrder(orderCode);
    }

    @PatchMapping("/update-status/{orderCode}/{status}")
    public ResponseEntity<DataResponse<Object>> updatePatchStatus(@PathVariable String orderCode, @PathVariable String status){
        return internalService.updatePatchStatus(orderCode, status);
    }
}
