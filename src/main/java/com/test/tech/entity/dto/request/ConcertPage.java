package com.test.tech.entity.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConcertPage {
    private String sort;
    private String order;
    private String search;
    private String searchBy;
    private Date start;
    private Date end;
}
