package Client;

import java.io.*;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {
    static int maxFileSize=-99;
    static FileReader fileReader[]=new FileReader[5];
    static BufferedReader br[]=new BufferedReader[5];
    static File fileNameArray[]=new File[5];
    static Scanner sc[]=new Scanner[5];
    static FileInputStream fis[]=new FileInputStream[5];
    static String AllData[]=new String[5];
    static int[] count=new int[5];
    static int v=0;


    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s=new Socket("localhost",2200);
        // System.out.println(s);
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        String s1;
        for(int i =0;i<5;i++)
        {
            count[i]=0;
        }
        FileSender();
        do{

            dout.writeUTF(FrameCreator());
            v++;
            dout.flush();
            if(maxFileSize<v*10){
               break;
            }
        }while(true);
    }

    public static void FileSender() throws IOException {
        String fileName[] = {"input1.txt", "input2.txt", "input3.txt", "input4.txt", "input5.txt"};



        int flag = 0;
        if (flag == 0) {
            for (int i = 0; i < 5; i++) {
                fileNameArray[i] = new File(fileName[i]);
                if (!fileNameArray[i].exists()) {
                    fileNameArray[i].createNewFile();
                }

                if(fileNameArray[i].length()>maxFileSize){
                    maxFileSize= (int) fileNameArray[i].length();
                }

                fileReader[i]=new FileReader(fileNameArray[i]);
                sc[i]=new Scanner(fileReader[i]);
            }
        }




        for(int i=0;i<5;i++)
        {
            String oneFile="";
            while (sc[i].hasNextLine())
            {
                oneFile=oneFile+sc[i].nextLine()+"\n";
            }
            AllData[i]=oneFile;
        }
    }



    public static String FrameCreator() throws IOException {

        String IndividualFile="";
        String TenBit ="",frame="";

        boolean flag2=true;
        for(int i=0;i<5;i++)
        {
            Random rand = new Random();
            int r = rand.nextInt(100);
            System.out.println("Random: "+r);
            if(r<50) {
                try {
                    IndividualFile = AllData[i].substring(count[i] * 10, count[i] * 10 + 10);
                } catch (Exception e) {

                }


                if (flag2) {
                    TenBit = "//" + fileNameArray[i].getName() + "#output" + (i + 1) + ".txt#" + IndividualFile + "//";
                } else if (!flag2) {
                    TenBit = fileNameArray[i].getName() + "#output" + (i + 1) + ".txt#" + IndividualFile + "//";
                }
                flag2 = false;
                frame = frame + TenBit;
                count[i]++;

            }


        }
        frame="!!"+frame+"!!";
        System.out.println("Client : "+frame);
        System.out.println();

        return frame;

    }

}
