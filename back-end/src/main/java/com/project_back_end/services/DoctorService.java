package com.project_back_end.services;

import com.project_back_end.models.Appointment;
import com.project_back_end.models.Doctor;
import com.project_back_end.repo.AppointmentRepository;
import com.project_back_end.repo.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис для обработки логики, связанной с врачами.
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Получить список всех врачей
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Получить врача по ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Сохранить нового врача
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Удалить врача
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Авторизация врача по email и паролю
    public Doctor login(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null && doctor.getPassword().equals(password)) {
            return doctor;
        }
        return null;
    }

    // Получить список приёмов (доступность) врача на конкретную дату
    public List<Appointment> getAvailabilityByDoctorAndDate(Long doctorId, String date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }
}
