package com.akbal.gulgenclik.service;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.domain.Session;
import com.akbal.gulgenclik.repository.BilliardTableRepository;
import com.akbal.gulgenclik.repository.SessionRepository;
import com.akbal.gulgenclik.repository.UserRepository;
import com.akbal.gulgenclik.security.SecurityUtils;
import com.akbal.gulgenclik.service.dto.SessionDTO;
import com.akbal.gulgenclik.service.mapper.SessionMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionMapper sessionMapper;
    private final BilliardTableRepository billiardTableRepository;

    public SessionService(SessionRepository sessionRepository,
                          UserRepository userRepository,
                          SessionMapper sessionMapper,
                          BilliardTableRepository billiardTableRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.sessionMapper = sessionMapper;
        this.billiardTableRepository = billiardTableRepository;
    }

    public ServiceResult<SessionDTO> openSession(Long billiardTableId) {
        Session session = new Session();
        billiardTableRepository
            .findById(billiardTableId)
            .ifPresent(billiardTable -> {
                billiardTable.setUsing(true);
                session.setBilliardTable(billiardTable);
            });
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(session::setUser);
        Session saved = sessionRepository.save(session);
        return new ServiceResult<>(sessionMapper.toDto(saved));
    }

    public ServiceResult<SessionDTO> getExistingSession(Long billiardTableId){
        Optional<Session> sessionOptional = sessionRepository.findFirstByBilliardTableIdAndEndDateIsNull(billiardTableId);

        if (sessionOptional.isPresent()) {
            return new ServiceResult<>(sessionMapper.toDto(sessionOptional.get()));
        }
        Session session = new Session();
        billiardTableRepository
            .findById(billiardTableId)
            .ifPresent(session::setBilliardTable);
        SecurityUtils
            .getCurrentUserLogin()
            .flatMap(userRepository::findOneByLogin)
            .ifPresent(session::setUser);
        return new ServiceResult<>(sessionMapper.toDto(session));
    }

    public ServiceResult<SessionDTO> getSession(Long billiardTableId){
        if (sessionRepository.existsByBilliardTableIdAndEndDateIsNull(billiardTableId)) {
            return getExistingSession(billiardTableId);
        }
        return openSession(billiardTableId);
    }




}
