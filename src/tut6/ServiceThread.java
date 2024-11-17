package tut6;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServiceThread implements Runnable {
    private int clientNumber;
    String clientName;
    private Socket socketOfServer;
//    private List<String> messages ;
    private List<String> orders ;
//    private List<ServiceThread> serviceThreads ;
    private BufferedReader is;


//    public ServiceThread(Socket socketOfServer, int clientNumber,List<String> orders, List<String> messages,List<ServiceThread> serviceThreads) throws IOException {
    public ServiceThread(Socket socketOfServer, int clientNumber) throws IOException {
        this.socketOfServer = socketOfServer;
        this.is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));
        this.clientName = is.readLine();
        this.clientNumber = clientNumber;
//        this.messages = messages;
//        this.orders = orders;
//        this.serviceThreads = serviceThreads;
        System.out.println("New connection with client# " + this.clientNumber + " at " + socketOfServer);
    }

    @Override
    public void run() {
        try{


            while (true) {
                String line = is.readLine();
                BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
                os.write(line.toUpperCase());
//                orders.add(clientName);
//                broadcastMessage(line,this);

            }

        }catch (Exception e){
            System.out.println(e);
//            disconnectClient();
        }
    }
//    public void sendMessage(ServiceThread sender,String message) throws IOException {
//        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
//
//        os.write(sender.clientName);
//        os.newLine();
//        os.write(message);
//        os.newLine();
//        os.flush();
//    }

    // Method to broadcast a message to all clients
//    private void broadcastMessage(String message, ServiceThread sender) throws IOException {
//        synchronized (sender) {
//            for (ServiceThread client : serviceThreads) {
//                if(client != sender){
//                    client.sendMessage(sender,message);
//                }
//            }
//        }
//    }
//    private void disconnectClient() {
//        try {
//            System.out.println(clientName + " has left the chat.");
////            serviceThreads.remove(this);
//            broadcastMessage(clientName + " has left the chat.", this);
//            socketOfServer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}