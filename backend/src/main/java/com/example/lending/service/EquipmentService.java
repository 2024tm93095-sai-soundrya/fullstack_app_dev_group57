package com.example.lending.service;

import com.example.lending.model.Equipment;
import com.example.lending.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public Equipment add(Equipment e) { return equipmentRepository.save(e); }
    public List<Equipment> listAll() { return equipmentRepository.findAll(); }
    public Equipment get(Long id) { return equipmentRepository.findById(id).orElseThrow(); }
    public List<Equipment> searchByCategory(String cat) { return equipmentRepository.findByCategoryContainingIgnoreCase(cat); }
}
