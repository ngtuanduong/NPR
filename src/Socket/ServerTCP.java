package Socket;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class ServerTCP {
    static ServerSocket server;
    static int clientNumbers;

    public static class ServiceThread extends  Thread {

        Socket connection;
        int clientNumber;
        DataInputStream dis;
        DataOutputStream dos;
        private ServiceThread(Socket connection,int clientNumber) throws IOException {
            this.connection = connection;
            this.clientNumber = clientNumber;
            dis = new DataInputStream(connection.getInputStream());
            dos = new DataOutputStream(connection.getOutputStream());
            System.out.println("Client #" +clientNumber+ " accepted");

        }
        @Override
        public void run() {
            try{
                while(true){
                    String capitalize = dis.readLine().toUpperCase();
//                    BigInteger a = new BigInteger(dis.readLine());
//                    int num = Integer.parseInt(dis.readLine());
//                    num =(int) Math.pow(num,2);
//                    a = a.pow(2);
//                    System.out.println(a);



                    if(capitalize.equals("QUIT")) break;
                    dos.writeBytes(capitalize + "\n");
                    dos.flush();
                }
                System.out.println("Client #" +clientNumber+ " quit");
            }catch(IOException e){
                System.out.println(e);
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args){
        clientNumbers = 0;
        try{
            server = new ServerSocket(69);
            while(true){
                Socket clientSocket = server.accept();
                new ServiceThread(clientSocket,++clientNumbers).start();
            }
        }catch(IOException e){
            System.out.print(e);
        }


    }
}
