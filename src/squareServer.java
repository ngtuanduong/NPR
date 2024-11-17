import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;

public class squareServer {
    public static void main(String argv[]) throws Exception
    {
        String sentence_from_client;
        String number_to_client;

        //Tạo socket server, chờ tại cổng '6543'
        ServerSocket welcomeSocket = new ServerSocket(6543);

        while(true) {
            //chờ yêu cầu từ client
            Socket connectionSocket = welcomeSocket.accept();

            //Tạo input stream, nối tới Socket
            BufferedReader inFromClient =
                    new BufferedReader(new
                            InputStreamReader(connectionSocket.getInputStream(), StandardCharsets.UTF_8));

            //Tạo outputStream, nối tới socket
            BufferedWriter outToClient = new BufferedWriter(new OutputStreamWriter(connectionSocket.getOutputStream(),StandardCharsets.UTF_8));

            //Đọc thông tin từ socket
            sentence_from_client = inFromClient.readLine();
            //Chuyển string sang dạng bigInteger
            BigInteger number_from_client = new BigInteger(sentence_from_client);
            //Tính bình phương
            number_from_client = number_from_client.pow(2);
            number_to_client =  number_from_client +" (Server accepted!)" + '\n';
            //ghi dữ liệu ra socket
            //Truyền dữ liệu tới client
            outToClient.write(number_to_client);
            outToClient.newLine();
            outToClient.flush();


        }

    }
}