package com.empresa.nominas.rest;

import com.empresa.nominas.model.Nomina;
import com.empresa.nominas.service.NominaService;
import com.empresa.nominas.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nominas")
public class NominaRestController {

    private final NominaService nominaService;
    private final EmpleadoService empleadoService;

    // LISTAR TODAS
    @GetMapping
    public List<Nomina> listar() {
        return nominaService.listarNominas();
    }

    // BUSCAR POR ID NOMINA
    @GetMapping("/{id}")
    public ResponseEntity<Nomina> buscarPorId(@PathVariable Integer id) {
        return nominaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BUSCAR POR ID EMPLEADO
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Nomina> buscarPorEmpleado(@PathVariable Integer idEmpleado) {
        return nominaService.buscarPorEmpleadoId(idEmpleado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR NOMINA
    @PostMapping("/empleado/{idEmpleado}")
    public ResponseEntity<Nomina> crear(
            @PathVariable Integer idEmpleado,
            @RequestBody Nomina nomina) {

        return empleadoService.buscarPorId(idEmpleado)
                .map(empleado -> {
                    nomina.setEmpleado(empleado);
                    return ResponseEntity.ok(nominaService.guardar(nomina));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ACTUALIZAR NOMINA
    @PutMapping("/{id}")
    public ResponseEntity<Nomina> actualizar(
            @PathVariable Integer id,
            @RequestBody Nomina datos) {

        return nominaService.buscarPorId(id)
                .map(nomina -> {
                    nomina.setSalario(datos.getSalario());
                    nomina.setFecha(datos.getFecha());
                    return ResponseEntity.ok(nominaService.guardar(nomina));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR NOMINA
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (nominaService.buscarPorId(id).isPresent()) {
            nominaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

