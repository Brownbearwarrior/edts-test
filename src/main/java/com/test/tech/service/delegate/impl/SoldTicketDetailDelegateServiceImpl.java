package com.test.tech.service.delegate.impl;

import com.test.tech.entity.dao.SoldTicketDetail;
import com.test.tech.repository.SoldTicketDetailRepository;
import com.test.tech.service.delegate.SoldTicketDetailDelegateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SoldTicketDetailDelegateServiceImpl implements SoldTicketDetailDelegateService {
    private final SoldTicketDetailRepository repository;

    @Override
    public SoldTicketDetail saveDetail(SoldTicketDetail soldTicketDetail) {
        return repository.save(soldTicketDetail);
    }
}
