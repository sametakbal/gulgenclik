package com.akbal.gulgenclik.service.mapper;

import com.akbal.gulgenclik.domain.Session;
import com.akbal.gulgenclik.service.dto.SessionDTO;
import org.springframework.stereotype.Service;

@Service
public class SessionMapper {
    private final UserMapper userMapper;
    private final BilliardTableMapper billiardTableMapper;

    private final BuffetContentMapper buffetContentMapper;

    public SessionMapper(UserMapper userMapper,
                         BilliardTableMapper billiardTableMapper,
                         BuffetContentMapper buffetContentMapper) {
        this.userMapper = userMapper;
        this.billiardTableMapper = billiardTableMapper;
        this.buffetContentMapper = buffetContentMapper;
    }

    public SessionDTO toDto(Session entity){
        SessionDTO dto = new SessionDTO();
        dto.setId(entity.getId());
        dto.setUser(userMapper.userToUserDTO(entity.getUser()));
        dto.setBilliardTable(billiardTableMapper.toDto(entity.getBilliardTable()));
        dto.setBuffetContents(buffetContentMapper.toDtos(entity.getBuffetContents()));
        return dto;
    }


}
