package com.empresa.nominas.service;

import com.empresa.nominas.model.Empleado;
import com.empresa.nominas.repository.EmpleadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    // Obtener todos
    public List<Empleado> listarEmpleados() {
        return empleadoRepository.findAll();
    }

    // Buscar por ID
    public Optional<Empleado> buscarPorId(Integer id) {
        return empleadoRepository.findById(id);
    }

    // Buscar por DNI
    public Optional<Empleado> buscarPorDni(String dni) {
        return empleadoRepository.findByDni(dni);
    }

    // Guardar / Actualizar
    public Empleado guardar(Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // Eliminar
    public void eliminar(Integer id) {
        empleadoRepository.deleteById(id);
    }
}

