package com.test.tech.controller;

import com.test.tech.service.internal.BookingInternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookingController {
    private BookingInternalService internalService;

    //create
    //read
    //update
    //delete
}
