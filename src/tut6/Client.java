package tut6;

import java.io.*;
import java.net.Socket;


public class Client {
     Socket socketOfClient = null;
     BufferedReader is = null;
     BufferedWriter os = null;
     BufferedReader inputFromKeyboard = null;

    public Client(String host,int port){
        try{
            socketOfClient = new Socket(host,port);
            is = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));
            os = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));

            inputFromKeyboard = new BufferedReader(new InputStreamReader(System.in));
//            new Thread(new IncomingMessagesHandler()).start();//handle incoming message
//            sentName();
            String sendMessage ;
            while((sendMessage = inputFromKeyboard.readLine()) != null){
                if(sendMessage == "QUIT"){
                    break;
                }
                os.write(sendMessage);
                os.newLine();
                os.flush();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

//    private void sentName() throws IOException {
//        System.out.print("Enter your name: ");
//        os.write(consoleReader.readLine());
//        os.newLine();
//        os.flush();
//    }

    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;
        new Client(host,port);
    }
//    private class IncomingMessagesHandler implements Runnable {
//        @Override
//        public void run() {
//            try {
//                String name;
//                String message;
//                while (true) {
//                    name = is.readLine();
//                    if(name.isEmpty()){
//                        break;
//                    }
//                    message = is.readLine();
//                    System.out.println(name + ": " + message);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
