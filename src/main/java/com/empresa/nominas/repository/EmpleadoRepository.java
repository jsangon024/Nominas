package com.empresa.nominas.repository;

import com.empresa.nominas.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    // Equivalente a tu antiguo buscarPorDNI()
    Optional<Empleado> findByDni(String dni);

}

