import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2200);

        System.out.println("Server waiting on port 2200");
        Socket clientSocket = serverSocket.accept();
        System.out.println(clientSocket + "\n");
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        int i, j;

        String inputLine;
        BufferedWriter bw = null;
        FileWriter fw = null;

        while (true) {
            inputLine = in.readUTF();
            if (inputLine.equals("END")) {
                //System.out.println(inputLine);
                break;
            }
            System.out.println(inputLine);

            String[] received = inputLine.split("%");
            /*
            String slot1 = received[1];
            String slot2 = received[2];
            String slot3 = received[3];
            String slot4 = received[4];
            String slot5 = received[5];
                    */

            for (i = 1; i < received.length -1; i++) {
                String[] data = received[i].split("#");
                String fname = data[2];
                String data1 = data[3];
                data1 = data1 + "\n";
                //System.out.println(data1);
                String filename = fname + ".txt";
                fw = new FileWriter(filename, true);
                fw.write(data1);
                fw.flush();
            }


        }

    }
}
