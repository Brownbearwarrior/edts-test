package com.test.tech.entity.dto.response;

import com.test.tech.entity.dto.request.ConcertRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ConcertResponse extends ConcertRequest {
    private String concertCode;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
}
