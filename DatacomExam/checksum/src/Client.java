import java.net.*;
import java.io.*;
import java.util.*;
public class Client {

    public static void main(String args[]) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s=new Socket("127.0.0.1",2000);
        System.out.println(s);
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        String s1;
        String str="";
        String m = Sender();
        do{
           // s1=br.next();
           try{
               dout.writeUTF(m);
               str=din.readUTF();
           }
           catch (Exception e)
           {
               break;
           }
            System.out.println(str);
            dout.flush();
        }while(true);
    }
    public static String Sender() throws IOException {
        String fileName = "file1.txt";

       FileReader f = new FileReader(fileName);
       Scanner sc = new Scanner(f);
           String oneFile="";
            while (sc.hasNextLine())
            {
                oneFile=oneFile+sc.nextLine()+"\n";
            }

            System.out.println(oneFile);
            int divisor = 16;
            int temp=0;
            for(int i = 0; i<oneFile.length(); i++)
            {
                        int k = oneFile.charAt(i);
                        temp+=k;
            }
            int checksum = temp%16;
            Random r = new Random();
            int x = r.nextInt(100);
        System.out.println("Random Number : "+x);
            if(x<30)
                checksum+=2;
            oneFile=oneFile+","+Integer.toString(checksum);
            System.out.println("String with Checksum: "+oneFile);
        return oneFile;
    }

}


