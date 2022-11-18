package com.akbal.gulgenclik.repository;

import com.akbal.gulgenclik.domain.BuffetContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuffetContentRepository extends JpaRepository<BuffetContent,Long> {
    List<BuffetContent> findAllByOrderById();
}
