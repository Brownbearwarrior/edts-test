package com.test.tech.repository;

import com.test.tech.entity.dao.SoldTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldTicketRepository extends JpaRepository<SoldTicket, String> {
    SoldTicket findByConcertCodeAndSoldTicketId (String concertCode, String soldTicketId);
    List<SoldTicket> findByConcertCode(String concertCode);
}
