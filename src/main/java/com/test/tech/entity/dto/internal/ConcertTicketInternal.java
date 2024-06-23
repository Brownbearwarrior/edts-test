package com.test.tech.entity.dto.internal;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConcertTicketInternal {
    private String classTicket;
    private int availableTicket;
    private int price;
    private String currency;
}
