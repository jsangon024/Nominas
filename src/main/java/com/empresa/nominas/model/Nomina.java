package com.empresa.nominas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "nomina")
public class Nomina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El salario no puede ser nulo")
    @Min(value = 0, message = "El salario no puede ser negativo")
    private Double salario;

    @Column(name = "fecha_pago")
    @NotNull(message = "La fecha no puede ser nula")
    private LocalDate fecha;

    // Relación con Empleado
    @OneToOne
    @JoinColumn(name = "empleado_id", nullable = false, unique = true)
    private Empleado empleado;

    // Constructores
    public Nomina() {}

    public Nomina(Integer id, Double salario, LocalDate fecha, Empleado empleado) {
        this.id = id;
        this.salario = salario;
        this.fecha = fecha;
        this.empleado = empleado;
    }

    // Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
