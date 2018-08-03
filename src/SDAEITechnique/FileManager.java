/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author shaff
 */
public class FileManager {
    private boolean isTersimpan;
    
    public FileManager() {
    }
    
    public void simpanChiperText(int id, String chiperText){
//        File file = new File("E:\\KULIAH\\Tugas Akhir\\GenderClassification\\Basisdata\\Mean.txt");
        File file = new File("D:\\Data Encrypt\\Data Txt\\chiper_text_"+id+".txt");
        
        try{
            FileOutputStream fos = new FileOutputStream(file);
            
            fos.write(chiperText.getBytes());
            fos.flush();
            fos.close();
            
            isTersimpan = true;
        }catch(IOException e){
            isTersimpan = false;
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public String openFile(String filename){
        String chiperText = "";
        String nilai;
        
        try{
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream dataIn = new DataInputStream(fstream);
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(dataIn))){
                while((nilai=reader.readLine())!=null){
                    chiperText = nilai;
                }
                reader.close();
            }
        }catch(FileNotFoundException notfound){
            System.out.println("File tidak ditemukan: "+notfound.getMessage());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        return chiperText;
    }
    
    public boolean isDataTersimpan(){
        return isTersimpan;
    } 
}
