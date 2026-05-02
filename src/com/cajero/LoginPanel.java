package com.cajero;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JTextField usuarioField;
    private JPasswordField pinField;
    private JButton loginButton;
    private JLabel mensajeLabel;
    private JLabel intentosLabel;
    private int intentos = 3;
    private Cuenta cuenta;
    private CajeroAutomatico ventanaPrincipal;

    public LoginPanel(CajeroAutomatico ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Título
        JLabel tituloLabel = new JLabel("CAJERO AUTOMÁTICO - LOGIN");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(tituloLabel, gbc);

        // Label y campo de Usuario
        JLabel usuarioLabel = new JLabel("Usuario:");
        usuarioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usuarioLabel, gbc);

        usuarioField = new JTextField(20);
        usuarioField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usuarioField, gbc);

        // Label y campo de PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(pinLabel, gbc);

        pinField = new JPasswordField(20);
        pinField.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(pinField, gbc);

        // Label de intentos
        intentosLabel = new JLabel("Intentos restantes: " + intentos);
        intentosLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        intentosLabel.setForeground(new Color(0, 100, 0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(intentosLabel, gbc);

        // Botón Login
        loginButton = new JButton("INICIAR SESIÓN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(0, 120, 200));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(250, 50));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        // Label de mensaje
        mensajeLabel = new JLabel("");
        mensajeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mensajeLabel.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(mensajeLabel, gbc);

        // Acciones
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });

        pinField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarLogin();
            }
        });
    }

    private void verificarLogin() {
        String usuario = usuarioField.getText().trim();
        String pin = new String(pinField.getPassword());

        if (usuario.isEmpty()) {
            mensajeLabel.setText("Por favor ingrese un usuario");
            mensajeLabel.setForeground(Color.RED);
            return;
        }

        cuenta = new Cuenta(usuario);

        if (cuenta.validarPIN(pin)) {
            mensajeLabel.setText("✓ ACCESO PERMITIDO");
            mensajeLabel.setForeground(new Color(0, 150, 0));
            loginButton.setEnabled(false);
            usuarioField.setEnabled(false);
            pinField.setEnabled(false);

            JOptionPane.showMessageDialog(this,
                    "¡Bienvenido " + usuario + "!\nSesión iniciada correctamente.",
                    "Acceso Permitido",
                    JOptionPane.INFORMATION_MESSAGE);

            // Pasar la cuenta al menú principal
            ventanaPrincipal.irAlMenuPrincipal(cuenta);

        } else {
            intentos--;

            if (intentos > 0) {
                mensajeLabel.setText("PIN incorrecto");
                mensajeLabel.setForeground(Color.RED);
                intentosLabel.setText("Intentos restantes: " + intentos);
                pinField.setText("");
                pinField.requestFocus();
            } else {
                mensajeLabel.setText("¡ACCESO BLOQUEADO!");
                mensajeLabel.setForeground(new Color(200, 0, 0));
                intentosLabel.setText("Intentos agotados");
                intentosLabel.setForeground(new Color(200, 0, 0));
                loginButton.setEnabled(false);
                usuarioField.setEnabled(false);
                pinField.setEnabled(false);

                JOptionPane.showMessageDialog(this,
                        "¡ACCESO BLOQUEADO!\nHa excedido el número de intentos.",
                        "Acceso Denegado",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void resetearPanel() {
        usuarioField.setText("");
        pinField.setText("");
        mensajeLabel.setText("");
        intentosLabel.setText("Intentos restantes: 3");
        intentosLabel.setForeground(new Color(0, 100, 0));
        loginButton.setEnabled(true);
        usuarioField.setEnabled(true);
        pinField.setEnabled(true);
        intentos = 3;
    }
}
