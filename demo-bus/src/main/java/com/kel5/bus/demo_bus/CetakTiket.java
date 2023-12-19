package com.kel5.bus.demo_bus;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CetakTiket {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private List<Penumpang> listPenumpang;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;

    public JFrame getFrame() {
        return frame;
    }
    
    public CetakTiket() {
        listPenumpang = new ArrayList<>();
        initialize();
    }
    

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 378, 303);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the closing event (e.g., hide the frame instead of disposing)
                frame.setVisible(false);
            }
        });

        // Menambahkan WindowAdapter untuk menangani kejadian penutupan jendela
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Mengatur perilaku penutupan jendela saat tombol close (X) ditekan
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 10, 135, 124);
        frame.getContentPane().add(panel);

        // Path ke gambar
        String imagePath = "C:\\Users\\ASUS\\Downloads\\R (1).jpg";

        // Menggunakan ImageIcon untuk menampilkan gambar pada JLabel
        ImageIcon imageIcon = new ImageIcon(imagePath);

        // Mendapatkan lebar dan tinggi panel
        int panelWidth = panel.getWidth();
        int panelHeight = panel.getHeight();

        // Menyesuaikan ukuran gambar sesuai dengan ukuran panel
        Image scaledImage = imageIcon.getImage().getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // Membuat label dan menetapkan gambar ke dalamnya
        JLabel imageLabel = new JLabel(scaledImageIcon);
        panel.add(imageLabel);

        JLabel lblNewLabel = new JLabel("Nama:");
        lblNewLabel.setBounds(155, 10, 50, 18);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblBus = new JLabel("Bus:");
        lblBus.setBounds(155, 66, 50, 18);
        frame.getContentPane().add(lblBus);

        JLabel lblTujuan = new JLabel("Tujuan:");
        lblTujuan.setBounds(155, 94, 50, 18);
        frame.getContentPane().add(lblTujuan);

        JLabel lblKursi = new JLabel("Kursi:");
        lblKursi.setBounds(155, 184, 50, 18);
        frame.getContentPane().add(lblKursi);

        JLabel lblNik = new JLabel("NIK:");
        lblNik.setBounds(155, 38, 50, 18);
        frame.getContentPane().add(lblNik);

        textField = new JTextField();
        textField.setBounds(202, 10, 140, 19);
        textField.setEditable(false);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setEditable(false);
        textField_1.setBounds(202, 38, 140, 19);
        frame.getContentPane().add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setEditable(false);
        textField_2.setBounds(202, 66, 140, 19);
        frame.getContentPane().add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setEditable(false);
        textField_3.setBounds(202, 94, 140, 19);
        frame.getContentPane().add(textField_3);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setEditable(false);
        textField_4.setBounds(202, 184, 140, 19);
        frame.getContentPane().add(textField_4);

        JButton btnNewButton = new JButton("Cetak");
        btnNewButton.setBounds(155, 227, 97, 29);
        frame.getContentPane().add(btnNewButton);
        
        textField_5 = new JTextField();
        textField_5.setEditable(false);
        textField_5.setColumns(10);
        textField_5.setBounds(202, 123, 140, 19);
        frame.getContentPane().add(textField_5);
        
        textField_6 = new JTextField();
        textField_6.setEditable(false);
        textField_6.setColumns(10);
        textField_6.setBounds(202, 155, 140, 19);
        frame.getContentPane().add(textField_6);
        
        JLabel lblHari = new JLabel("Hari:");
        lblHari.setBounds(155, 123, 50, 18);
        frame.getContentPane().add(lblHari);
        
        JLabel lblKeberangkatan = new JLabel("Jam:");
        lblKeberangkatan.setBounds(155, 156, 74, 18);
        frame.getContentPane().add(lblKeberangkatan);
        
        textField_7 = new JTextField();
        textField_7.setBounds(37, 184, 74, 19);
        frame.getContentPane().add(textField_7);
        textField_7.setColumns(10);

        btnNewButton.addActionListener(e -> {
            String nama = textField.getText();
            String nik = textField_1.getText();
            String namaBus = textField_2.getText();
            String tujuan = textField_3.getText();
            String kursi = textField_4.getText();

            if (isValidData(nama, nik, namaBus, tujuan, kursi)) {
                Penumpang penumpang = new Penumpang(nama, nik, namaBus, tujuan, kursi);
                listPenumpang.add(penumpang);

                System.out.println("Data Penumpang:");
                for (Penumpang p : listPenumpang) {
                    System.out.println("Nama: " + p.getNama());
                    System.out.println("NIK: " + p.getNik());
                    System.out.println("Bus: " + p.getNamaBus());
                    System.out.println("Tujuan: " + p.getTujuan());
                    System.out.println("Kursi: " + p.getKursi());
                    System.out.println("----------------------");
                }

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Pilih Lokasi Penyimpanan");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(frame);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    // Meminta pengguna untuk memasukkan nama file
                    String fileName = JOptionPane.showInputDialog(frame, "Masukkan Nama File:");

                    if (fileName != null && !fileName.trim().isEmpty()) {
                        File selectedDirectory = fileChooser.getSelectedFile();
                        File outputFile = new File(selectedDirectory, fileName + ".png");

                        saveImage(scaledImage, outputFile);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Nama file tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.out.println("Penyimpanan dibatalkan.");
                }

                frame.dispose();

                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");

            }
        });
    }

 // Di dalam metode saveImage
    private void saveImage(Image image, File outputFile) {
        try {
            // Menentukan lebar tambahan untuk latar belakang putih
            int additionalWidth = 150;

            // Menghitung lebar total gambar, termasuk latar belakang
            int totalWidth = image.getWidth(null) + additionalWidth;

            // Membuat BufferedImage dengan lebar tambahan untuk latar belakang
            BufferedImage bufferedImage = new BufferedImage(totalWidth, image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = bufferedImage.createGraphics();

            // Mengisi latar belakang dengan warna putih
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

            // Menggambar gambar di sebelah kiri
            graphics.drawImage(image, 0, 0, null);

            // Menambahkan teks pada gambar di sebelah kanan
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("SansSerif", Font.BOLD, 12));
            graphics.drawString("Nama: " + textField.getText(), image.getWidth(null) + 10, 20);
            graphics.drawString("NIK: " + textField_1.getText(), image.getWidth(null) + 10, 40);
            graphics.drawString("Bus: " + textField_2.getText(), image.getWidth(null) + 10, 60);
            graphics.drawString("Tujuan: " + textField_3.getText(), image.getWidth(null) + 10, 80);
            graphics.drawString("Kursi: " + textField_4.getText(), image.getWidth(null) + 10, 100);

            graphics.dispose();
            ImageIO.write(bufferedImage, "png", outputFile);
            System.out.println("Gambar berhasil disimpan di: " + outputFile.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private boolean isValidData(String nama, String nik, String namaBus, String tujuan, String kursi) {
        return !nama.isEmpty() && !nik.isEmpty() && !namaBus.isEmpty() && !tujuan.isEmpty() && !kursi.isEmpty();
    }

    private class Penumpang {
        private String nama;
        private String nik;
        private String namaBus;
        private String tujuan;
        private String kursi;

        public Penumpang(String nama, String nik, String namaBus, String tujuan, String kursi) {
            this.nama = nama;
            this.nik = nik;
            this.namaBus = namaBus;
            this.tujuan = tujuan;
            this.kursi = kursi;
        }

        public String getNama() {
            return nama;
        }

        public String getNik() {
            return nik;
        }

        public String getNamaBus() {
            return namaBus;
        }

        public String getTujuan() {
            return tujuan;
        }

        public String getKursi() {
            return kursi;
        }

        public void showFrame() {
            frame.setVisible(true);
        }
    }

	public void setPassengerInfo(String nama, String nik, String namaBus, String tujuan, String kursi) {
		textField.setText(nama);
        textField_1.setText(nik);
        textField_2.setText(namaBus);
        textField_3.setText(tujuan);
        textField_4.setText(kursi);

	}
}
