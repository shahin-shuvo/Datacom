package datacom2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;


public class Client {

    static String hostName = "localhost";
    static int portNumber = 2200;
    static int divisor = 16;

    public static void main(String[] args) throws IOException {
        Socket echoSocket = new Socket("localhost", portNumber);
        DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream());
        DataInputStream in = new DataInputStream(echoSocket.getInputStream());
        FileReader[] fr = new FileReader[6];
        for (int i = 1; i < 6; i++) {
            String fileName = "f" + i + ".txt";
            fr[i] = new FileReader(fileName);
        }
        //FileReader fr = new FileReader("input.txt");
        Scanner[] sc = new Scanner[6];
        BufferedReader[] br = new BufferedReader[6];
        for (int i = 1; i < 6; i++) {
            //sc[i] = new Scanner(fr[i]);
            br[i] = new BufferedReader(fr[i]);
        }

        String startMarker = "@";
        String EndMarker = "$";
        String[] Frame = new String[6];
        try {
            for (int i = 1; i < 6; i++) {
                //System.out.println("i = " + i);
                String[] part = new String[5];
                for (int j = 1; j < 6; j++) {
                    //System.out.println("j = " + j);
                    int bytes = 10;
                    char[] array = new char[bytes];
                    br[j].read(array, 0, 10);
                    part[i] = new String(array);
                    String src = "f" + j;
                    String dst = src + "00";
                    Frame[j] = startMarker + "#" + src + "#" + dst + "#" + part[i] + "#" + EndMarker;
                    //System.out.println(Frame[i]);
                }
                
                String finalframe = "^"+ "%" + Frame[1] + "%" + Frame[2] + "%" + Frame[3] + "%" +Frame[4] + "%" +Frame[5] + "%" + "^^";
                System.out.println("Frame : " + finalframe);
                out.writeUTF(finalframe);
                out.flush();
            }
        }
    catch(NoSuchElementException e ){
            
        }
    catch(Exception eeee){
            
        }
    
        out.writeUTF("END");

       // in.close();
       // out.close();

    }
                
    
}
