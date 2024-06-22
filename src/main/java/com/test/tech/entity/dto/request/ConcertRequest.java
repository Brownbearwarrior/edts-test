package com.test.tech.entity.dto.request;

import com.test.tech.entity.dto.internal.ConcertTicket;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertRequest {
    private String concertName;
    private String mainPerformer;
    private String city;
    private String location;
    private Date heldDate;
    private ConcertTicket ticket;
}
