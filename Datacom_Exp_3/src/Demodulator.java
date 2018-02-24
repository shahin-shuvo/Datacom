import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class Demodulator {
    static int portNumber = 4200;
    public static void main(String[] args) throws IOException {
        Socket echoSocket = new Socket("localhost", portNumber);
        DataOutputStream out = new DataOutputStream(echoSocket.getOutputStream());
        DataInputStream in = new DataInputStream(echoSocket.getInputStream());

        int noOfUser = 3;
        String[] spreadingCodes = {"0101","0011","0000"};

        FileReader[] fr = new FileReader[noOfUser];
        BufferedReader[] br = new BufferedReader[noOfUser];

        for (int i = 0; i < noOfUser; i++) {
            String fileName = "f" + i + ".txt";
            try {
                fr[i] = new FileReader(fileName);
                br[i] = new BufferedReader(fr[i]);
            } catch (FileNotFoundException ex) {
                System.out.println(fileName + "Not found");
            }
        }

        int i=0,j=0,lines=0;


        String[] readString = new String[3];
        StringBuilder[] finalString = new StringBuilder[3];
            for (i = 0; i < noOfUser; i++) {
                String fileName = "f" + i + ".txt";
                File  f = new File(fileName);
                int bytes = (int)f.length();
                char[] array = new char[bytes];
                try {
                    br[i].read(array, 0, bytes);
                    readString[i] = new String(array);
                    System.out.println("\nActual string :-\n" + readString[i]);

                    System.out.println("Before Spreading : " + toBinaryString(readString[i]));
                    finalString[i] = spreadString(toBinaryString(readString[i]), spreadingCodes[i]);
                    System.out.println("After spreading : " + finalString[i]);

                } catch (IOException ex) {
                    System.out.println("Reading failed");
                }
            }

            StringBuilder FinalDataSignal = createFinalSignal(finalString[0], finalString[1], finalString[2]);

            System.out.println("\nFinal Signal :\n" + FinalDataSignal);
            out.writeUTF(FinalDataSignal.toString());
            out.flush();
        }

    public static StringBuilder spreadString(String binary, String spreadCode){
        StringBuilder stringAfterSpread = new StringBuilder();
        for(int i = 0; i< binary.length();i++){
            char char1 = binary.charAt(i);
            stringAfterSpread.append(spreadBit(spreadCode, char1));
        }
        return stringAfterSpread;
    }

    public static StringBuffer spreadBit(String spreadCode, char c){
        StringBuffer spreadString = new StringBuffer();
        for(int j =0; j<4; j++){
            if(c == spreadCode.charAt(j)){

                spreadString.append("0");
            }
            else{
                spreadString.append("1");
            }
        }
        return spreadString;
    }

    public static String toBinaryString(String text){
        return new BigInteger(text.getBytes()).toString(2);
    }

    public static StringBuilder createFinalSignal(StringBuilder s1, StringBuilder s2, StringBuilder s3){
        StringBuilder str = new StringBuilder();
        for(int i= 0; i< s1.length(); i++){
            int temp = 0;
            temp+=CreateVoltageSignal(s1.charAt(i));
            temp+=CreateVoltageSignal(s2.charAt(i));
            temp+=CreateVoltageSignal(s3.charAt(i));
            if(temp>=0){
                str.append("+");
            }
            str.append(String.valueOf(temp));
        }
        return str;
    }

    public static int CreateVoltageSignal(char ch){
        if(ch == '0'){
            return 1;
        }
        else{
            return -1;
        }
    }

    public static String toActualString(String s){
        String text2 = new String(new BigInteger(s, 2).toByteArray());
        return text2;
    }

}

