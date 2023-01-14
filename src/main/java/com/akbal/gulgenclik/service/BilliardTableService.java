package com.akbal.gulgenclik.service;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.domain.BilliardTable;
import com.akbal.gulgenclik.repository.BilliardTableRepository;
import com.akbal.gulgenclik.service.dto.BilliardTableDTO;
import com.akbal.gulgenclik.service.mapper.BilliardTableMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BilliardTableService {
    private final Logger log = LoggerFactory.getLogger(BilliardTableService.class);
    private final BilliardTableRepository billiardTableRepository;
    private final BilliardTableMapper mapper;

    public BilliardTableService(BilliardTableRepository billiardTableRepository,
                                BilliardTableMapper mapper) {
        this.billiardTableRepository = billiardTableRepository;
        this.mapper = mapper;
    }

    public ServiceResult<BilliardTableDTO> createBilliardTable(BilliardTableDTO dto) {
        BilliardTable billiardTable = mapper.toEntity(dto);
        BilliardTable saved = billiardTableRepository.save(billiardTable);
        log.info("Billiard Table Created");
        return new ServiceResult<>(mapper.toDto(saved));
    }

    public ServiceResult<List<BilliardTableDTO>> getBilliardTables(String term){
        List<BilliardTable> allByNameContains = billiardTableRepository.findAllByNameContainsOrderById(term);
        return new ServiceResult<>(mapper.toBilliardTableDTOs(allByNameContains));
    }



}
