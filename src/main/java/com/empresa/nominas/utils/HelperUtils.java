package com.empresa.nominas.utils;

import java.text.DecimalFormat;

public class HelperUtils {

    /** Formateador de decimales para salarios (2 decimales). */
    private static final DecimalFormat df = new DecimalFormat("#.00");

    /**
     * Convierte un String a Integer de forma segura.
     * Si el valor no es numérico, devuelve null.
     */
    public static Integer toInteger(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Convierte un String a Double de forma segura.
     * Si el valor no es numérico, devuelve null.
     */
    public static Double toDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Valida si un campo String está vacío o es nulo.
     */
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }


    /**
     * Valida que un DNI tenga un formato razonable.
     * (8 números + letra final)
     */
    public static boolean validarDNI(String dni) {
        if (dni == null) return false;

        return dni.matches("^[0-9]{8}[A-Za-z]$");
    }

    public static String formatearSalario(double salario) {
        return df.format(salario) + " €";
    }
}
