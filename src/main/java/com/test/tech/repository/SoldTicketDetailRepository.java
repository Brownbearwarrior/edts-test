package com.test.tech.repository;

import com.test.tech.entity.dao.SoldTicketDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldTicketDetailRepository extends JpaRepository<SoldTicketDetail, String> {
}
