package com.test.tech.entity.dto.response;

import com.test.tech.entity.dto.request.BookingRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BookingResponse extends BookingRequest {
    private String bookingCode;
    private String orderCode;
    private String bookingStatus;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
}
