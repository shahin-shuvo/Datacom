package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by shanto on 8/18/17.
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
            try{
                str=din.readUTF();
            }catch (Exception e){
                break;
            }
            System.out.println(str);
            System.out.println();
            Receive(str);
            dout.flush();
        }while(!str.equals("stop"));
    }

    public static void Receive(String received) throws IOException {
        String frameSplit[]=received.split("@");

        ArrayList<String> arrayList=new ArrayList<>();


        String fileSplit[]=frameSplit[1].split("//");

        for(int i=0;i<fileSplit.length;i++){

            if(fileSplit[i].length()>0){
                arrayList.add(fileSplit[i]);
            }

        }




        String fileInput[]=new String[arrayList.size()];

        for(int i=0;i<arrayList.size();i++){
            fileInput[i]=arrayList.get(i);
        }

        arrayList.clear();

        int j=0;
        for(int i=0;i<fileSplit.length;i++)
        {
            if(fileSplit[i].length()>0)
            {

                fileInput[j]=fileSplit[i];
                j++;
            }

        }
        String doc[];
        File file[]=new File[5];
        FileWriter fileWriter[]=new FileWriter[5];
        for(int i=0;i<fileInput.length;i++) {
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
