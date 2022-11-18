package com.akbal.gulgenclik.web.rest;

import com.akbal.gulgenclik.common.ServiceResult;
import com.akbal.gulgenclik.service.BuffetContentService;
import com.akbal.gulgenclik.service.dto.BuffetContentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buffetcontent")
public class BuffetContentResource {
    private final BuffetContentService service;

    public BuffetContentResource(BuffetContentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BuffetContentDTO> createBuffetContent(@RequestBody BuffetContentDTO dto){
        ServiceResult<BuffetContentDTO> result = service.createBuffetContent(dto);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping
    public ResponseEntity<List<BuffetContentDTO>> getAllContents(){
        return ResponseEntity.ok(service.getBuffetContents().getData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        ServiceResult<Boolean> result = service.deleteBuffetContent(id);
        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        }
        return ResponseEntity.badRequest().build();
    }


}
