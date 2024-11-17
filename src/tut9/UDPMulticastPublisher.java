package tut9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMulticastPublisher {
    public static void sendUDPMessage(String message,String ipAddress,int port) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(ipAddress);
        byte[] msg = message.getBytes();
        DatagramPacket packet = new DatagramPacket(msg,msg.length,group,port);
        socket.send(packet);
        socket.close();
    }

    public static void main(String[] args)  {
        int n = 1;
        try{
            while (true) {
                String s = "Hello ";
                sendUDPMessage(n++ +": " +  s,"230.0.0.0",4321);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
}
