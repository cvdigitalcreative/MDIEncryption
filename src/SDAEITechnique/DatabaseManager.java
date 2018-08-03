/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAEITechnique;

import Entity.DataRSA;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shaff
 */
public class DatabaseManager {
    private Connection conn = null;
    
    private final String user = "root";
    private final String password = "";
    
    public DatabaseManager(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rsa_database", user, password);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simpanDataRSA(DataRSA dataRSA){
        String sql_RSA;
        sql_RSA = "INSERT INTO data_rsa (id_key, nama_file, private_key, nilai_n) VALUES (?, ?, ?, ?)";
         
        try {
            PreparedStatement statement = conn.prepareStatement(sql_RSA);
            statement.setInt(1, dataRSA.getId_key());
            statement.setString(2, dataRSA.getNama_file());
            statement.setDouble(3, dataRSA.getPrivate_key());
            statement.setDouble(4, dataRSA.getNilai_n());
            
            int rowsInserted = statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getPrimaryKeyDatabase(){
        int id = 1;
        String sql_RSA;
        sql_RSA = "select id_key from data_rsa";
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql_RSA);
            
            while (result.next()){
                if(id != result.getInt(1)){
                    id = result.getInt(1);
                    break;
                }
                
                id += 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return id;
    }
    
    public DataRSA getDataRSADatabase(String nama_file){
        DataRSA dataRSA = new DataRSA();
        String sql_RSA;
        sql_RSA = "select *from data_rsa where nama_file = '"+nama_file+"'";
        
        try {            
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql_RSA);
            
            result.next();
            dataRSA.setId_key(result.getInt(1));
            dataRSA.setNama_file(result.getString(2));
            dataRSA.setPrivate_key(result.getDouble(3));
            dataRSA.setNilai_n(result.getDouble(4));

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dataRSA;
    }
}
