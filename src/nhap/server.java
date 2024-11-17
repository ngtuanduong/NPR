package nhap;
import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class server {
    public static void main (String[] args){
        int clientNumber=0;
        BigInteger a = new BigInteger("23");
        BigInteger b = new BigInteger("100");
         a = a.multiply(b);
        System.out.println(a);
        try {
            ServerSocket serverSocket = new ServerSocket(1907);
            System.out.println("listening");

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("connected");

                Core newThread = new Core(clientSocket, ++clientNumber);
                System.out.println("client number " + clientNumber + "connected");
                newThread.start();

            }

        } catch (Exception e){
            System.out.println("fail");
        }
    }

    public static class Core extends Thread {

        Socket clientSocket;
        int clientNumber;

        public Core (Socket clientSocket, int clientNumber ){
            this.clientSocket = clientSocket;
            this.clientNumber = clientNumber;
        }

        @Override
        public void run() {
            try {
                DataInputStream doc;
                DataOutputStream ghi;

                while (true) {
                    doc = new DataInputStream (clientSocket.getInputStream());
                    ghi = new DataOutputStream (clientSocket.getOutputStream());

                    BigInteger a = new BigInteger(doc.readLine());
                    a = a.pow(2);
                    System.out.println(a);
                    ghi.writeBytes(a + "\n");
                    ghi.flush();
                }

            } catch(Exception e) {
                System.out.print("fail");
            }
        }

    }
}
