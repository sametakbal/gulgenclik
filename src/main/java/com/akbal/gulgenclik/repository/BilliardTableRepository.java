package com.akbal.gulgenclik.repository;

import com.akbal.gulgenclik.domain.BilliardTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilliardTableRepository extends JpaRepository<BilliardTable, Long> {
    List<BilliardTable> findAllByNameContainsOrderById(String term);
}
