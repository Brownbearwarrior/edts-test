package com.test.tech.repository;

import com.test.tech.entity.dao.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationRepository extends JpaRepository<Regulation, Long> {
}
