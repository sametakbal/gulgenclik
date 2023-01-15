package com.akbal.gulgenclik.service;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.domain.BilliardTable;
import com.akbal.gulgenclik.domain.BuffetContent;
import com.akbal.gulgenclik.domain.Session;
import com.akbal.gulgenclik.repository.BilliardTableRepository;
import com.akbal.gulgenclik.repository.SessionRepository;
import com.akbal.gulgenclik.repository.UserRepository;
import com.akbal.gulgenclik.security.SecurityUtils;
import com.akbal.gulgenclik.service.dto.BuffetContentDTO;
import com.akbal.gulgenclik.service.dto.SessionDTO;
import com.akbal.gulgenclik.service.mapper.BuffetContentMapper;
import com.akbal.gulgenclik.service.mapper.SessionMapper;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;
    private final SessionMapper sessionMapper;
    private final BilliardTableRepository billiardTableRepository;
    private final BuffetContentMapper buffetContentMapper;

    public SessionService(
        SessionRepository sessionRepository,
        UserRepository userRepository,
        SessionMapper sessionMapper,
        BilliardTableRepository billiardTableRepository,
        BuffetContentMapper buffetContentMapper
    ) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        this.sessionMapper = sessionMapper;
        this.billiardTableRepository = billiardTableRepository;
        this.buffetContentMapper = buffetContentMapper;
    }

    public ServiceResult<SessionDTO> openSession(Long billiardTableId) {
        Session session = new Session();
        billiardTableRepository
            .findById(billiardTableId)
            .ifPresent(billiardTable -> {
                billiardTable.setUsing(true);
                session.setBilliardTable(billiardTable);
            });
        SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(session::setUser);
        Session saved = sessionRepository.save(session);
        return new ServiceResult<>(sessionMapper.toDto(saved));
    }

    public ServiceResult<SessionDTO> closeSession(Long billiardTableId) {
        Optional<BilliardTable> billiardTableOptional = billiardTableRepository.findById(billiardTableId);
        if (billiardTableOptional.isEmpty()) {
            return new ServiceResult<>(null);
        }
        BilliardTable billiardTable = billiardTableOptional.get();

        if (Boolean.FALSE.equals(billiardTable.getUsing()) && sessionRepository.existsByBilliardTableIdAndEndDateIsNull(billiardTableId)) {
            return new ServiceResult<>(null);
        }
        billiardTable.setUsing(false);
        billiardTableRepository.save(billiardTable);
        Optional<Session> sessionOptional = sessionRepository.findFirstByBilliardTableIdAndEndDateIsNull(billiardTableId);
        if (sessionOptional.isEmpty()) {
            return new ServiceResult<>(null);
        }
        Session session = sessionOptional.get();
        session.setEndDate(ZonedDateTime.now());
        Float sessionTimeCost = calculateSessionTimeCost(
            session.getCreatedDate().atZone(session.getEndDate().getZone()),
            session.getEndDate(),
            billiardTable.getPrice()
        );
        for (BuffetContent bf : session.getBuffetContents()) {
            sessionTimeCost += bf.getPrice();
        }
        session.setPrice(sessionTimeCost);
        return new ServiceResult<>(sessionMapper.toDto(sessionRepository.save(session)));
    }

    private Float calculateSessionTimeCost(ZonedDateTime startTime, ZonedDateTime endTime, Float tablePrice) {
        long timeSpent = (endTime.toEpochSecond() - startTime.toEpochSecond()) / 60;
        return (float) Math.ceil((tablePrice * timeSpent) / 60);
    }

    public ServiceResult<SessionDTO> getExistingSession(Long billiardTableId) {
        Optional<Session> sessionOptional = sessionRepository.findFirstByBilliardTableIdAndEndDateIsNull(billiardTableId);

        if (sessionOptional.isPresent()) {
            return new ServiceResult<>(sessionMapper.toDto(sessionOptional.get()));
        }
        Session session = new Session();
        billiardTableRepository.findById(billiardTableId).ifPresent(session::setBilliardTable);
        SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(session::setUser);
        return new ServiceResult<>(sessionMapper.toDto(session));
    }

    public ServiceResult<SessionDTO> getSession(Long billiardTableId) {
        if (sessionRepository.existsByBilliardTableIdAndEndDateIsNull(billiardTableId)) {
            return getExistingSession(billiardTableId);
        }
        return openSession(billiardTableId);
    }

    public ServiceResult<SessionDTO> addBuffetContent(Long sessionId, BuffetContentDTO contentDTO) {
        Optional<Session> byId = sessionRepository.findById(sessionId);
        if (byId.isEmpty()) {
            return new ServiceResult<>(null);
        }
        Session session = byId.get();
        session.getBuffetContents().add(buffetContentMapper.toEntity(contentDTO));
        sessionRepository.save(session);
        return new ServiceResult<>(sessionMapper.toDto(sessionRepository.save(session)));
    }

    public ServiceResult<SessionDTO> removeBuffetContent(Long sessionId, List<BuffetContentDTO> dtos) {
        Optional<Session> byId = sessionRepository.findById(sessionId);
        if (byId.isEmpty()) {
            return new ServiceResult<>(null);
        }
        Session session = byId.get();
        session.setBuffetContents(buffetContentMapper.toEntities(dtos));
        sessionRepository.save(session);
        return new ServiceResult<>(sessionMapper.toDto(sessionRepository.save(session)));
    }

    public Float getTotalCost(Long billiardTableId) {
        Optional<Session> sessionOptional = sessionRepository.findFirstByBilliardTableIdAndEndDateIsNull(billiardTableId);
        if (sessionOptional.isEmpty()) {
            return null;
        }
        Session session = sessionOptional.get();
        Float totalCost = calculateSessionTimeCost(
            session.getCreatedDate().atZone(ZonedDateTime.now().getZone()),
            ZonedDateTime.now(),
            session.getBilliardTable().getPrice()
        );
        for (BuffetContent bf : session.getBuffetContents()) {
            totalCost += bf.getPrice();
        }
        return totalCost;
    }

    public Integer getSpendMinutes(Long billiardTableId) {
        Optional<Session> sessionOptional = sessionRepository.findFirstByBilliardTableIdAndEndDateIsNull(billiardTableId);
        if (sessionOptional.isEmpty()) {
            return null;
        }
        Session session = sessionOptional.get();

        return (int) (
            (ZonedDateTime.now().toEpochSecond() - session.getCreatedDate().atZone(ZonedDateTime.now().getZone()).toEpochSecond()) / 60
        );
    }
}
