//masih error
package com.kel5.bus.demo_bus;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Jadwal3 extends Jadwal {

    private JFrame grandchildFrame;

    public Jadwal3() {
        super(); // Panggil konstruktor dari kelas induk
        initializeGrandchild();
    }

    private void initializeGrandchild() {
        grandchildFrame = new JFrame("Jadwal 3");
        grandchildFrame.setBounds(100, 100, 777, 463);
        grandchildFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        grandchildFrame.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(120, 53, 525, 333);
        panel_1.setBackground(new Color(255, 255, 255, 100));

        JLabel lblNewLabel_1 = new JLabel("Rute Bekasi-Bogor");
        lblNewLabel_1.setBounds(115, 0, 257, 50);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

        JTextArea txtrHariSenin = new JTextArea("Hari Minggu\r\n- 09:30-11.00\r\nBekasi-Bogor\r\n- 13:00-14.30\r\nBekasi-Bogor\r\n- 16.00-17.30\r\nBekasi-Bogor\r\n- 19.00-20.30\r\nBekasi-Bogor");
        txtrHariSenin.setBounds(55, 80, 317, 204);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds(33, 302, 53, 21);
        btnBack.addActionListener(e -> {
            MainMenu mainMenu = new MainMenu();
            mainMenu.getFrame().setVisible(true);
            grandchildFrame.dispose();
            // Handle back action
        });

        JButton btnLogout = new JButton("Next");
        btnLogout.setBounds(342, 302, 53, 21);
        btnLogout.addActionListener(e -> {
            // Handle logout action
        });
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Bus Kopaja");
        lblNewLabel.setBounds(165, 42, 281, 22);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_1.setLayout(null);
        panel_1.add(btnBack);
        panel_1.add(btnLogout);
        panel_1.add(lblNewLabel);
        panel_1.add(lblNewLabel_1);
        panel_1.add(txtrHariSenin);
        panel.add(panel_1);

        grandchildFrame.setVisible(true);
    }

    public JFrame getGrandchildFrame() {
        return grandchildFrame;
    }
}
