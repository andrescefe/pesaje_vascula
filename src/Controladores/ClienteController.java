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
import javax.swing.table.DefaultTableModel;
import pesaje.data.db;

/**
 *
 * @author AndresCefe
 */
public class ClienteController {
    
    private String sSQL = "";
    public Integer totalRegistros;
    Connection conn=db.java_db();
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private String id;
    private String nombre;
    private String apellido;
    
    public ClienteController(String id, String nombre, String apellido){
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        
    }
    
    public ClienteController(){
    
    }
    
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void mostrarClientes(JComboBox<ClienteController> cmo_cliente){
        
        try{
            String query = "select id_cliente, nombre, apellido from cliente order by nombre";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                cmo_cliente.addItem(
                        new ClienteController(
                                rs.getString("id_cliente"),
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
    
    
    public DefaultTableModel mostrarPorCodigo(String buscar) {

  
        DefaultTableModel modelo;

        String[] titulos
                = {"id_cliente","nit", "nombre",
                    "apellido", "telefono",
                    "email", "direccion"};

        String[] registros = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
 
        sSQL = "select * from cliente where id_cliente =" + buscar +" order by id_cliente desc";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("id_cliente");
                registros[1] = rs.getString("nit");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("apellido");
                registros[4] = rs.getString("telefono");
                registros[5] = rs.getString("email");
                registros[6] = rs.getString("direccion");
                 

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }

    }
    
    
    public DefaultTableModel mostrar(String buscar) {

  
        DefaultTableModel modelo;

        String[] titulos
                = {"id_cliente","nit", "nombre",
                    "apellido", "telefono",
                    "email", "direccion"};

        String[] registros = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
 
        sSQL = "select * from cliente where nombre like '%" + buscar + "%' order by id_cliente desc";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                
                registros[0] = rs.getString("id_cliente");
                registros[1] = rs.getString("nit");
                registros[2] = rs.getString("nombre");
                registros[3] = rs.getString("apellido");
                registros[4] = rs.getString("telefono");
                registros[5] = rs.getString("email");
                registros[6] = rs.getString("direccion");
                 

                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        
        finally {
            
            try{
                rs.close();
                pst.close();
                
            }
            catch(Exception e){
                
            }
        
        }

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
