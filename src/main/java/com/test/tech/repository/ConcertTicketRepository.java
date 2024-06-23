package com.test.tech.repository;

import com.test.tech.entity.dao.ConcertTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertTicketRepository extends JpaRepository<ConcertTicket, String> {
}
