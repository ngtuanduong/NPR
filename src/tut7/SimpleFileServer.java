package tut7;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer {

    static FileOutputStream fos;
    static DataInputStream dis;
    static int clientNumbers;
    static String filename = "copy.jpg";

    public static class ServiceThread extends Thread{
        Socket clientSocket;
        int clientNumber;

        public ServiceThread(Socket clientSocket,int clientNumber) throws IOException{

            this.clientSocket = clientSocket;
            this.clientNumber = clientNumber;
            dis = new DataInputStream(clientSocket.getInputStream());
            System.out.println("Client #" + clientNumber + " connected");
        }
        @Override
        public void run(){
            try{
                fos = new FileOutputStream(filename);
                int bytes;
                byte[] buffer = new byte[1024*70];
                long size = dis.readLong();
                while(size > 0 && (bytes = dis.read(buffer,0,(int)Math.min(size,buffer.length))) != -1){
                    fos.write(buffer,0,bytes);
                    size -= bytes;
                }
                dis.close();
                fos.close();
                clientSocket.close();
            }catch(IOException e){
                System.out.println(e);
                e.printStackTrace();
            }

        }
    }


    public static void main(String[] args) {
        clientNumbers = 0;
        try(ServerSocket server = new ServerSocket(9999)){
            while(true){
                Socket client = server.accept();
                ServiceThread service = new ServiceThread(client,++clientNumbers);
                service.start();
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
