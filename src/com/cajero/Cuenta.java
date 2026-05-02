package com.cajero;

public class Cuenta {
    private String usuario;
    private double saldo;
    private static final String PIN_CORRECTO = "1234";
    private static final double SALDO_INICIAL = 100.0;

    public Cuenta(String usuario) {
        this.usuario = usuario;
        this.saldo = SALDO_INICIAL;
    }

    // Validar PIN
    public boolean validarPIN(String pin) {
        return pin.equals(PIN_CORRECTO);
    }

    // Obtener saldo
    public double obtenerSaldo() {
        return saldo;
    }

    // Retirar dinero
    public boolean retirarDinero(double cantidad) {
        if (cantidad <= 0) {
            return false; // No permite cantidades negativas o cero
        }
        if (cantidad > saldo) {
            return false; // No permite retirar más que el saldo
        }
        saldo -= cantidad;
        return true;
    }

    // Obtener usuario
    public String obtenerUsuario() {
        return usuario;
    }

    // Resetear saldo (para pruebas)
    public void resetearSaldo() {
        this.saldo = SALDO_INICIAL;
    }
}
