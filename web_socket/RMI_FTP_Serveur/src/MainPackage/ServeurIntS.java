/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.ImageIcon;

/**
 *
 * @author ELouanes
 */
public interface ServeurIntS extends Remote{
    
    public void login(String user) throws RemoteException;
    public void logOut(String user) throws RemoteException;
    public void RecivMSG (String User,String MSG) throws RemoteException;
    public String SendMSG () throws RemoteException;
    public void RecivFile (byte[] file, String name,String user) throws RemoteException;
    public byte[] SendFile(String fileName,String user) throws java.rmi.RemoteException;
    public Vector GetListFiles() throws java.rmi.RemoteException;
    
}
