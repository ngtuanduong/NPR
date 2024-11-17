package Socket;

import java.io.*;
import java.net.*;

public class ClientTCPPractice {
    public static void main(String[] args){
        try{
            //request server socket
            Socket server = new Socket("localhost",69);

            //create input and output stream with server socket
            DataOutputStream outToServer = new DataOutputStream(server.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

            //initiate keyboard reader
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                //read from keyboard
                System.out.print("Send to server(\"QUIT\" to exit):");
                String message = br.readLine();


                //send to server
                outToServer.writeBytes(message + "\n");
                outToServer.flush();

                //exit while loop
                if(message.toUpperCase().equals("QUIT")) break;
                //receiver from server
                String fromServer = inFromServer.readLine();
                System.out.println("From server: "+fromServer);
            }
            outToServer.close();
            inFromServer.close();
            br.close();
            server.close();
        }catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }
}
