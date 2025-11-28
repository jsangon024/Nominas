package com.empresa.nominas.repository;

import com.empresa.nominas.model.Nomina;
import com.empresa.nominas.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominaRepository extends JpaRepository<Nomina, Integer> {

    // Buscar nómina por id empleado
        //List<Nomina> findByEmpleadoId(Integer empleadoId);
    Optional<Nomina> findByEmpleadoId(Integer empleadoId);



}

