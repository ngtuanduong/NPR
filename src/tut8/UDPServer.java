package tut8;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {

    public static void main(String[] args) {
        try{
            //Create a UDP server
            DatagramSocket server = new DatagramSocket(3000);//throw SocketException

            while(true){
                //Create byte array to store data
                byte[] receiveData = new byte[4096];
                //Create a packet to receive the data
                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
                //Store data from client to the packet
                server.receive(receivePacket);// throw IOException

                //Print the sentence from client to server's console
                String sentence = new String (receivePacket.getData());
                sentence = sentence.trim();
                System.out.println("Message from client: " + sentence);

                //Specify client by info from the receive packet
                InetAddress clientIP = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                //Create data to send to client
                String sentence_to_send = sentence.toUpperCase();
                byte[] sendData;
                sendData = sentence_to_send.getBytes();

                //Create a packet to send that data
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,clientIP,clientPort);
                //Send data to that client
                server.send(sendPacket);
            }
        }catch(SocketException e){
            System.out.println(e);
            e.printStackTrace();
        }catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
