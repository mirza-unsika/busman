package com.kel5.bus.demo_bus;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Beli {
    private JFrame frmBusSystemBy;
    private ImageIcon imageIcon;
    private JTable table;
    private String selectedJenisBus = "";
    private static final double TRANSJAKARTA_PRICE = 5000;
    private static final double KOPAJA_PRICE = 4500;
    private static final double METROMINI_PRICE = 7000;
    private static final double SINARJAYA_PRICE = 6000;
    private static final double PAHALAKENCANA_PRICE = 4000;
    private JTextField searchField_1;
    private JTextField textField;
    private int jumlahPembelian = 0;
    
    private boolean ticketPrinted = false;
    private List<PembelianTiket> daftarPembelian = new ArrayList<>();
    private int currentPenumpangIndex = 0;
    private CetakTiket cetakTiket;

    

    public void showFrame() {
        frmBusSystemBy.setVisible(true);
    }

    public Beli() {
        cetakTiket = new CetakTiket();
        initialize();
    }

    public JFrame getFrame() {
        return frmBusSystemBy;
    }

    private void initialize() {
        frmBusSystemBy = new JFrame();
        frmBusSystemBy.setTitle("Bus System By Kelompok 5");
        frmBusSystemBy.setBounds(100, 100, 917, 492);
        frmBusSystemBy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String imagePath = "C:\\Users\\ASUS\\Downloads\\R.jpg";
        imageIcon = new ImageIcon(imagePath);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        
        
        List<String> jenisBusList = getJenisBusFromDatabase();
        panel.setBackground(new Color(0, 0, 0));
        frmBusSystemBy.getContentPane().add(panel, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(294, 0, 609, 376);
        panel_1.setBackground(new Color(255, 255, 255, 30));

        
        JButton btnBack = new JButton("Back");
        btnBack.setBackground(new Color(255, 255, 255, 255));
        btnBack.setBounds(10, 408, 83, 37);
        btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainMenu mainMenu = new MainMenu();
                mainMenu.getFrame().setVisible(true);
                frmBusSystemBy.dispose();
            }
        });

        JButton btnCetakTiket = new JButton("Cetak Tiket");
        btnCetakTiket.setBounds(788, 409, 105, 37);
        btnCetakTiket.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCetakTiket.setBackground(new Color(255, 255, 255, 255));
        btnCetakTiket.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCetakTiket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jumlahPembelian > 0 && !ticketPrinted) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String nama = (String) table.getValueAt(selectedRow, 0);
                        String nik = (String) table.getValueAt(selectedRow, 1);
                        String namaBus = (String) table.getValueAt(selectedRow, 2);
                        String tujuan = (String) table.getValueAt(selectedRow, 3);
                        String kursi = (String) table.getValueAt(selectedRow, 4);

                        cetakTiket.setPassengerInfo(nama, nik, namaBus, tujuan, kursi);
                        cetakTiket.getFrame().setVisible(true);

                        ticketPrinted = true;
                        currentPenumpangIndex++;

                        if (currentPenumpangIndex < daftarPembelian.size()) {
                            ticketPrinted = false;

                            PembelianTiket nextPenumpang = daftarPembelian.get(currentPenumpangIndex);

                            cetakTiket.setPassengerInfo(nextPenumpang.getNama(), nextPenumpang.getNik(),
                                    nextPenumpang.getNamaBus(), nextPenumpang.getTujuan(), nextPenumpang.getKursi());
                            cetakTiket.getFrame().setVisible(true);
                        }

                        // Perubahan: Hapus data penumpang hanya jika tiket berhasil dicetak
                        if (ticketPrinted) {
                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.removeRow(selectedRow);

                            if (selectedRow < daftarPembelian.size()) {
                                daftarPembelian.remove(selectedRow);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frmBusSystemBy,
                                "Pilih satu baris data penumpang untuk mencetak tiket.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frmBusSystemBy,
                            "Belum ada pembelian tiket atau tiket sudah dicetak.");
                }
            }
        });

        panel_1.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(39, 46, 528, 285);
        panel_1.add(scrollPane);
        scrollPane.setBackground(new Color(255, 255, 255, 255));

        table = new JTable();
        table.setModel(new NonEditableTableModel(
                new Object[] []{},
                new String[]{"Nama", "NIK", "Pilih Bus", "Tujuan","Hari","Jam", "Kursi"}
        ));
        scrollPane.setViewportView(table);
        panel.setLayout(null);
        panel.add(btnBack);
        panel.add(btnCetakTiket);
        panel.add(panel_1);
        searchField_1 = new JTextField();
        searchField_1.setBounds(96, 35, 156, 23);
        panel.add(searchField_1);
        searchField_1.setColumns(10);
        searchField_1.setFont(new Font("Tahoma", Font.BOLD, 13));

        JLabel lblNewLabel = new JLabel("Nama");
        lblNewLabel.setBounds(10, 36, 83, 19);
        panel.add(lblNewLabel);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setForeground(new Color(255, 255, 255));

        JComboBox<String> Bus = new JComboBox<>(jenisBusList.toArray(new String[0]));
        Bus.setBounds(96, 132, 156, 23);
        panel.add(Bus);
        Bus.setBackground(new Color(255, 255, 255, 255));

        JLabel lblPilihBus = new JLabel("Pilih bus");
        lblPilihBus.setBounds(10, 132, 83, 19);
        panel.add(lblPilihBus);
        lblPilihBus.setForeground(new Color(255, 255, 255));
        lblPilihBus.setFont(new Font("Tahoma", Font.BOLD, 15));

        JComboBox<String> Tujuan = new JComboBox<>();
        Tujuan.setBounds(96, 180, 156, 25);
        panel.add(Tujuan);
        Tujuan.setBackground(new Color(255, 255, 255, 255));
        
        JComboBox<String> Hari = new JComboBox<>();
        Hari.setBounds(96, 228, 156, 23);
        panel.add(Hari);

        JComboBox<String> Jam = new JComboBox<>();
        Jam.setBounds(96, 273, 156, 23);
        panel.add(Jam);
        
        JLabel lblKursi = new JLabel("Kursi");
        lblKursi.setBounds(10, 326, 83, 19);
        panel.add(lblKursi);
        lblKursi.setForeground(new Color(255, 255, 255));
        lblKursi.setFont(new Font("Tahoma", Font.BOLD, 15));

        JLabel lblTujuan = new JLabel("Tujuan");
        lblTujuan.setBounds(10, 181, 83, 19);
        panel.add(lblTujuan);
        lblTujuan.setForeground(new Color(255, 255, 255));
        lblTujuan.setFont(new Font("Tahoma", Font.BOLD, 15));

        // JComboBox for waktu keberangkatan
        JComboBox<String> Kursi = new JComboBox<>(new String[]{"08.00", "11.30", "15.00", "18.30"});
        Kursi.setBounds(96, 325, 156, 25);
        panel.add(Kursi);
        Kursi.setBackground(new Color(255, 255, 255, 255));

        Set<String> reservedSeats = new HashSet<>();

        // Adding ActionListener to comboBox
        Bus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mendapatkan jenis bus yang dipilih
                String selectedBus = (String) Bus.getSelectedItem();

             
                // Mengosongkan dan mengatur ulang ComboBox Tujuan
                Tujuan.removeAllItems();

                // Menentukan tujuan berdasarkan jenis bus
                if ("Metromini".equals(selectedBus)) {
                    // Jika jenis bus adalah Metromini, tambahkan tujuan "Bekasi-Karawang"
                    Tujuan.addItem("Bekasi-Karawang");
                }
                // Anda dapat menambahkan blok if-else untuk jenis bus lain jika diperlukan

                // Mengosongkan dan mengatur ulang ComboBox Hari dan Jam
                Hari.removeAllItems();
                Jam.removeAllItems();

                // Menambahkan hari Senin, Selasa, Rabu, Kamis, Jumat ke ComboBox Hari
                String[] hariArray = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat"};
                for (String hari : hariArray) {
                    Hari.addItem(hari);
                }

                // Menambahkan jam keberangkatan 08.00, 11.30, 15.00, 18.30 ke ComboBox Jam
                String[] jamArray = {"08.00", "11.30", "15.00", "18.30"};
                for (String jam : jamArray) {
                    Jam.addItem(jam);
                }

                // Mengosongkan dan mengatur ulang ComboBox Kursi
                Kursi.removeAllItems();

                // Menambahkan pilihan kursi berdasarkan hari
                String selectedHari = (String) Hari.getSelectedItem();
                if ("Senin".equals(selectedHari)) {
                    // Jika hari Senin, tambahkan pilihan kursi untuk Senin
                    String[] kursiArray = {"M01", "M02", "M03","M10","M15"};
                    for (String kursi : kursiArray) {
                        Kursi.addItem(kursi);
                    }
                }  
            }
        });
        
        JButton btnNewButton = new JButton("Beli");
        btnNewButton.setBounds(345, 408, 129, 37);
        panel.add(btnNewButton);
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBackground(new Color(255, 255, 255, 255));
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));

        JLabel lblNik = new JLabel("NIK");
        lblNik.setForeground(Color.WHITE);
        lblNik.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNik.setBounds(10, 84, 83, 19);
        panel.add(lblNik);
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.BOLD, 13));
        textField.setColumns(10);
        textField.setBounds(96, 83, 156, 23);
        panel.add(textField);
        
        JLabel lblHari = new JLabel("Hari");
        lblHari.setForeground(Color.WHITE);
        lblHari.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblHari.setBounds(10, 233, 83, 19);
        panel.add(lblHari);
        
        JLabel lblJam = new JLabel("Jam");
        lblJam.setForeground(Color.WHITE);
        lblJam.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblJam.setBounds(10, 278, 83, 19);
        panel.add(lblJam);

        // Membuat DocumentFilter untuk membatasi panjang input NIK
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                // Hanya mengizinkan penggantian teks jika panjang setelah penggantian tidak melebihi 16 karakter
                if ((fb.getDocument().getLength() + text.length() - length) <= 16) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = searchField_1.getText();
                String nik = textField.getText();
                String namaBus = (String) Bus.getSelectedItem();
                String tujuan = (String) Tujuan.getSelectedItem();
                String hari = (String) Hari.getSelectedItem();
                String jam = (String) Jam.getSelectedItem();
                String kursi = (String) Kursi.getSelectedItem();

                if (nik.length() != 16) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus terdiri dari 16 digit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (nama.isEmpty() && nik.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama dan NIK harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (nama.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (nik.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus diisi.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!isValidName(nama)) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Nama harus berisi huruf saja.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (!isValidNIK(nik)) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "NIK harus berisi angka saja.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                } else if (namaBus.isEmpty() || tujuan.isEmpty() || hari.isEmpty() || jam.isEmpty() || kursi.isEmpty()) {
                    JOptionPane.showMessageDialog(frmBusSystemBy, "Mohon isi semua kolom");
                } else {
                    double hargaTiket = getHargaTiket(namaBus);
                    String konfirmasiBeli = "Anda akan membeli tiket:\n"
                            + "Nama Penumpang: " + nama + "\n"
                            + "NIK: " + nik + "\n"
                            + "Nama Bus: " + namaBus + "\n"
                            + "Tujuan: " + tujuan + "\n"
                            + "Hari: " + hari + "\n"
                            + "Jam: " + jam + "\n"
                            + "Kursi: " + kursi + "\n"
                            + "Harga Tiket: " + hargaTiket + "\n\n"
                            + "Silakan masukkan uang Anda.";

                    String inputUangStr = JOptionPane.showInputDialog(frmBusSystemBy, konfirmasiBeli);

                    try {
                        double inputUang = Double.parseDouble(inputUangStr);

                        if (inputUang >= hargaTiket) {
                            String infoBeli = "Pembelian berhasil!\n"
                                    + "Nama Penumpang: " + nama + "\n"
                                    + "NIK: " + nik + "\n"
                                    + "Nama Bus: " + namaBus + "\n"
                                    + "Tujuan: " + tujuan + "\n"
                                    + "Hari: " + hari + "\n"
                                    + "Jam: " + jam + "\n"
                                    + "Kursi: " + kursi + "\n"
                                    + "Harga Tiket: " + hargaTiket + "\n"
                                    + "Uang Anda: " + inputUang + "\n"
                                    + "Kembalian: " + (inputUang - hargaTiket);
                            JOptionPane.showMessageDialog(frmBusSystemBy, infoBeli);

                            DefaultTableModel model = (DefaultTableModel) table.getModel();
                            model.addRow(new Object[]{nama, nik, namaBus, tujuan, hari, jam, kursi});

                            jumlahPembelian++;
                            ticketPrinted = false;

                            // Add penumpang info to the list
                            PembelianTiket pembelian = new PembelianTiket();
                            pembelian.nama = nama;
                            pembelian.nik = nik;
                            pembelian.namaBus = namaBus;
                            pembelian.tujuan = tujuan;
                            pembelian.kursi = kursi;
                            pembelian.hargaTiket = hargaTiket;

                            daftarPembelian.add(pembelian);

                            // Menyimpan data penumpang ke dalam tabel data_penumpang
                            saveToDatabase(nama, nik, namaBus, tujuan, hari, jam, kursi);
                        } else {
                            JOptionPane.showMessageDialog(frmBusSystemBy, "Uang tidak cukup. Pembelian gagal.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frmBusSystemBy, "Masukkan jumlah uang yang valid.");
                    }
                }
            }
            private void saveToDatabase(String nama, String nik, String namaBus, String tujuan, String hari, String jam, String kursi) {
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/busman", "root", "")) {
                    String query = "INSERT INTO data_penumpang (nama, nik, pilih_bus, jurusan, hari, keberangkatan, kursi) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                        preparedStatement.setString(1, nama);
                        preparedStatement.setString(2, nik);
                        preparedStatement.setString(3, namaBus);
                        preparedStatement.setString(4, tujuan);
                        preparedStatement.setString(5, hari);
                        preparedStatement.setString(6, jam);
                        preparedStatement.setString(7, kursi);

                        int rowsAffected = preparedStatement.executeUpdate();

                        if (rowsAffected > 0) {
                            System.out.println("Data penumpang berhasil disimpan ke dalam database.");
                        } else {
                            System.out.println("Gagal menyimpan data penumpang ke dalam database.");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    // Handle exception (e.g., show error message)
                }
            }
        });
        
        
        
        

        Tujuan.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Tujuan.hidePopup();
            }
        });

        Bus.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                Bus.hidePopup();
            }
        });
    }

    private double getHargaTiket(String namaBus) {
        switch (namaBus) {
            case "Transjakarta":
                return TRANSJAKARTA_PRICE;
            case "Kopaja":
                return KOPAJA_PRICE;
            case "Metromini":
                return METROMINI_PRICE;
            case "Sinar Jaya":
                return SINARJAYA_PRICE;
            case "Pahala Kencana":
                return PAHALAKENCANA_PRICE;
            default:
                return 0.0;
        }
    }

    private void saveTicketToText() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Tiket sebagai Teks");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));

        int userSelection = fileChooser.showSaveDialog(frmBusSystemBy);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getParentFile(), fileToSave.getName() + ".txt");
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
                StringBuilder ticketText = new StringBuilder();

                for (PembelianTiket pembelian : daftarPembelian) {
                    ticketText.append("Nama Penumpang: ").append(pembelian.getNama()).append("\n");
                    ticketText.append("NIK: ").append(pembelian.getNik()).append("\n");
                    ticketText.append("Nama Bus: ").append(pembelian.getNamaBus()).append("\n");
                    ticketText.append("Tujuan: ").append(pembelian.getTujuan()).append("\n");
                    ticketText.append("Kursi: ").append(pembelian.getKursi()).append("\n");
                    ticketText.append("Harga Tiket: ").append(pembelian.getHargaTiket()).append("\n\n");
                }

                writer.write(ticketText.toString());
                JOptionPane.showMessageDialog(frmBusSystemBy, "Tiket berhasil disimpan sebagai teks.", "Informasi", JOptionPane.INFORMATION_MESSAGE);

                ticketPrinted = true;

            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frmBusSystemBy, "Gagal menyimpan tiket sebagai teks.", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            ticketPrinted = false;
            JOptionPane.showMessageDialog(frmBusSystemBy, "Pencetakan tiket dibatalkan.");
        }
    }
    private List<String> getJenisBusFromDatabase() {
        List<String> jenisBusList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/busman", "root", "")) {
            String query = "SELECT nama_jenis_bus FROM jenis_bus";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        jenisBusList.add(resultSet.getString("nama_jenis_bus"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception (e.g., show error message)
        }

        return jenisBusList;
    }
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    private boolean isValidNIK(String nik) {
        return nik.matches("^[0-9]+$");
    }

    private class NonEditableTableModel extends DefaultTableModel {
        public NonEditableTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    private class PembelianTiket {
        private String nama;
        private String nik;
        private String namaBus;
        private String tujuan;
        private String kursi;
        private double hargaTiket;

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

        public double getHargaTiket() {
            return hargaTiket;
        }
        
    }
}