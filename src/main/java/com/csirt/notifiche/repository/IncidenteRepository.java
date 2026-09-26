package com.csirt.notifiche.repository;

import com.csirt.notifiche.model.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepository extends JpaRepository<Incidente, Long> {
    // I metodi standard (save, findById, findAll, delete) sono già implementati in automatico
}