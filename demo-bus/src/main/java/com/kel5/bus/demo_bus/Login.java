package com.kel5.bus.demo_bus;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JFrame frmBusSystemBy;
    private JTextField textField;
    private JPasswordField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.frmBusSystemBy.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        initialize();
    }

    public JFrame getFrame() {
        return frmBusSystemBy;
    }
    private void initialize() {
    	
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 464, 479);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 450, 300);
        frmBusSystemBy.getContentPane().add(layeredPane);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(2, 2, 100));
        panel1.setBounds(0, 0, 450, 442);
        layeredPane.add(panel1, JLayeredPane.DEFAULT_LAYER);
        panel1.setLayout(null);

        String imagePath = "C:\\Users\\ASUS\\OneDrive\\Desktop\\bg\\HTC-Edge-Launcher-square-l (1).jpg";

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 450, 442);

        try {
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(ImageIO.read(new File(imagePath)))
                    .getImage().getScaledInstance(panel1.getWidth(), panel1.getHeight(), Image.SCALE_SMOOTH));
            lblNewLabel.setIcon(imageIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        panel1.add(lblNewLabel);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(25, 25, 25, 128));
        panel2.setBounds(78, 67, 294, 305);
        layeredPane.add(panel2, JLayeredPane.PALETTE_LAYER);
        panel2.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Login");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setBounds(10, 11, 274, 34);
        panel2.add(lblNewLabel_1);

        textField = new JTextField();
        textField.setFont(new Font("Dialog", Font.BOLD, 16));
        textField.setForeground(new Color(255, 255, 255));
        textField.setBackground(new Color(25, 25, 25, 128));
        textField.setBounds(10, 82, 274, 27);
        textField.setBorder(BorderFactory.createEmptyBorder());
        panel2.add(textField);

        JLabel label = new JLabel("Username");
        label.setFont(new Font("Dialog", Font.BOLD, 13));
        label.setBounds(10, 53, 82, 21);
        label.setForeground(new Color(255, 255, 255));
        panel2.add(label);

        JLabel label_1 = new JLabel("Password");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Dialog", Font.BOLD, 13));
        label_1.setBounds(10, 135, 82, 21);
        panel2.add(label_1);

        JButton button = new JButton("Login");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(textField_2.getPassword());

                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Username kosong!");
                } else if (password.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Password kosong!");
                } else {
                    if (isValidLogin(username, password)) {
                        JOptionPane.showMessageDialog(frmBusSystemBy, "Login berhasil!");

                        // Sembunyikan frame login
                        frmBusSystemBy.setVisible(false);

                        // Tampilkan frame MainMenu
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.getFrame().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(frmBusSystemBy, "Login gagal! Username atau password salah.");
                    }
                }
            }
            private boolean isValidLogin(String inputUsername, String inputPassword) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/busman", "root", "")) {
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, inputUsername);
                        preparedStatement.setString(2, inputPassword);

                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            return resultSet.next(); // Returns true if a matching record is found
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }

        });
        button.setBackground(new Color(0, 128, 0));
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Dialog", Font.BOLD, 15));
        button.setBounds(88, 211, 115, 44);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        panel2.add(button);

        textField_2 = new JPasswordField();
        textField_2.setForeground(Color.WHITE);
        textField_2.setFont(new Font("Dialog", Font.BOLD, 16));
        textField_2.setBackground(new Color(25, 25, 25, 128));
        textField_2.setBounds(10, 157, 274, 27);
        textField_2.setBorder(BorderFactory.createEmptyBorder());
        panel2.add(textField_2);
    }

    
}
