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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pesaje.data.db;
import utilitis.General;

/**
 *
 * @author andrescefe
 */
public class UsuarioController extends General {
    
    Connection conn=db.java_db();
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    private String sSQL = ""; //Sentencia SQL
    private String sSQL2 = "";
    public Integer totalRegistros; // Obtener los registros
    
    public DefaultTableModel mostrar(String buscar) {

        DefaultTableModel modelo;

        String[] titulos = {"Codigo", "Nombre", "Perfil"};

        String[] registros = new String[3];
        int totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select id, username, perfil from Users "
              + " where username like '%" + buscar + "%' order by id desc";

        try {

            st = conn.createStatement();
            rs = st.executeQuery(sSQL);

            while (rs.next()) {

                registros[0] = rs.getString("id");
                registros[1] = rs.getString("username");
                registros[2] = rs.getString("perfil");
                
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
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
    
}
