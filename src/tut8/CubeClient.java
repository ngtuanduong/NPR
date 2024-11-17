package tut8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CubeClient {
    public static void main(String[] args){
        try{
            //Create client socket
            DatagramSocket clientSocket = new DatagramSocket(8000);//throw SocketException
            //Specify ip and port of the server
            InetAddress serverAddress = InetAddress.getByName("localhost"); //throw UnknownHostException
            int serverPort = 3000;

            while(true){
                //Take input from keyboard
                System.out.println("Send to server: ");
                BufferedReader inFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
                String sentence_to_send = inFromKeyboard.readLine();

                //Create data to send
                byte[] sendData;
                sendData = sentence_to_send.getBytes();
                //Crate a packet to send include data, length of that data, server address and server port
                DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,serverAddress,serverPort);
                //Send data to server
                clientSocket.send(sendPacket);

                //Create a array to store data from client
                byte[] receiveData = new byte[2000];
                //Create a packet to receive data from server
                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

                //Receive data from server and print to client's console
                clientSocket.receive(receivePacket);
                String receivedSentence = new String(receivePacket.getData());
                receivedSentence = receivedSentence.trim();
                System.out.println("FROM SERVER: " + receivedSentence);
            }
        }catch(UnknownHostException e){
            System.out.println(e);
            e.printStackTrace();
        }catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }


    }
}
