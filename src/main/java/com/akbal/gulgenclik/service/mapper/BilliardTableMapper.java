package com.akbal.gulgenclik.service.mapper;

import com.akbal.gulgenclik.domain.BilliardTable;
import com.akbal.gulgenclik.service.dto.BilliardTableDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BilliardTableMapper {

    private List<BilliardTableDTO> toBilliardTableDTOs(List<BilliardTable> tables){
        return tables.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }
    public BilliardTableDTO toDto(BilliardTable entity){
        if (entity == null) {
            return null;
        }
        return new BilliardTableDTO(entity);
    }

    public BilliardTable toEntity(BilliardTableDTO dto){
        return new BilliardTable(dto.getId(),dto.getName(),dto.getPrice(),dto.getUsing());
    }
}
