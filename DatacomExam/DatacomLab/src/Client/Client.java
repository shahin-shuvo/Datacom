package Client;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * Created by shanto on 7/26/17.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s=new Socket("localhost",2000);
        System.out.println(s);
        din=new DataInputStream(s.getInputStream());
        dout=new DataOutputStream(s.getOutputStream());
        Scanner br=new Scanner(System.in);
        String input=" ";
        String s1;
        do{
            s1=clientInput();
            dout.writeUTF(s1);
            dout.flush();
            input=din.readUTF();
            System.out.println(input);
        }while(!s1.equals("stop"));
    }

    public static String clientInput() throws FileNotFoundException {
        FileReader fileReader=new FileReader("input");
        Scanner scanner=new Scanner(fileReader);
        String string=scanner.nextLine();
        char[] chars=string.toCharArray();
        int[] ints=new int[chars.length];
        int sum=0;
        for(int i=0;i<chars.length;i++)
        {
            ints[i]=chars[i];
            sum+=ints[i];

        }
        int remainder=sum%16;
        int remaninder1=remainder;
        Random random=new Random();
        int randomGenerator=random.nextInt(100);
        if(randomGenerator<30)
        {
            remainder=remainder+1;
            System.out.println("Error genarated : "+randomGenerator);
        }
        String text=String.valueOf(chars);
        System.out.println("Client -> Text : "+text+" ,Remainder :"+remainder);
        return text+remainder;
    }

}
