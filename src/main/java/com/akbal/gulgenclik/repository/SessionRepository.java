package com.akbal.gulgenclik.repository;

import com.akbal.gulgenclik.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    Optional<Session> findFirstByBilliardTableIdAndEndDateIsNull(Long billiardTableId);
    boolean existsByBilliardTableIdAndEndDateIsNull(Long billiardTableId);
}
