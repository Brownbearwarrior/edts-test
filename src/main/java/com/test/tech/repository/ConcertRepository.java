package com.test.tech.repository;

import com.test.tech.entity.dao.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, String> {
    Concert findByConcertCode(String concertCode);
}
