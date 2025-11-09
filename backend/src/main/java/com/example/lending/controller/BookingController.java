package com.example.lending.controller;

import com.example.lending.dto.BookingRequestDto;
import com.example.lending.model.Booking;
import com.example.lending.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/request")
    public ResponseEntity<Booking> request(@RequestBody BookingRequestDto dto) {
        return ResponseEntity.ok(bookingService.requestBooking(dto.getUserId(), dto.getEquipmentId(), dto.getQuantity()));
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<Booking> approve(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.approveAndIssue(id));
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Booking> returned(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.markReturned(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Booking>> userBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(bookingService.getBookingsForUser(userId));
    }

    @GetMapping
    public ResponseEntity<List<Booking>> allBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
