package tut6;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class sajdla {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter an IP address for the host, or \"localhost\": ");
        String host = "10.10.26.205";
        Socket socketOfClient = null;
        BufferedReader is = null;
        BufferedWriter os = null;

        try{
            socketOfClient = new Socket(host, 9999);
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Host not found!");
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to host: " + host);
            return;
        }
        String num = null;
        while(true){
            System.out.print("Please enter an integer to square or \"exit\" to exit:  ");
            num = sc.nextLine();
            os.write(num);
            os.newLine();
            os.flush();
            String responseLine;
            responseLine = is.readLine();
            System.out.println("The server responded with: " + responseLine);
            if(num.equals("exit")){
                System.out.println("Exiting!");
                break;
            }
        }
    }
}