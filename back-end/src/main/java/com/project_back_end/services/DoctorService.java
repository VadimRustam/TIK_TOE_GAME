package com.project_back_end.services;

import com.project_back_end.models.Doctor;
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
}
