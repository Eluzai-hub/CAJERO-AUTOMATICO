package com.cajero;

import javax.swing.*;

public class CajeroAutomatico extends JFrame {
    private JTabbedPane tabbedPane;
    private LoginPanel loginPanel;
    private MenuPrincipalPanel menuPanel;

    public CajeroAutomatico() {
        setTitle("Cajero Automático");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        tabbedPane = new JTabbedPane();

        // Crear paneles
        loginPanel = new LoginPanel(this);
        menuPanel = new MenuPrincipalPanel(this);

        // Agregar pestañas
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Menú Principal", menuPanel);

        // Desabilitar la pestaña del menú al inicio
        tabbedPane.setEnabledAt(1, false);

        add(tabbedPane);
        setVisible(true);
    }

    // Ir al menú principal después de login exitoso
    public void irAlMenuPrincipal(Cuenta cuenta) {
        menuPanel.setCuenta(cuenta);
        tabbedPane.setEnabledAt(1, true);
        tabbedPane.setSelectedIndex(1);
        tabbedPane.setEnabledAt(0, false);
    }

    // Volver al login después de salir
    public void irAlLogin() {
        loginPanel.resetearPanel();
        tabbedPane.setEnabledAt(0, true);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setSelectedIndex(0);
    }
}
