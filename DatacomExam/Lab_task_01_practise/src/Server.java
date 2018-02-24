import java.net.*;
import java.util.*;
import java.io.*;

public class Server
{
    public static  void main(String[] args) throws IOException, NumberFormatException, EOFException {
        ServerSocket ss;
        Socket s;
        DataInputStream dis;
        DataOutputStream dos;

        System.out.println("Server is connected.........");
        ss = new ServerSocket(2200);
        s = ss.accept();
        System.out.println("Client is connected...........");
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        FileWriter fw = new FileWriter("output.txt");

        String str = " ";

        do {
            str = dis.readUTF();

            System.out.println(str);

            if(str.equals("20"))
            {
                fw.write("\r\n");
                continue;
            }

            int data = Integer.parseInt(str);

            String s1 = Integer.toBinaryString(data);

            int cnt=0;

            for(int i=0; i<s1.length(); i++)
            {
                if(s1.charAt(i)=='1') cnt++;
            }

            if(cnt%2==1)
            {
                data = data>>1;
                char c = (char)data;
                System.out.println("char : "+c);
                fw.write(c);
                fw.flush();
            }
            else System.out.println(" :( Error :( ");
            dos.flush();

        } while (!str.equals("stop"));
    }
}
