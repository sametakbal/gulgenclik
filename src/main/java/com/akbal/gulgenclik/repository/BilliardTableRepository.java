package com.akbal.gulgenclik.repository;

import com.akbal.gulgenclik.domain.BilliardTable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BilliardTableRepository extends JpaRepository<BilliardTable,Long>{
}
