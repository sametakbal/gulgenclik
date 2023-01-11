package com.akbal.gulgenclik.web.rest;


import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.service.BilliardTableService;
import com.akbal.gulgenclik.service.dto.BilliardTableDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/billiard")
public class BilliardTableResource {
    private final BilliardTableService service;

    public BilliardTableResource(BilliardTableService service) {
        this.service = service;
    }

    @GetMapping("/american")
    public ResponseEntity<List<BilliardTableDTO>> getAmericanTables(){
        ServiceResult<List<BilliardTableDTO>> result = service.getBilliardTables("Amerikan");
        return ResponseEntity.ok(result.getData());
    }

    @GetMapping("/threeball")
    public ResponseEntity<List<BilliardTableDTO>> getThreeBallTables(){
        ServiceResult<List<BilliardTableDTO>> result = service.getBilliardTables("Top");
        return ResponseEntity.ok(result.getData());
    }
}
