package com.project_back_end.repo;

import com.project_back_end.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для работы с сущностью Patient.
 * Расширяет JpaRepository для получения CRUD-операций.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // Поиск пациента по email (пример кастомного запроса)
    Patient findByEmail(String email);

    // Поиск всех пациентов по фамилии
    List<Patient> findAllByLastName(String lastName);

    // Здесь можно добавить другие кастомные методы при необходимости
}
