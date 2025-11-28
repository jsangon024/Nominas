package com.empresa.nominas.controller;

import com.empresa.nominas.model.Empleado;
import com.empresa.nominas.service.EmpleadoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    // ============================
    // LISTAR EMPLEADOS
    // ============================
    @GetMapping("/listar")
    public String listar(Model model) {
        model.addAttribute("empleados", empleadoService.listarEmpleados());
        return "empleados/listar";
    }

    // ============================
    // FORMULARIO BUSCAR POR DNI
    // ============================
    @GetMapping("/buscar")
    public String mostrarFormularioBusqueda() {
        return "empleados/buscar";
    }

    @PostMapping("/buscar")
    public String buscarPorDni(@RequestParam("dni") String dni, Model model) {

        Optional<Empleado> empleadoOpt = empleadoService.buscarPorDni(dni);

        if (empleadoOpt.isPresent()) {
            model.addAttribute("empleado", empleadoOpt.get());
        } else {
            model.addAttribute("mensaje", "No existe un empleado con el DNI indicado.");
        }

        return "empleados/buscar";
    }

    // ============================
    // FORMULARIO EDITAR EMPLEADO
    // ============================
    @GetMapping("/editar/{id}")
    public String mostrarEditar(@PathVariable Integer id, Model model) {

        Optional<Empleado> empleadoOpt = empleadoService.buscarPorId(id);

        if (empleadoOpt.isPresent()) {
            model.addAttribute("empleado", empleadoOpt.get());
            return "empleados/editar";
        } else {
            model.addAttribute("mensaje", "Empleado no encontrado");
            return "redirect:/empleados/listar";
        }
    }

    // ============================
    // GUARDAR (EDITAR)
    // ============================
    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Empleado empleado, Model model) {

        Empleado actualizado = empleadoService.guardar(empleado); // ✔ usa el método real

        model.addAttribute("empleado", actualizado);
        model.addAttribute("mensaje", "Empleado actualizado correctamente");

        return "empleados/editar";
    }

    // ============================
    // ELIMINAR
    // ============================
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        empleadoService.eliminar(id);
        return "redirect:/empleados/listar";
    }
}
