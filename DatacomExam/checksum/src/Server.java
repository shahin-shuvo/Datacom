import java.net.*;
import java.io.*;
import java.util.*;
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        System.out.println("Server START......");
        ss=new ServerSocket(2000);
        s=ss.accept();
        System.out.println("Client Connected.....");
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        String str=" ";
        String s1;
        do{
            str=din.readUTF();
            System.out.println(str);
            //dout.flush();
            boolean l=Receive(str);
            if(l)
            {
                dout.writeUTF("Received Successfully");
                dout.flush();
                break;

            }
            else

            {
                dout.writeUTF("Errorr");
                dout.flush();
                break;
            }

        }while(!str.equals("stop"));

    }
    public static boolean Receive(String s)
    {
        int temp=0;
        String[] k = s.split(",");
        for (int i =0 ; i<k[0].length();i++)
        {
            int m = k[0].charAt(i);
            temp+=m;
        }
        int checksum = temp%16;
        if(checksum==Integer.parseInt(k[1]))
        {
            return true;
        }
        else
            return false;

    }


}