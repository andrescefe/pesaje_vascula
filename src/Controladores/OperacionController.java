/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pesaje.data.Emp;
import pesaje.data.db;
import utilitis.General;

/**
 *
 * @author AndresCefe
 */
public class OperacionController extends General {
    
    Connection conn=db.java_db();
    Statement st=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    
    
    public OperacionController(){
       
    }
    
    public String verificarVehiculo(String placa){
        
        String tipo = "";
        String query = "SELECT placa, tipo FROM Movimiento where tipo = 'E' AND placa = ?";
   
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1,placa);
            rs = pst.executeQuery();
            
            if(rs.next()){
//                rs.getString("placa");
                tipo = rs.getString("tipo");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        finally {

          try{
              pst.close();
              rs.close();

          }  

          catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
            }
        }
        
        return tipo;
    }
    
    public void ingresoVehiculo(String placa, Float peso, int cliente, int conductor, int producto){
        
        try {
            
            Date currentDate = GregorianCalendar.getInstance().getTime();
            DateFormat df = DateFormat.getDateInstance();
            String dateString = df.format(currentDate);
            String fecha = dateString;
            
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String timeString = sdf.format(d);

            String hora = timeString;
            
            String sql ="insert into Movimiento "
                    + "(placa,cliente_id, producto_id, fecha_ingreso,usuario_id, peso,tipo, conductor_id) "
                    + "values(?,?,?,?,?,?,?,?) ";
            
            pst=conn.prepareStatement(sql);
            pst.setString(1,placa);
            pst.setInt(2,cliente);
            pst.setInt(3, producto);
            pst.setString(4,fecha + "-" + hora);
            pst.setInt(5, Emp.empId);
            pst.setFloat(6, peso);
            pst.setString(7, "E");
            pst.setInt(8, conductor);

            pst.execute();
            JOptionPane.showMessageDialog(null,"Se registro el ingreso del vehiculo");
            auditoria("Ingresa vehiculo " + placa + " cliente " + cliente + " conductor " + conductor);
       
            
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        finally {

          try{
              pst.close();
          }  

          catch(Exception e){
             JOptionPane.showMessageDialog(null,e);

            }
        }
        
    }
    
    public void salidaVehiculo(String placa, float peso){

        try {
            
            Date currentDate = GregorianCalendar.getInstance().getTime();
            DateFormat df = DateFormat.getDateInstance();
            String dateString = df.format(currentDate);
            String fecha = dateString;

            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String timeString = sdf.format(d);
            String hora = timeString;

            String sql= "UPDATE Movimiento set tipo= ?,fecha_salida = ?, peso = ? "
                                + "WHERE placa = ?";
            
            pst=conn.prepareStatement(sql);
            pst.setString(1, "S");
            pst.setString(2, fecha + "-" + hora);
            pst.setFloat(3, peso);
            pst.setString(4, placa);
            pst.execute();
            JOptionPane.showMessageDialog(null,"Salida exitosa del vehiculo");
        } catch (SQLException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        finally {

          try{
              pst.close();
          }  

          catch(Exception e){
             JOptionPane.showMessageDialog(null,e);

            }
        }
        
    }
    
    public void auditoria(String accion){
      Date currentDate = GregorianCalendar.getInstance().getTime();
      DateFormat df = DateFormat.getDateInstance();
      String dateString = df.format(currentDate);

      Date d = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
      String timeString = sdf.format(d);

      String value0 = timeString;
      String value1 = dateString;
      String val = Emp.empUsername;
        try{

            String reg= "insert into Audit (emp_id, date, status) values "
                        + "('"+val+"','"+value0+" / "+value1+"','"+accion+"')";
            pst=conn.prepareStatement(reg);
            pst.execute();

       }
      catch (Exception e)

      {
          JOptionPane.showMessageDialog(null,e);
      }
       finally {

          try{
              pst.close();

          }  

          catch(Exception e){
             JOptionPane.showMessageDialog(null,e);

            }
        }
    }
    
}
