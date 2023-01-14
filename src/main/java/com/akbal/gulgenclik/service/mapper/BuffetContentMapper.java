package com.akbal.gulgenclik.service.mapper;

import com.akbal.gulgenclik.domain.BuffetContent;
import com.akbal.gulgenclik.service.dto.BuffetContentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuffetContentMapper {

    public BuffetContentDTO toDto(BuffetContent entity) {
        return entity == null ? null : new BuffetContentDTO(entity);
    }

    public List<BuffetContentDTO> toDtos(List<BuffetContent> contents) {
        return contents == null ? null : contents.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BuffetContent toEntity(BuffetContentDTO dto) {
        return dto == null ? null : new BuffetContent(dto.getId(), dto.getName(), dto.getPrice());
    }
}
