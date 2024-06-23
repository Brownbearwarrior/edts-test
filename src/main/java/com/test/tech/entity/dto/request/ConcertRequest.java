package com.test.tech.entity.dto.request;

import com.test.tech.entity.dto.internal.ConcertTicketInternal;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConcertRequest {
    private String concertName;
    private String mainPerformer;
    private String city;
    private String location;
    private Date heldDate;
    private List<ConcertTicketInternal> ticket;
}
