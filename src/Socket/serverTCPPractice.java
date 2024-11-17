package Socket;

import java.io.*;
import java.net.*;



public class serverTCPPractice {
    static int port = 69;
    static int numberOfClient = 0;
    public static void main(String[] args){
        try{
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is running at port " + port +"...");
            while(true){
                Socket client = server.accept();
                ThreadService thread = new ThreadService(client,++numberOfClient);
                thread.start();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public static class ThreadService extends Thread {

        Socket clientSocket;
        int clientNumber;
        DataInputStream inFromClient;
        DataOutputStream outToClient;

        public ThreadService(Socket clientSocket,int clientNumber) {
            this.clientSocket = clientSocket;
            this.clientNumber = clientNumber;
            try{
                inFromClient = new DataInputStream(clientSocket.getInputStream());
                outToClient = new DataOutputStream(clientSocket.getOutputStream());
                System.out.println("Client #" + clientNumber + " joined successfully");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
        @Override
        public void run(){
            try{
                while(true){
                    String messageFromClient = inFromClient.readLine();
                    if(messageFromClient.toUpperCase().equals("QUIT")) break;
                    String modifiedMessage = messageFromClient.toUpperCase();
                    outToClient.writeBytes(modifiedMessage + "\n");
                    outToClient.flush();
                }
                inFromClient.close();
                outToClient.close();
                clientSocket.close();
                System.out.println("Client #" + clientNumber + " exited");
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
        }
    }
}
