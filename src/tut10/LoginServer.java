package tut10;
// LoginServer.java
// LoginServer uses an SSLServerSocket to demonstrate JSSE's SSL implementation.

// Java core packages
import java.io.*;
import java.math.BigInteger;


// Java extension packages
import javax.net.ssl.*;

public class LoginServer {
    private static final String CORRECT_USER_NAME = "duong";
    private static final String CORRECT_PASSWORD = "123123";
    private SSLServerSocket serverSocket;

    // LoginServer constructor
    public LoginServer() throws Exception
    {
        System.setProperty("javax.net.ssl.keyStore", "./SSLStore");
        System.setProperty("javax.net.ssl.keyStorePassword", "123123");
        // SSLServerSocketFactory for building SSLServerSockets
        SSLServerSocketFactory socketFactory =
                ( SSLServerSocketFactory )
                        SSLServerSocketFactory.getDefault();
        // create SSLServerSocket on specified port
        serverSocket = ( SSLServerSocket )
                socketFactory.createServerSocket( 7070 );

    } // end LoginServer constructor
    // start server and listen for clients
    private void runServer()
    {
        // perpetually listen for client s
        while ( true ) {
            // wait for client connection and check login information
            try {
                System.err.println( "Waiting for connection..." );
                // create new SSLSocket for client
                SSLSocket socket =  ( SSLSocket ) serverSocket.accept();
                // open BufferedReader for reading data from client
                BufferedReader input = new BufferedReader(
                        new InputStreamReader( socket.getInputStream() ) );
                // open PrintWriter for writing data to client
                PrintWriter output = new PrintWriter(
                        new OutputStreamWriter(socket.getOutputStream() ) );
                String userName = input.readLine();
                String password = input.readLine();
                boolean success = false;
                if ( userName.equals( CORRECT_USER_NAME ) &&
                        password.equals( CORRECT_PASSWORD ) ) {
                    output.println( "Login successfully" );
                    output.flush();
                    success = true;
                }
                else {
                    output.println( "Login failed." );
                }
                if(success){
                    while(true){
                        String res = input.readLine();
                        if(res.equals("QUIT")) break;
                        BigInteger inputNumber = new BigInteger(res);
                        output.println(inputNumber.pow(3));
                        output.flush();
                    }

                }
                // clean up streams and SSLSocket
                output.close();
                input.close();
                socket.close();

            } // end try

            // handle exception communicating with client
            catch ( IOException ioException ) {
                ioException.printStackTrace();
            }

        } // end while

    } // end method runServer

    // execute application
    public static void main( String args[] ) throws Exception
    {
        LoginServer server = new LoginServer();
        server.runServer();
    }
} //end LoginServer class
