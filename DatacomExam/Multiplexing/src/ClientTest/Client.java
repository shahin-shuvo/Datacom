package ClientTest;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by shanto on 8/9/17.
 */
public class Client {

    static File file[]=new File[5];
    static Scanner scanners[]=new Scanner[5];
    static FileReader fileReader[]=new FileReader[5];
    static BufferedReader br[]=new BufferedReader[5];
    static FileInputStream fis[]=new FileInputStream[5];
    static String allFileEle[]=new String[5];


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
        int cnt=0;
        Sender();
        do{
            s1=br.next();

            dout.writeUTF(Helper(cnt));
            cnt++;
            dout.flush();
        }while(!s1.equals("stop"));
    }

    public static void Sender() throws IOException {
        String fileName[] = {"file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt"};


        int flag = 0;
        if (flag == 0) {
            for (int i = 0; i < 5; i++) {
                file[i] = new File(fileName[i]);
                if (!file[i].exists()) {
                    file[i].createNewFile();
                }
                fileReader[i]=new FileReader(file[i]);
                scanners[i]=new Scanner(fileReader[i]);
            }
        }




        for(int i=0;i<5;i++)
        {
            String oneFile="";
            while (scanners[i].hasNextLine())
            {
                oneFile=oneFile+scanners[i].nextLine()+"\n";
                //System.out.println(oneFile);
            }
            allFileEle[i]=oneFile;
            System.out.println(allFileEle[i]);
        }
    }



    public static String Helper(int num) throws IOException {
        //char chars[]=new char[10];
        String eachFile="";
        String TenChar ="",frame="";
        //String temp="";
        //int cnt=0;

        for(int i=0;i<5;i++)
        {
                //chars[j]= (char) fis[i].read();
                //temp=br[i].readLine();
            try {
                eachFile=allFileEle[i].substring(num*10,num*10+10);
                System.out.println(eachFile);
            }catch (Exception e)
            {
                System.out.println("You have reached end of the file");
            }

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
            //cnt++;

        }
        frame="@"+frame+"@";
        System.out.println("Client : "+frame);

        return frame;

    }


}
