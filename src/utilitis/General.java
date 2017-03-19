/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilitis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author AndresCefe
 */
public class General {
    
    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    
    public int userRepeat(String username){
        
      String sql ="SELECT COUNT(username) AS login FROM Users WHERE username = " + username;
        
        try {
            
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            int loginResultante=0;
            
            while(rs.next()) {
                loginResultante = rs.getInt("login");
            }
            
            return loginResultante;
            
        } catch (Exception e) {
            return 0;
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
//                st.close();
            }
            catch(Exception e){

            }
        }
        
        return id;
    }
}
