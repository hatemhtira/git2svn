/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;





import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 *
 * @author ELouanes
 */
public class ServeurImpl
    extends
    UnicastRemoteObject
    implements
    ServeurInt
{

    Serveur Serv;
    String MSG;
    String User;
    String[] FileList;
    Vector<String> list;
    int cmp = 0;

    public ServeurImpl()
        throws RemoteException
    {
        super();
    }


    public ServeurImpl(Serveur S)
        throws RemoteException
    {
        super();
        Serv = S;
        list = new Vector<>();
    }


    @Override
    public void login(String user)
        throws RemoteException
    {
        Serv.ADDUSER(user);
    }

    @Override
    public void RecivMSG(String User, String MSG)
        throws RemoteException
    {
        Serv.ADDMSG(User, MSG);
    }

    @Override
    public void RecivFile(byte[] filedata, String name, String user)
        throws RemoteException
    {
        try
        {
            BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("C:\\Users\\htira\\eclipse-workspace\\WebSocket project\\ServerRMI\\"
                                                                                        + name));
            output.write(filedata, 0, filedata.length);
            output.flush();
            output.close();
            Serv.ADDMSG(name, " Hase Ben Upload by " + user);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    @Override
    public byte[] SendFile(String fileName, String user)
        throws RemoteException
    {

        try
        {
            fileName = "C:\\Users\\htira\\eclipse-workspace\\WebSocket project\\ServerRMI\\" + fileName;
            File file = new File(fileName);
            byte buffer[] = new byte[(int)file.length()];
            BufferedInputStream input = new BufferedInputStream(new FileInputStream(fileName));
            input.read(buffer, 0, buffer.length);
            input.close();
            Serv.ADDMSG(fileName, " Hase benn Download by " + user);
            return (buffer);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return (null);
        }
    }


    @Override
    public String SendMSG()
        throws RemoteException
    {

        return Serv.SENDMASG();
    }

    @Override
    public Vector GetListFiles()
        throws RemoteException
    {
        try
        {
            list.removeAllElements();
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c",
                                                        "cd \"C:\\Users\\htira\\eclipse-workspace\\WebSocket project\\ServerRMI\" && dir /B");
            builder.redirectErrorStream(true);
            Process p;

            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true)
            {
                line = r.readLine();
                if (line == null)
                {
                    break;
                }
                list.add(line);
            }
            return list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void logOut(String user)
        throws RemoteException
    {
        Serv.DELUSER(user);
    }


}
