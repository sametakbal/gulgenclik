package com.akbal.gulgenclik.web.rest;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.service.SessionService;
import com.akbal.gulgenclik.service.dto.BuffetContentDTO;
import com.akbal.gulgenclik.service.dto.SessionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class SessionResource {

    private final SessionService service;

    public SessionResource(SessionService service) {
        this.service = service;
    }

    @GetMapping("/billiard/{billiardTableId}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable Long billiardTableId) {
        ServiceResult<SessionDTO> result = service.getExistingSession(billiardTableId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/billiard/{billiardTableId}")
    public ResponseEntity<SessionDTO> openSession(@PathVariable Long billiardTableId) {
        ServiceResult<SessionDTO> result = service.openSession(billiardTableId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/billiard/close/{billiardTableId}")
    public ResponseEntity<SessionDTO> closeSession(@PathVariable Long billiardTableId) {
        ServiceResult<SessionDTO> result = service.closeSession(billiardTableId);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/buffetcontent/add/{sessionId}")
    public ResponseEntity<SessionDTO> addBuffetContent(@PathVariable Long sessionId, @RequestBody BuffetContentDTO buffetContentDTO) {
        ServiceResult<SessionDTO> result = service.addBuffetContent(sessionId, buffetContentDTO);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }
}
