package com.example.lending.service;

import com.example.lending.model.Booking;
import com.example.lending.model.Equipment;
import com.example.lending.model.User;
import com.example.lending.repository.BookingRepository;
import com.example.lending.repository.EquipmentRepository;
import com.example.lending.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    @Transactional
    public Booking requestBooking(Long userId, Long equipmentId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow();
        Equipment eq = equipmentRepository.findById(equipmentId).orElseThrow();

        Booking b = Booking.builder()
                .user(user)
                .equipment(eq)
                .quantity(quantity)
                .status("REQUESTED")
                .requestDate(LocalDate.now())
                .build();
        return bookingRepository.save(b);
    }

    @Transactional
    public Booking approveAndIssue(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        if (!"REQUESTED".equals(booking.getStatus())) throw new RuntimeException("Invalid status");
        Equipment eq = booking.getEquipment();
        if (eq.getAvailableQuantity() < booking.getQuantity()) {
            throw new RuntimeException("Not enough items available");
        }
        eq.setAvailableQuantity(eq.getAvailableQuantity() - booking.getQuantity());
        booking.setStatus("ISSUED");
        booking.setIssueDate(LocalDate.now());
        booking.setDueDate(LocalDate.now().plusDays(7));
        equipmentRepository.save(eq);
        return bookingRepository.save(booking);
    }

    @Transactional
    public Booking markReturned(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        Equipment eq = booking.getEquipment();
        eq.setAvailableQuantity(eq.getAvailableQuantity() + booking.getQuantity());
        booking.setStatus("RETURNED");
        booking.setReturnDate(LocalDate.now());
        equipmentRepository.save(eq);
        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return bookingRepository.findByUser(user);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
