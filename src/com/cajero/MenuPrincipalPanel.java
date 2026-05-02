package com.cajero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalPanel extends JPanel {
    private JLabel saldoLabel;
    private JLabel usuarioLabel;
    private JButton consultarSaldoButton;
    private JButton retirarDineroButton;
    private JButton salirButton;
    private JLabel mensajeLabel;
    private Cuenta cuenta;
    private CajeroAutomatico ventanaPrincipal;

    public MenuPrincipalPanel(CajeroAutomatico ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Título
        JLabel tituloLabel = new JLabel("MENÚ PRINCIPAL - CAJERO AUTOMÁTICO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(tituloLabel, gbc);

        // Label de usuario
        usuarioLabel = new JLabel("Usuario: ");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(usuarioLabel, gbc);

        // Botón Consultar Saldo
        consultarSaldoButton = new JButton("CONSULTAR SALDO");
        consultarSaldoButton.setFont(new Font("Arial", Font.BOLD, 14));
        consultarSaldoButton.setBackground(new Color(0, 150, 75));
        consultarSaldoButton.setForeground(Color.WHITE);
        consultarSaldoButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(consultarSaldoButton, gbc);

        // Label de saldo
        saldoLabel = new JLabel("Saldo actual: $0.00");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 18));
        saldoLabel.setForeground(new Color(0, 100, 200));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(saldoLabel, gbc);

        // Botón Retirar Dinero
        retirarDineroButton = new JButton("RETIRAR DINERO");
        retirarDineroButton.setFont(new Font("Arial", Font.BOLD, 14));
        retirarDineroButton.setBackground(new Color(200, 100, 0));
        retirarDineroButton.setForeground(Color.WHITE);
        retirarDineroButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(retirarDineroButton, gbc);

        // Label de mensaje
        mensajeLabel = new JLabel("");
        mensajeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(mensajeLabel, gbc);

        // Botón Salir
        salirButton = new JButton("SALIR");
        salirButton.setFont(new Font("Arial", Font.BOLD, 14));
        salirButton.setBackground(new Color(200, 0, 0));
        salirButton.setForeground(Color.WHITE);
        salirButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(salirButton, gbc);

        // Acciones de botones
        consultarSaldoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarSaldo();
            }
        });

        retirarDineroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retirarDinero();
            }
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        usuarioLabel.setText("Usuario: " + cuenta.obtenerUsuario());
    }

    private void consultarSaldo() {
        double saldo = cuenta.obtenerSaldo();
        saldoLabel.setText("Saldo actual: $" + String.format("%.2f", saldo));
        mensajeLabel.setText("");
        mensajeLabel.setForeground(new Color(0, 100, 0));
    }

    private void retirarDinero() {
        String input = JOptionPane.showInputDialog(this,
                "Ingrese la cantidad a retirar (solo números enteros):",
                "Retirar Dinero",
                JOptionPane.PLAIN_MESSAGE);

        if (input == null) {
            return; // Usuario canceló
        }

        try {
            double cantidad = Double.parseDouble(input);

            // Validar que sea un número entero
            if (cantidad != (long) cantidad) {
                mensajeLabel.setText("⚠ Solo se aceptan números enteros");
                mensajeLabel.setForeground(Color.RED);
                return;
            }

            // Validar que sea positivo
            if (cantidad <= 0) {
                mensajeLabel.setText("⚠ La cantidad debe ser mayor a 0");
                mensajeLabel.setForeground(Color.RED);
                return;
            }

            // Intentar retirar
            if (cuenta.retirarDinero(cantidad)) {
                saldoLabel.setText("Saldo actual: $" + String.format("%.2f", cuenta.obtenerSaldo()));
                mensajeLabel.setText("✓ Retiro exitoso. Nuevos saldo: $" + String.format("%.2f", cuenta.obtenerSaldo()));
                mensajeLabel.setForeground(new Color(0, 150, 0));
                JOptionPane.showMessageDialog(this,
                        "✓ Retiro de $" + (long)cantidad + " realizado con éxito",
                        "Retiro Exitoso",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                mensajeLabel.setText("⚠ Fondos insuficientes. Saldo: $" + String.format("%.2f", cuenta.obtenerSaldo()));
                mensajeLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(this,
                        "⚠ No tiene saldo suficiente para retirar $" + (long)cantidad,
                        "Fondos Insuficientes",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            mensajeLabel.setText("⚠ Ingrese un número válido");
            mensajeLabel.setForeground(Color.RED);
        }
    }

    private void salir() {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            ventanaPrincipal.irAlLogin();
        }
    }
}
