import java.net.*;
import java.io.*;
import java.util.*;
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream din;
        DataOutputStream dout;
        s = new Socket("127.0.0.1", 2200);
        din = new DataInputStream(s.getInputStream());
        dout = new DataOutputStream(s.getOutputStream());
        FileReader fin = new FileReader("input.txt");
        Scanner br = new Scanner(fin);
        String s1 = null;
        String s2 = null;
        do {
            while(br.hasNextLine())
            {
                int arry[] = new int[100];
                int sum=0,index=0;
                int remainder,checkSum;
                String tmp_int = "";
                s1 = br.nextLine();
                String ar[] = s1.split(" ");
                for(int i =0;i<ar.length;i++)
                {
                    for(int j=0; j<ar[i].length(); j++)
                    {
                        char ch = ar[i].charAt(j);
                        int ascii = (int) ch;
                        sum = sum + ascii;
                    }
                }

                System.out.println("Sum: "+sum);

                remainder = sum%16;
                Random rand = new Random();

                int query = rand.nextInt(100);
                System.out.println("Random : "+query);

                if(query<30) checkSum = remainder+1;

                else  checkSum = remainder;
                s2 = s1+" "+Integer.toString(checkSum);

                System.out.println("Final string with checksum: "+s2);
                dout.writeUTF(s2);


                String ss = din.readUTF();
                System.out.println(ss);
                dout.flush();
                System.out.println("####################");

            }

        } while (!s1.equals("stop"));
    }
}