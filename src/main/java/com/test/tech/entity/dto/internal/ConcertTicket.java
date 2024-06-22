package com.test.tech.entity.dto.internal;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertTicket {
    private String classTicket;
    private int availableTicket;
    private int price;
    private String currency;
}
