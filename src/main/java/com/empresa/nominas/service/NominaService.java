package com.empresa.nominas.service;

import com.empresa.nominas.model.Nomina;
import com.empresa.nominas.repository.NominaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominaService {

    private final NominaRepository nominaRepository;

    public NominaService(NominaRepository nominaRepository) {
        this.nominaRepository = nominaRepository;
    }

    public List<Nomina> listarNominas() {
        return nominaRepository.findAll();
    }

    public Optional<Nomina> buscarPorId(Integer id) {
        return nominaRepository.findById(id);
    }

    public Optional<Nomina> buscarPorEmpleadoId(Integer empleadoId) {
        return nominaRepository.findByEmpleadoId(empleadoId);
    }

    public Nomina guardar(Nomina nomina) {
        return nominaRepository.save(nomina);
    }

    public void eliminar(Integer id) {
        nominaRepository.deleteById(id);
    }
}
