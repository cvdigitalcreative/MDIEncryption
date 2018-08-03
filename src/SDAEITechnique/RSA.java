/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

import Entity.DataRSA;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author shaff
 */
public class RSA {
    private double p, q, n, m;
    
    public RSA() {
    }
    
    public int set_nilai_prima_pq(double p, double q){
        int pembagi, flag_p, flag_q, flag_prima;
        
        flag_p = 1;
        flag_q = 1;
        flag_prima = -1;
        
        for(pembagi = 2; pembagi <= p/2; pembagi++){
            if(p%pembagi == 0){
                flag_p = 0;
                break;
            }
        }
        
        for(pembagi = 2; pembagi <= q/2; pembagi++){
            if(q%pembagi == 0){
                flag_q = 0;
                break;
            }
        }
        
        if(flag_p == 1){
            this.p = p;
        }
        
        if(flag_q == 1){
            this.q = q;
        }
        
        if(flag_p == 1 && flag_q == 1){
            flag_prima = 1;
        }
        else if(flag_p == 1 && flag_q == 0){
            flag_prima = 0;
        }
        else if(flag_p == 0 && flag_q == 1){
            flag_prima = 2;
        }
            
        return flag_prima;
    }
    
    public double hitung_nilai_n(){
        return p*q;
    }
    
    public double hitung_nilai_m(){
        return (p-1)*(q-1);
    }
    
    public double hitung_nilai_e(int y){
        double gcd;
        double iterasi = 1;
        
        while(iterasi < y)
        {
            iterasi += 1;
            gcd = hitung_gcd_euclid(iterasi, y);
            
            if(gcd == 1){
                break;
            }
        }
            
        return iterasi;
    }
    
    public double hitung_gcd_euclid(double x, double y){
//        System.out.println(y+" = n"+x+" + "+(y%x));
        
        while(y%x!=0)
        {
            double rem = y%x;
            y = x;
            x = rem;
            
//            System.out.println(y+" = n"+x+" + " +y%x);
        }
            
        return x;
    }
    
    public double hitung_nilai_d(double e, double m){
        double gcd = 0;
        double iterasi = 1;
        
        while(gcd != 1)
        {
            iterasi += 1;
            gcd = (iterasi*e)%m ;
        }
        
        return iterasi;
    }    
    
    public ArrayList<String> getKodeASCII(String key){
        int h = key.length();
        int i;
        ArrayList<String> blokKodeASCII;
        
        blokKodeASCII = new ArrayList<>();
        
        System.out.println("Kode ASCII : ");
        for(i=0; i<h; i++){
            int keyASCII = (int)key.charAt(i);
            System.out.print(keyASCII+" ");
            blokKodeASCII.add(String.valueOf(keyASCII));
        }

        return blokKodeASCII;
    }
    
    public String getStringFromASCII(String plainText){
        String blok, text;
        int i, blokInteger, iterasi;
        
        iterasi = 0;
        blok = "";
        text = "";
        
        System.out.println("panjang teks : "+plainText);
        for(i=0; i<plainText.length(); i++){
            blok = blok + plainText.charAt(i);
            
            if(iterasi == 2){
                blokInteger = Integer.parseInt(blok);
                text += String.valueOf((char)blokInteger);
                iterasi = 0;
                blok = "";
            }
            else if(iterasi != 1 && i == plainText.length()-1){
                blokInteger = Integer.parseInt(blok);
                text += String.valueOf((char)blokInteger);
            }
            else{
                iterasi += 1;
            }
        }
        
        return text;
    }
    
    public ArrayList<String> getBlokPlainTeks(ArrayList<String> KodeASCII){
        ArrayList<String> blokPlainTeks;
        int i;
        
        blokPlainTeks = new ArrayList<>();
        
        System.out.println("blok plain teks : ");
        for(i=0; i<KodeASCII.size(); i++){
            String blok;
            
            if(KodeASCII.get(i).length() == 1){
                blok = "00"+KodeASCII.get(i);
            }
            else if(KodeASCII.get(i).length() == 2){
                blok = "0"+KodeASCII.get(i);
            }
            else{
                blok = KodeASCII.get(i);
            }

            System.out.print(blok+" ");
            blokPlainTeks.add(blok);
        }
        
        return blokPlainTeks;
    }
    
    public String enkripsiPlainTeks(ArrayList<String> blokPlainTeks, double e, double n){
        int i;
        double blokInteger, chiperBlok;
        String chiperText="";
        
        for(i=0; i<blokPlainTeks.size(); i++){  
            blokInteger =  Double.parseDouble(blokPlainTeks.get(i));
            System.out.println("blok : "+blokInteger);
            chiperBlok = Math.pow(blokInteger, e)%n;
            
            if(i == blokPlainTeks.size()-1){
                chiperText += String.valueOf((int)chiperBlok);
            }
            else{
                chiperText += String.valueOf((int)chiperBlok)+" ";
            }
        }
        
        return chiperText;
    }
    
    public ArrayList<String> getBlokChiperTeks(String ChiperText){
        ArrayList<String> blokChiperTeks;
        String blok;
        int i;
        
        blokChiperTeks = new ArrayList<>();
        blok = "";
        
        for(i=0; i<ChiperText.length(); i++){            
            if(String.valueOf(ChiperText.charAt(i)).equals(" ")){
                blokChiperTeks.add(blok);
                blok = "";
            }
            else{
                blok = blok + ChiperText.charAt(i);
                if(i == ChiperText.length()-1){
                    blokChiperTeks.add(blok);
                }
            }
        }
        
        return blokChiperTeks;
    }
    
    public String dekripsiChiperTeks(ArrayList<String> blokChiperTeks, double d, double n){
        int i;
        double blokInteger, plainBlok;
        String plainText="", blok;
        BigInteger plainBlokBigNumber;
        
        for(i=0; i<blokChiperTeks.size(); i++){
            blokInteger =  Double.parseDouble(blokChiperTeks.get(i));
            plainBlokBigNumber = BigInteger.valueOf((long)blokInteger);
            plainBlok = plainBlokBigNumber.pow((int)d).mod(BigInteger.valueOf((int)n)).doubleValue();
            
            System.out.println(String.valueOf((int)plainBlok)+" ");
            blok = String.valueOf((int)plainBlok);
            
            if(blok.length() == 1){
                plainText += "00"+blok;
            }
            else if(blok.length() == 2){
                plainText += "0"+blok;
            }
            else{
                plainText += blok;
            }
        }
        
        return plainText;
    }
    
    public int simpanDataRSA(String nama_file, double d, double nilai_n){   
        int primary_key;
        DatabaseManager databaseManager = new DatabaseManager();
        DataRSA dataRSA = new DataRSA();
        
        primary_key = databaseManager.getPrimaryKeyDatabase();
        
        dataRSA.setId_key(primary_key);
        dataRSA.setNama_file(nama_file);
        dataRSA.setNilai_n(nilai_n);
        dataRSA.setPrivate_key(d);
        databaseManager.simpanDataRSA(dataRSA);
        
        return primary_key;
    }
    
    public DataRSA getDataRSA(String nama_file){
        DatabaseManager databaseManager = new DatabaseManager();
        DataRSA dataRSA;
        
        dataRSA = databaseManager.getDataRSADatabase(nama_file);
        return dataRSA;
    }
    
    public void simpanDataCipherText(int primary_key, String chiperText){
        FileManager fileManager;
        fileManager = new FileManager();
        
        fileManager.simpanChiperText(primary_key, chiperText);
    }
}
