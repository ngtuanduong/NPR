package Socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String[] args){
        try{
            Socket clientSocket = new Socket("localhost",69);
            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            Scanner sc = new Scanner(System.in);
            System.out.println("Your name: ");
            String name = sc.nextLine();
            System.out.println("Your studentID: ");
            String sid = sc.nextLine();
            String message = "Hello, my name is " + name + ", my ID is " + sid;
            dos.writeBytes(message + "\n");
            System.out.println("From server: " +dis.readLine());
            dos.flush();
            while(true){
                System.out.println("Your message (\"quit\" to exit): ");
                message = sc.nextLine();
                dos.writeBytes(message + "\n");
                dos.flush();
                if(message.toLowerCase().equals("quit")) break;
                System.out.println("From server: " +dis.readLine());

            }

            clientSocket.close();
            dis.close();
            dos.close();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
