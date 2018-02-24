import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss;
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        System.out.println("Server START......");
        ss=new ServerSocket(2200);
        s=ss.accept();
        System.out.println("Client Connected.....");
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        String str="";
        String s1;
        do{
            try{
                str=din.readUTF();
            }catch (Exception e){
                break;
            }
            System.out.println(str);
            Receive(str);
            dout.flush();
        }while(!str.equals("stop"));
    }

    public static void Receive(String received) throws IOException {
        String frameSplit[]=received.split("!!");
        String fileSplit[]=frameSplit[1].split("//");
        String fileInput[]=new String[5];
        int j=0;
        for(int i=0;i<fileSplit.length;i++)
        {
            System.out.println(frameSplit[1]);

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
            System.out.println(doc[1]);
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
