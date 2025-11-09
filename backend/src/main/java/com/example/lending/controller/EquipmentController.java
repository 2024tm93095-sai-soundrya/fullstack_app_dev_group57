package com.example.lending.controller;

import com.example.lending.model.Equipment;
import com.example.lending.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentService equipmentService;

    @GetMapping
    public ResponseEntity<List<Equipment>> list(@RequestParam(required=false) String category) {
        if (category != null) return ResponseEntity.ok(equipmentService.searchByCategory(category));
        return ResponseEntity.ok(equipmentService.listAll());
    }

    @PostMapping
    public ResponseEntity<Equipment> add(@RequestBody Equipment e) {
        return ResponseEntity.ok(equipmentService.add(e));
    }
}
