package com.empresa.nominas.rest;

import com.empresa.nominas.model.Empleado;
import com.empresa.nominas.service.EmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/empleados")
public class EmpleadoRestController {

    private final EmpleadoService empleadoService;

    // LISTAR TODOS
    @GetMapping
    public List<Empleado> listar() {
        return empleadoService.listarEmpleados();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscarPorId(@PathVariable Integer id) {
        return empleadoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // BUSCAR POR DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<Empleado> buscarPorDni(@PathVariable String dni) {
        return empleadoService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREAR
    @PostMapping
    public Empleado crear(@RequestBody Empleado empleado) {
        return empleadoService.guardar(empleado);
    }

    // ACTUALIZAR
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(
            @PathVariable Integer id,
            @RequestBody Empleado datos) {

        return empleadoService.buscarPorId(id)
                .map(empleado -> {
                    empleado.setNombre(datos.getNombre());
                    empleado.setDni(datos.getDni());
                    return ResponseEntity.ok(empleadoService.guardar(empleado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (empleadoService.buscarPorId(id).isPresent()) {
            empleadoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
