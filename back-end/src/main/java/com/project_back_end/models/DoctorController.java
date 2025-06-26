package com.project.back_end.controllers;

import com.project_back_end.models.Appointment;
import com.project_back_end.models.Doctor;
import com.project_back_end.repositories.DoctorRepository;
import com.project_back_end.services.DoctorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ Новый метод для получения доступности врача
    @GetMapping("/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestParam Long doctorId,
            @RequestParam String date,
            @RequestHeader("Authorization") String token) {

        // TODO: Валидация токена и извлечение роли пользователя

        List<Appointment> appointments = doctorService.getAvailabilityByDoctorAndDate(doctorId, date);
        return ResponseEntity.ok(appointments);
    }
}
