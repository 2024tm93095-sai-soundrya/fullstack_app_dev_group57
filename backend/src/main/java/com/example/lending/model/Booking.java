package com.example.lending.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Equipment equipment;

    private int quantity;
    private String status; // REQUESTED, ISSUED, RETURNED, REJECTED
    private LocalDate requestDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String notes;
}
