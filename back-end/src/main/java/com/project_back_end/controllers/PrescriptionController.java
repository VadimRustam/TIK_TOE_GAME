package com.project_back_end.controllers;

import com.project_back_end.models.Prescription;
import com.project_back_end.services.PrescriptionService;
import com.project_back_end.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final TokenService tokenService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService, TokenService tokenService) {
        this.prescriptionService = prescriptionService;
        this.tokenService = tokenService;
    }

    // ✅ Получить все рецепты
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    // ✅ Получить рецепт по ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        if (prescription == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(prescription);
    }

    // ✅ Добавить рецепт с валидацией и проверкой токена
    @PostMapping("/{token}")
    public ResponseEntity<?> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody Prescription prescription) {

        if (!tokenService.validateToken(token)) {
            return ResponseEntity.status(401).body("Invalid or expired token");
        }

        Prescription saved = prescriptionService.createPrescription(prescription);
        return ResponseEntity.ok(saved);
    }

    // ✅ Удалить рецепт
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build();
    }
}
