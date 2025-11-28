package com.empresa.nominas.controller;

import com.empresa.nominas.service.EmpleadoService;
import com.empresa.nominas.service.NominaService;
import com.empresa.nominas.utils.HelperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nominas")
public class NominaController {

    private final NominaService nominaService;
    private final EmpleadoService empleadoService;

    // FORMULARIO BUSCAR SALARIO
    @GetMapping("/buscarSalario")
    public String buscarSalarioForm(Model model) {
        model.addAttribute("titulo", "Buscar Salario");
        return "nominas/buscarSalario";
    }

    // PROCESAR BÚSQUEDA SALARIO POR DNI
    @PostMapping("/buscarSalario")
    public String buscarSalario(@RequestParam String dni, Model model) {

        var empleadoOpt = empleadoService.buscarPorDni(dni);

        if (empleadoOpt.isEmpty()) {
            model.addAttribute("mensaje", "No existe un empleado con ese DNI");
            return "nominas/buscarSalario";
        }

        var empleado = empleadoOpt.get();

        var nominaOpt = nominaService.buscarPorEmpleadoId(empleado.getId());

        if (nominaOpt.isEmpty()) {
            model.addAttribute("mensaje", "No existe nómina para ese empleado");
            return "nominas/buscarSalario";
        }

        var nomina= nominaOpt.get();
        var salario = HelperUtils.formatearSalario(nominaOpt.get().getSalario());

        model.addAttribute("nomina", nomina);
        model.addAttribute("dni", dni);
        model.addAttribute("salario", salario);

        return "nominas/buscarSalario";
    }
}

