/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ELouanes
 */
public class Thre extends Thread{

    Clien C;
    ServeurInt Int;
    boolean Recev;
    String RecevMSG;
    public Thre(Clien C,ServeurInt Int) {
        super();
        this.C=C;
        this.Int=Int;
        Recev=true;
    }
    
    @Override
    public void run(){
        while(true){
            try {
            //RecvMSG();
            String MSG= Int.SendMSG();
            if(MSG!=null)
            if(Recev){
                C.RecvMSG(MSG);
                RecevMSG=MSG;
                Recev=false;
                
            }else{
                if(!MSG.equals(RecevMSG)){
                    C.RecvMSG(MSG);
                    RecevMSG=MSG;
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Clien.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    
}
