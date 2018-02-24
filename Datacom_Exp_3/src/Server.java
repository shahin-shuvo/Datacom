import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public  class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(4200);
        Socket clientSocket = serverSocket.accept();
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        String str = in.readUTF();

        int[] spread1 = {1,-1,1,-1};
        int[] spread2 = {1,1,-1,-1};
        int[] spread3 = {1,1,1,1};

        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int i =0; i<str.length(); i+=2){
            data.add(Integer.parseInt(str.substring(i, i+2)));
        }

        StringBuilder s1 = getOne(data, spread1);
        StringBuilder s2 = getOne(data, spread2);
        StringBuilder s3 = getOne(data, spread3);



        String fs1 = toActualString(s1);
        String fs2 = toActualString(s2);
        String fs3 = toActualString(s3);


        System.out.println("1st User:");
        System.out.println(fs1);
        System.out.println();
        System.out.println("After Check sum by the divisor 6:");
        doChecksum(fs1,6);

        System.out.println();
        System.out.println("2nd User:");
        System.out.println(fs2);
        System.out.println();
        System.out.println();
        System.out.println("After Check sum  by the divisor 17:");
        doChecksum(fs2,17);

        System.out.println("3rd User:");
        System.out.println(fs3);
        System.out.println();
        System.out.println();
        System.out.println("After Check sum  by the divisor 8:");
        doChecksum(fs3,8);

        System.out.println("After Spreading:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);



        FileWriter fw1 = new FileWriter("f_out_1.txt");
        fw1.write(fs1);
        fw1.flush();

        FileWriter fw2 = new FileWriter("f_out_2.txt");
        fw2.write(fs2);
        fw2.flush();

        FileWriter fw3 = new FileWriter("f_out_3.txt");
        fw3.write(fs3);
        fw3.flush();

        in.close();
        out.close();
        fw1.close();
        fw2.close();
        fw3.close();
    }

    public static StringBuilder getOne(ArrayList<Integer> data, int [] spread){
        StringBuilder out = new StringBuilder();
        for(int i =0; i< data.size(); i+=4){
            int temp = 0;
            temp+=data.get(i) * spread[0];
            temp+=data.get(i+1) * spread[1];
            temp+=data.get(i+2) * spread[2];
            temp+=data.get(i+3) * spread[3];

            if(temp/4 == -1){
                out.append("1");
            }else{
                out.append("0");
            }
        }
        return out;
    }

    public static String toActualString(StringBuilder s){
        String text2 = new String(new BigInteger(s.toString(), 2).toByteArray());
        return text2;
    }

    public static void doChecksum(String fs,int divisor)
    {
        int arry[] = new int[100];
        int sum=0,index=0;
        int remainder,checkSum;
        String tmp_int = "";
        String ss = fs;
        String ar[] = ss.split(" ");
        for(int i =0;i<ar.length;i++)
        {
            for(int j=0; j<ar[i].length()-1; j=j+2)
            {
                char ch = ar[i].charAt(j);
                char ch1 = ar[i].charAt(j+1);
                int ascii = (int) ch;
                int ascii2 = (int) ch1;
                sum = sum + ascii+ascii2;
            }
        }

        System.out.println("Sum: "+sum);

        remainder = sum%divisor;

         checkSum = remainder;


        ss = ss+" "+Integer.toString(checkSum);

        System.out.println("Final string with checksum: "+ss);
        System.out.println();
    }

}
