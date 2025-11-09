package com.example.lending.repository;

import com.example.lending.model.Booking;
import com.example.lending.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);
}
