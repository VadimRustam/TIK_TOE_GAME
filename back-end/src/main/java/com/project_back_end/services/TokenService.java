package com.project_back_end.services;

import com.project_back_end.models.Appointment;
import com.project_back_end.repo.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
package com.project_back_end.services;

import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Сервис для генерации и валидации токенов авторизации.
 */
@Service
public class TokenService {

    // Генерация нового токена
    public String generateToken(String userEmail) {
        // В реальном приложении это JWT, здесь — заглушка
        return UUID.randomUUID().toString() + "-" + userEmail.hashCode();
    }

    // Проверка токена (заглушка)
    public boolean validateToken(String token) {
        // Просто пример, не использовать в продакшене
        return token != null && token.length() > 10;
    }
}

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Получить все приёмы
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Найти приём по ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Сохранить/создать новый приём
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Удалить приём по ID
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    // Обновить приём
    public Appointment updateAppointment(Long id, Appointment updated) {
        return appointmentRepository.findById(id).map(app -> {
            app.setAppointmentTime(updated.getAppointmentTime());
            app.setDoctor(updated.getDoctor());
            app.setPatient(updated.getPatient());
            return appointmentRepository.save(app);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }
}
