/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import pesaje.data.db;

/**
 *
 * @author AndresCefe
 */
public class ConductorController {
    
    private String sSQL = "";
    public Integer totalRegistros;
    Connection conn=db.java_db();
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private String id;
    private String nombre;
    private String apellido;
    
    public ConductorController(String id, String nombre, String apellido){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
    public ConductorController(){
        
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void mostrarConductores(JComboBox<ConductorController> cmo_conductor){
        
        try{
            String query = "select id_conductor, nombre, apellido from conductor order by nombre";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                cmo_conductor.addItem(
                        new ConductorController(
                                rs.getString("id_conductor"),
                                rs.getString("nombre"),
                                rs.getString("apellido")
                        )
                );
                
            }
            
            
        }catch(Exception ex){
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        finally {

            try{
                rs.close();
                st.close();
            }
            catch(Exception e){

            }
        }
    }
    
    @Override
    public String toString(){
        return nombre + " " + apellido;
    }
    
    public int getIdInsert(PreparedStatement pst){
        
        int id = 0;
        
        try{
            rs = pst.getGeneratedKeys();
            if(rs.next()){
                id = rs.getInt(1);
            }
        }catch(Exception ex){
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        finally {

            try{
                rs.close();
                st.close();
            }
            catch(Exception e){

            }
        }
        
        return id;
    }

    
}

    