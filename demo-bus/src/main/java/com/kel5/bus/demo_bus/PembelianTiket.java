package com.kel5.bus.demo_bus;

public class PembelianTiket {
    private String nama;
    private String nik;
    private String namaBus;
    private String tujuan;
    private String kursi;
    private String hari;
    private String jam;

    public PembelianTiket(String nama, String nik, String namaBus, String tujuan, String kursi) {
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
    public String getHari() {
        return hari;
    }
    public String getjam() {
        return jam;
    }
    public double getHargaTiket() {
        switch (namaBus) {
            case "Transjakarta":
                return 5000;
            case "Kopaja":
                return 4500;
            case "Metromini":
                return 7000;
            case "Sinar Jaya":
                return 6000;
            case "Pahala Kencana":
                return 4000;
            default:
                return 0.0;
        }
    }
}

