/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author shaff
 */
public class DataRSA {
    int id_key;
    String nama_file;
    double private_key;
    double nilai_n;
    
    public DataRSA() {
    }

    public DataRSA(int id_key, String nama_file, double private_key, double nilai_n) {
        this.id_key = id_key;
        this.nama_file = nama_file;
        this.private_key = private_key;
        this.nilai_n = nilai_n;
    }

    public int getId_key() {
        return id_key;
    }

    public void setId_key(int id_key) {
        this.id_key = id_key;
    }

    public String getNama_file() {
        return nama_file;
    }

    public void setNama_file(String nama_file) {
        this.nama_file = nama_file;
    }

    public double getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(double private_key) {
        this.private_key = private_key;
    }

    public double getNilai_n() {
        return nilai_n;
    }

    public void setNilai_n(double nilai_n) {
        this.nilai_n = nilai_n;
    }
    
    
}
