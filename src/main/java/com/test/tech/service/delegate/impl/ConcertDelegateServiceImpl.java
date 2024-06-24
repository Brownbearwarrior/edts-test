package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.Concert;
import com.test.tech.repository.ConcertRepository;
import com.test.tech.service.delegate.ConcertDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConcertDelegateServiceImpl implements ConcertDelegateService {
    private final ConcertRepository repository;

    @Override
    public Concert saveConcert(Concert concert) {
        return repository.save(concert);
    }

    @Override
    public Concert findConcert(String concertCode) {
        return repository.findByConcertCode(concertCode);
    }

    @Override
    public void deleteConcert(Concert concert) {
        repository.delete(concert);
    }
}
