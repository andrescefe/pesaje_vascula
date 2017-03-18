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
public class ProductoController {
    
    private String sSQL = "";
    public Integer totalRegistros;
    Connection conn=db.java_db();
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private String id;
    private String nombre;
    
    public ProductoController(String id, String nombre){
        this.id     = id;
        this.nombre = nombre;
    }
    
    public ProductoController(){
        
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void mostrarProductor(JComboBox<ProductoController> cmo_producto){
        
        try{
            String query = "select id_producto, nombre from producto order by nombre";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            
            while(rs.next()){
                cmo_producto.addItem(
                        new ProductoController(
                                rs.getString("id_producto"),
                                rs.getString("nombre")
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
        return nombre;
    }
    
    
    public DefaultTableModel mostrarPorCodigo(String buscar) {

  
        DefaultTableModel modelo;

        String[] titulos
                = {"id_producto","nombre", "categoria"};

        String[] registros = new String[3];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
 
        sSQL = "select * from producto where id_producto =" + buscar +" order by id_producto desc";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("id_producto");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("categoria");
                 
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
                = {"id_producto","nombre", "categoria"};

        String[] registros = new String[3];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
 
        sSQL = "select * from producto where nombre like '%" + buscar + "%' order by id_producto desc";

        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                
                registros[0] = rs.getString("id_producto");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("categoria");                

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
