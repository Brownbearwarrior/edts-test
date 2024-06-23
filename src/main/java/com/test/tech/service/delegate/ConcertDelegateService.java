package com.test.tech.service.delegate;

import com.test.tech.entity.dao.Concert;

public interface ConcertDelegateService {
    Concert saveConcert(Concert concert);
    Concert findConcert(String concertCode);
    void deleteConcert(Concert concert);
}
