package com.test.tech.service.internal;

import com.test.tech.entity.dao.UserAccount;
import com.test.tech.entity.dto.request.ConcertPage;
import com.test.tech.entity.dto.request.ConcertRequest;
import com.test.tech.entity.dto.response.DataResponse;
import org.springframework.http.ResponseEntity;

public interface ConcertInternalService {
    ResponseEntity<DataResponse<Object>> createConcert(UserAccount userAccount, ConcertRequest request);
    ResponseEntity<DataResponse<Object>> findDetailConcert(String concertCode);
    ResponseEntity<DataResponse<Object>> findConcert(ConcertPage page);
    ResponseEntity<DataResponse<Object>> update(String concertCode, UserAccount userAccount, ConcertRequest request);
    ResponseEntity<DataResponse<Object>> delete(String concertCode, UserAccount userAccount);
}
