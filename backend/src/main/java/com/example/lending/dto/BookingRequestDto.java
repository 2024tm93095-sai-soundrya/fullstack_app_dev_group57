package com.example.lending.dto;
import lombok.Data;

@Data
public class BookingRequestDto {
    private Long userId;
    private Long equipmentId;
    private int quantity;
}
