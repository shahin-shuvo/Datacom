package Client;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by shanto on 8/9/17.
 */
public class Client {

    static File file[]=new File[5];
    //BufferedReader br[]=new BufferedReader[5];
    static FileInputStream fis[]=new FileInputStream[5];


    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s=new Socket("127.0.0.1",2000);
        System.out.println(s);
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        String s1;
        Sender();
        do{
            s1=br.next();
            dout.writeUTF(Helper());
            dout.flush();
        }while(!s1.equals("stop"));
    }

    public static void Sender() throws IOException {
        String fileName[] = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt"};


        int flag = 0;
        if (flag == 0) {
            for (int i = 0; i < 5; i++) {
                file[i] = new File(fileName[i]);
                //System.out.println(file[i].getName());
                if (!file[i].exists()) {
                    file[i].createNewFile();
                }
                //br[i]=new BufferedReader(new FileInputStream(file[i]));
                fis[i] = new FileInputStream(file[i]);

            }
        }
    }

public static String finalWord;

    /*public static String HelperTwo() throws IOException {
        for(int index=0;index<5;index++)
        {
            while (fis[index].read()!=-1)
            {
                return finalWord= Helper();
            }
        }
        return finalWord;
    }*/


    public static String Helper() throws IOException {
        char chars[]=new char[10];
        String eachFile;
        String TenChar ="",frame="";


        for(int i=0;i<5;i++)
        {
            for(int j=0;j<10;j++)
            {
                chars[j]= (char) fis[i].read();


            }
            //eachFile=chars.toString();
            eachFile=String.valueOf(chars);
            //System.out.println(eachFile);
            boolean flagTwo=true;
            if(flagTwo)
            {
                TenChar="//"+file[i].getName()+"#output"+(i+1)+".txt#"+eachFile+"//";
            }
            else if(!flagTwo)
            {
                TenChar=file[i].getName()+"#output"+(i+1)+".txt#"+eachFile+"//";
            }
            flagTwo=false;
            frame=frame+TenChar;

        }
        frame="@"+frame+"@";
        System.out.println("Client : "+frame);

        return frame;

    }

}
