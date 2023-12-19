package com.kel5.bus.demo_bus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jadwal5 {
    private JFrame frmBusSystemBy;
    private ImageIcon imageIcon;

    public void showFrame() {
        frmBusSystemBy.setVisible(true);
    }

    public Jadwal5() {
        initialize();
    }

    public JFrame getFrame() {
        return frmBusSystemBy;
    }

    protected void initialize() {
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 777, 463);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "D:\\Download\\black-abstract-background-with-various-corner-layers-vector.jpg";
        imageIcon = new ImageIcon(imagePath);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setBackground(new Color(0, 0, 0));
        frmBusSystemBy.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(122, 37, 525, 333);
        panel_1.setBackground(new Color(255, 255, 255, 2));

        JTextArea txtrHariSenin = new JTextArea();
        txtrHariSenin.setFont(new Font("Monospaced", Font.BOLD, 16));
        txtrHariSenin.setForeground(new Color(255, 255, 255));
        txtrHariSenin.setEditable(false);
        txtrHariSenin.setBackground(new Color(255, 255, 255, 2));
        txtrHariSenin.setBounds(180, 73, 189, 224);
        txtrHariSenin.setText("Hari Rabu-Jum'at\r\n- 10.00-11.15\r\nBekasi-Depok\r\n- 13.00-14.15\r\nBekasi-Depok\r\n- 16.00-17.15\r\nBekasi-Depok\r\n- 19.00-20.15\r\nBekasi-Depok");
        panel.setLayout(null);
        panel_1.setLayout(null);
        panel_1.add(txtrHariSenin);
        panel.add(panel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Bus Sinar Jaya\r\n");
        lblNewLabel_1_1.setForeground(new Color(128, 128, 0));
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(192, 0, 257, 50);
        panel_1.add(lblNewLabel_1_1);

        JLabel lblNewLabel = new JLabel("==========================================================\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setBounds(57, 50, 423, 13);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_2 = new JLabel("==========================================================\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(57, 284, 423, 13);
        panel_1.add(lblNewLabel_2);

        JLabel lblNewLabel_1 = new JLabel("Rute Bekasi-Depok");
        lblNewLabel_1.setBounds(293, 10, 257, 50);
        panel.add(lblNewLabel_1);
        lblNewLabel_1.setForeground(new Color(128, 128, 0));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmBusSystemBy.setVisible(false);
                Jadwal4 jadwal4 = new Jadwal4();
                jadwal4.showFrame(); // Close the current frame (Jadwal)
            }
        });
        btnBack.setBounds(21, 375, 74, 41);
        panel.add(btnBack);

        JButton btnNext = new JButton("Next");
        btnNext.setBounds(666, 375, 74, 41);
        panel.add(btnNext);
        
        JButton btnBack_1 = new JButton("Main Menu");
        btnBack_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainMenu mainMenu = new MainMenu();
                mainMenu.getFrame().setVisible(true);
                frmBusSystemBy.dispose();
        	}
        });
        btnBack_1.setBounds(340, 375, 98, 41);
        panel.add(btnBack_1);
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frmBusSystemBy.setVisible(false);
                Jadwal6 jadwal6 = new Jadwal6();
                jadwal6.showFrame();
               ;
            }
        });
    }

    
}
