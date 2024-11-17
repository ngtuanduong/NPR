package Socket;

import java.net.*;

public class InetAddressTest {
    public static void main(String[] args){
        try{
            InetAddress a = InetAddress.getByName("localhost");
            System.out.println(a.getHostAddress());
        }catch(UnknownHostException e){
            System.out.print(e);
        }
    }
}
