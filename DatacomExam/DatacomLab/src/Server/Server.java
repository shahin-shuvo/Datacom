package Server;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by shanto on 7/26/17.
 */
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
        Scanner scanner=new Scanner(System.in);
        String s1;
        do{
            str=din.readUTF();
            System.out.println("Server -> "+str+" is "+checker(str));
            dout.flush();
            s1=scanner.next();
            dout.writeUTF(s1);
            dout.flush();

        }while(!str.equals("stop"));
    }

    public static boolean checker(String string)
    {
        char[] chars=string.toCharArray();
        int[] intArray=new int[chars.length];
        int clientCheksum=0,sum=0;
        for(int i=0;i<chars.length-1;i++)
        {
            intArray[i]=chars[i];
            sum+=intArray[i];
            //System.out.println(sum);

        }
        clientCheksum=Integer.parseInt(String.valueOf(chars[chars.length-1]));
        //System.out.println("client checksum "+clientCheksum);
        //System.out.println("char value : "+chars[chars.length-1]);
        int remainder=sum%16;
        System.out.println("Server -> Orginal remainder : "+remainder+", ClientSent : "+clientCheksum);
        //System.out.println(intArray.length);
        //System.out.println("Sum : "+sum);
        if(remainder==clientCheksum)
        {
            return true;
        }
        return false;
    }
}
