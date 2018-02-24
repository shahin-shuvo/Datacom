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
        ss = new ServerSocket(2200);
        s = ss.accept();
        System.out.println("Client Connected.....");
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        String s1 = " ";

        do {
            String tmp_int = "";
            int arry[] = new int[100];
            int sum=0,count =0,checkSumClient = 0,remainder,index=0;
            s1 = din.readUTF();
            System.out.println("Received line:" +s1);
            String ar[] = s1.split(" ");

            for(int i =0;i<ar.length-1;i++)
            {
                for(int j=0; j<ar[i].length(); j++)
                {
                    char ch = ar[i].charAt(j);
                    int ascii = (int) ch;
                    sum = sum + ascii;
                }
            }

            checkSumClient=Integer.parseInt(ar[ar.length-1]);


            System.out.println("Sum: "+sum);
            remainder = sum%16;
            System.out.println("Remainder: "+ remainder);

            if(checkSumClient == remainder)
            {
                System.out.println("Received messege correctly");
                dout.writeUTF("Received");

            }
            else
            {
                System.out.println("An Error has occured");
                dout.writeUTF("Error");

            }
            dout.flush();
            System.out.println("####################");

        } while (!s1.equals("stop"));
    }
}