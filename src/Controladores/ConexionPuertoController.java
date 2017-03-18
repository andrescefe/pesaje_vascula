/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import panamahitek.Arduino.PanamaHitek_Arduino;

/**
 *
 * @author andrescefe
 */
public class ConexionPuertoController {
    
    public String dato;
    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
      SerialPortEventListener evento = new SerialPortEventListener() {

            @Override
            public void serialEvent(SerialPortEvent spe) {

                try{
    //                dato = arduino.receiveData();
    //                if(arduino.isMessageAvailable()){

                    System.out.println(arduino.receiveData());
    //                }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex + "fallo");
                }


                }
            };
    
    
    public ConexionPuertoController(){
//        read = new Thread(new ImplementoRunnable());
//        read.start();
        try {
            
//            arduino.arduinoTX("COM3", 9600);
            arduino.arduinoRXTX("COM3", 9600,evento);
//            read.resume();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex + "fallo al conectar");
        }
        
    
    }
    
    public int getDato(){
       
        try {
           
        } catch (Exception ex) {
            Logger.getLogger(ConexionPuertoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
        
    }
    
//    private class ImplementoRunnable implements Runnable{
//        
//        public void run(){
//            
////          
//        while(true){
//                try{
//                    Thread.sleep(100);
//                    dato = arduino.printMessage();
//                }catch(Exception e){
//                    JOptionPane.showMessageDialog(null,e + "fallo");
//                }
//            }
//        }
//    }
    
    
    
    
//    public static void main(String args[]) {
//        
//        ConexionPuertoController p = new ConexionPuertoController();
////        p.getDato();
//        
//    }
    
    
    
}
