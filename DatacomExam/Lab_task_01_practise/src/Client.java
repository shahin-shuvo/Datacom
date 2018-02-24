import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args) throws IOException {
        Socket s;
        DataInputStream dis;
        DataOutputStream dos;

        s = new Socket("localhost", 2200);
        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());

        FileReader fileReader = new FileReader("input.txt");
        Scanner sc = new Scanner(fileReader);

        String s1 = null;
        String s2 = null;

        do {
            while (sc.hasNextLine())
            {
                s1 = sc.nextLine();
                for (int i=0; i<s1.length(); i++)
                {
                    char s3 = s1.charAt(i);
                    int ascii = s3;
                    //System.out.println(ascii);
                    String binaryReprestation = Integer.toBinaryString(ascii);
                    int cnt = 0;

                    for (int j=0; j<binaryReprestation.length(); j++)
                    {
                        if (binaryReprestation.charAt(j)=='1') cnt++;
                    }

                    int num = Integer.parseInt(binaryReprestation);

                    ascii = ascii<<1;

                    if(cnt%2==0) ascii = ascii|1;
                    String c = Integer.toString(ascii);
                    dos.writeUTF(c);
                    dos.flush();
                }
                dos.writeUTF("20");

                dos.flush();

            }
        }
        while (!s1.equals("stop"));
    }
}
