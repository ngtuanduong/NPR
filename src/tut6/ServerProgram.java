//package tut6;
//
//import java.io.*;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//public class ServerProgram  {
//
////    private static List<String> orders = Collections.synchronizedList(new ArrayList<>());
////    private static List<String> messages = Collections.synchronizedList(new ArrayList<>());
////    private static List<ServiceThread> serviceThreads = Collections.synchronizedList(new ArrayList<>());
//
//    public static void main(String[] args) throws IOException {
//        ServerSocket listener = null;
//
//        System.out.println("Server started!");
//        int clientNumber = 0;
//
//        try{
//            listener = new ServerSocket(9999);
//        }catch (Exception e){
//            System.out.println(e);
//            System.exit(1);
//        }
//
//        try{
//            while (true){
//                Socket socketOfserver = listener.accept();
////                ServiceThread serviceThread =  new ServiceThread(socketOfserver,clientNumber++,orders,messages,serviceThreads);
//                ServiceThread serviceThread =  new ServiceThread(socketOfserver,clientNumber++);
//                serviceThreads.add(serviceThread);
//                for(ServiceThread st : serviceThreads){
//                    System.out.println(st.clientName);
//                }
//                new Thread(serviceThread).start();
//            }
//        }finally {
//            listener.close();
//        }
//    }
//}
//
