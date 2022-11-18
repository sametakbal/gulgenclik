package com.akbal.gulgenclik.service;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.domain.BuffetContent;
import com.akbal.gulgenclik.repository.BuffetContentRepository;
import com.akbal.gulgenclik.service.dto.BuffetContentDTO;
import com.akbal.gulgenclik.service.mapper.BuffetContentMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BuffetContentService {
    private final BuffetContentRepository buffetContentRepository;
    private final BuffetContentMapper buffetContentMapper;

    public BuffetContentService(BuffetContentRepository buffetContentRepository,
                                BuffetContentMapper buffetContentMapper) {
        this.buffetContentRepository = buffetContentRepository;
        this.buffetContentMapper = buffetContentMapper;
    }

    public ServiceResult<BuffetContentDTO> createBuffetContent(BuffetContentDTO dto){
        if (dto == null) {
            return new ServiceResult<>(null);
        }
        BuffetContent buffetContent = buffetContentMapper.toEntity(dto);
        BuffetContent saved = buffetContentRepository.save(buffetContent);
        return new ServiceResult<>(buffetContentMapper.toDto(saved));
    }

    public ServiceResult<List<BuffetContentDTO>> getBuffetContents(){
        List<BuffetContent> contents = buffetContentRepository.findAllByOrderById();
        return new ServiceResult<>(buffetContentMapper.toDtos(contents));
    }

}
