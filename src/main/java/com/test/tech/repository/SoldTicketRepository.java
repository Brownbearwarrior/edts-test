package com.test.tech.repository;

import com.test.tech.entity.dao.SoldTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldTicketRepository extends JpaRepository<SoldTicket, String> {
}
