package tut7;

import java.io.*;
import java.net.Socket;

public class SimpleFileClient {
    File f = null;
    FileInputStream fis = null;
    DataOutputStream dos = null;


    private void transfer(Socket host,String file) throws IOException {
        f = new File(file);
        fis = new FileInputStream(f);
        byte[] buffer = new byte[1024*70];
        dos = new DataOutputStream(host.getOutputStream());
        dos.writeLong(f.length());
        int bytes;
        while((bytes = fis.read(buffer)) != -1){
            dos.write(buffer,0,bytes);
            dos.flush();
        }
        fis.close();
        dos.close();
        host.close();
    }

    public static void main(String[] args) {
        try{
        Socket host = new Socket("localhost",9999);
        SimpleFileClient client = new SimpleFileClient();
        client.transfer(host,"src/tut7/test.jpg");
        }catch(IOException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
