package Client;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by shanto on 8/18/17.
 */
public class Client {

    static File file[]=new File[5];
    static Scanner scanners[]=new Scanner[5];
    static FileReader fileReader[]=new FileReader[5];
    static BufferedReader br[]=new BufferedReader[5];
    static FileInputStream fis[]=new FileInputStream[5];
    static String allFileEle[]=new String[5];
    static int maxFileSize=0;


    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s=new Socket("127.0.0.1",2000);
        System.out.println(s);
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        HelperTwo();
        String s1;
        int cnt=0;
        Sender();
        do{

            dout.writeUTF(Helper());
            cnt++;
            dout.flush();
            if(maxFileSize<cnt*10){
                break;
            }
        }while(true);
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

                if(file[i].length()>maxFileSize){
                    maxFileSize= (int) file[i].length();
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
            }
            allFileEle[i]=oneFile;
        }
    }


    static int track[]=new int[5];
    static int count[]=new int[5];

    public static void HelperTwo(){

        for(int i=0;i<5;i++){
            track[i]=0;
            count[i]=0;
        }
    }

    public static String Helper() throws IOException {
        String eachFile="";
        String TenChar ="",frame="";




        for(int i=0;i<5;i++)
        {
            try {

                eachFile=allFileEle[i].substring(count[i]*10,count[i]*10+10);
            }catch (Exception e)
            {
                System.out.println("You have reached end of the file");
            }

            Random randomGen=new Random();

            int r = randomGen.nextInt(100);
            System.out.println(r);

                if(r<50){


                    TenChar="//"+file[i].getName()+"#output"+(i+1)+".txt#"+eachFile+"//";
                    count[i]++;
                }else{
                    track[i]++;
                }


            frame=frame+TenChar;
            TenChar="";

        }
        frame="@"+frame+"@";
        System.out.println("Client : "+frame);
        System.out.println();

        return frame;

    }

}
