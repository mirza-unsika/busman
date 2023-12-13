//masih error

package com.kel5.bus.demo_bus;

import javax.swing.*;
import java.awt.*;

class Jadwal2 extends Jadwal {
    private JFrame childFrame;

    public Jadwal2() {
        super();
        initializeChild();
    }

    public void showFrame() {
        childFrame.setVisible(true);
    }

    JPanel initializeChild() {
        childFrame = new JFrame("Jadwal 2");
        childFrame.setBounds(100, 100, 777, 463);
        childFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Change the image path to the location of your image
        String imagePath = "D:\\Download\\aINmBZ.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(new Color(0, 0, 0));
        childFrame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(120, 53, 525, 333);
        panel_1.setBackground(new Color(255, 255, 255, 100));

        JLabel lblNewLabel_1 = new JLabel("Rute Bekasi-Jakarta");
        lblNewLabel_1.setBounds(115, 0, 257, 50);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTextArea txtrHariSenin = new JTextArea("Hari Selasa-Sabtu\r\n- 08:45-10.00\r\nBekasi-Jakarta\r\n- 11:30-12.45\r\nBekasi-Jakarta\r\n- 16.30-17.45\r\nBekasi-Jakarta\r\n- 19.15-20.30\r\nBekasi-Karawang");
        txtrHariSenin.setBounds(55, 80, 317, 204);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(33, 302, 53, 21);
        btnBack.addActionListener(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.getFrame().setVisible(true);
            childFrame.dispose();
            // Handle back action
        });

        JButton btnLogout = new JButton("Next");
        btnLogout.setBounds(342, 302, 53, 21);
        btnLogout.addActionListener(e -> {
            // Handle logout action
        });
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bus Transjakarta\r\n");
        lblNewLabel.setBounds(125, 42, 281, 22);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_1.setLayout(null);
        panel_1.add(btnBack);
        panel_1.add(btnLogout);
        panel_1.add(lblNewLabel);
        panel_1.add(lblNewLabel_1);
        panel_1.add(txtrHariSenin);
        panel.add(panel_1);

        return panel; // Return the JPanel
    }
}
