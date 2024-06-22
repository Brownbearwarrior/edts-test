package com.test.tech.entity.dto.response;

import com.test.tech.entity.dto.request.BookingRequest;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse extends BookingRequest {
    private String bookingCode;
    private String bookingStatus;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
}
