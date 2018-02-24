package Server;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by shanto on 8/9/17.
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
        String s1;
        do{
            str=din.readUTF();
            System.out.println(str);
            Receive(str);
            dout.flush();
        }while(!str.equals("stop"));
    }

    public static void Receive(String received) throws IOException {
        String frameSplit[]=received.split("@");
        String fileSplit[]=frameSplit[1].split("//");
        String fileInput[]=new String[5];
        int j=0;
        for(int i=0;i<fileSplit.length;i++)
        {
            //System.out.println(fileSplit[i]);
            if(fileSplit[i].length()>0)
            {

                fileInput[j]=fileSplit[i];
                j++;
            }
            //System.out.println(fileSplit[i].length());

        }
        //System.out.println(fileSplit.length);
        String doc[];
        File file[]=new File[5];
        FileWriter fileWriter[]=new FileWriter[5];
        for(int i=0;i<5;i++) {
            //System.out.println(fileInput[i]);
            doc = fileInput[i].split("#");
            file[i] = new File(doc[1]);
            if (!file[i].exists()) {
                file[i].createNewFile();

            }
            try {
                fileWriter[i]=new FileWriter(file[i],true);
                fileWriter[i].write(doc[2]);
                fileWriter[i].flush();
                fileWriter[i].close();
            } catch (Exception e){
                System.out.println("File transmission ended");
            }



        }
    }
}
