package tut10;

// LoginClient.java
// LoginClient uses an SSLSocket to transmit fake login information to LoginServer.
// Java core packages
import java.io.*;
// Java extension packages
import javax.swing.*;
import javax.net.ssl.*;

public class LoginClient {
    // LoginClient constructor
    public LoginClient()
    {
        System.setProperty("javax.net.ssl.trustStore","./SSLStore");
        System.setProperty("javax.net.ssl.trustStorePassword", "123123");
        // open SSLSocket connection to server and send login
        try {
            // obtain SSLSocketFactory for creating SSLSockets
            SSLSocketFactory socketFactory =  ( SSLSocketFactory ) SSLSocketFactory.getDefault();
            // create SSLSocket from factory
            SSLSocket socket =   ( SSLSocket ) socketFactory.createSocket(  "localhost", 7070 );
            // create PrintWriter for sending login to server
            PrintWriter output = new PrintWriter(
                    new OutputStreamWriter( socket.getOutputStream() ) );
            // prompt user for user name
            String userName = JOptionPane.showInputDialog( null, "Enter User Name:" );
            // send user name to server
            output.println( userName );
            // prompt user for password
            String password = JOptionPane.showInputDialog( null, "Enter Password:" );
            // send password to server
            output.println( password );
            output.flush();
            // create BufferedReader for reading server response
            BufferedReader input = new BufferedReader(
                    new InputStreamReader( socket.getInputStream () ) );
            // read response from server
            String response = input.readLine();
            System.out.println(response);

            // display response to user
            JOptionPane.showMessageDialog( null, response );
            // clean up streams and SSLSocket
            if(response.equals("Login successfully")){
                String inputNumber = JOptionPane.showInputDialog( null, "Enter a number:" );
                while(true) {
                    try{
                        if(inputNumber.toUpperCase().equals("QUIT")) {
                            output.println("QUIT");
                            break;
                        }
                        Double.parseDouble(inputNumber);
                        output.println( inputNumber );
                        output.flush();
                        response = input.readLine();
                        JOptionPane.showMessageDialog(null, response);
                        inputNumber = JOptionPane.showInputDialog(null, "Enter a number:");
                    }catch(NumberFormatException e){
                        JOptionPane.showMessageDialog( null, "Please enter a number." );
                        inputNumber = JOptionPane.showInputDialog( null, "Enter a number:" );
                    }
                }
                JOptionPane.showMessageDialog( null, "Goodbye" );
            }
            output.close();
            input.close();
            socket.close();
        } // end try
        // handle exception communicating with server
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        }
        // exit application
        finally {
            System.exit( 0 );
        }
    } // end LoginClient constructor
    // execute application
    public static void main( String args[] )
    {
        new LoginClient();
    }
}

