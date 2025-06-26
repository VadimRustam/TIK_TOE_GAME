package com.project_back_end.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String specialty;

    @ElementCollection
    private List<LocalDateTime> availableTimes;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public List<LocalDateTime> getAvailableTimes() { return availableTimes; }
    public void setAvailableTimes(List<LocalDateTime> availableTimes) { this.availableTimes = availableTimes; }
}
