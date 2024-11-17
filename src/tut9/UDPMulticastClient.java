package tut9;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPMulticastClient implements Runnable{

    public static void main(String[]  args){
        Thread t = new Thread(new UDPMulticastClient());
        t.start();
    }

    public void receiveUDPMessage(String ip,int port) throws IOException {
        byte[] buffer = new byte[1024];
        MulticastSocket socket = new MulticastSocket(port);
        InetAddress group = InetAddress.getByName(ip);
        socket.joinGroup(group);
        System.out.println("Waiting for message...");
        int n = 1;
        while(true){
            DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
            socket.receive(packet);
            String message = new String(packet.getData(),packet.getOffset(),packet.getLength());
            System.out.println("[Multicast message received] " + n++ + ": " + message);
            if("OK".equals(message)){
                System.out.println("No more message. Exiting: " + message );
                break;
            }
        }
        socket.leaveGroup(group);
        socket.close();
    }

    @Override
    public void run() {
        try{
            receiveUDPMessage("230.0.0.0",4321);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
