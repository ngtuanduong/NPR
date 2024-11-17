import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;
public class takeSourceOfWeb {

        public static void main(String[] args) {

            JFrame frame = new JFrame("FORM");
            frame.setSize(1000,500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            URL url = null;
            BufferedReader br = null;
            try{
                url = new URL("https://www.hanu.vn");
            }catch (MalformedURLException me){
                System.out.println("Cannot find webpage:" + url);
            System.exit(-1);
            }

            try{
                URLConnection aConnection = url.openConnection();
                InputStreamReader inputStreamReader = new InputStreamReader(aConnection.getInputStream());
                br = new BufferedReader(inputStreamReader);
            }catch (IOException ioe){
                System.out.println("Cannot connect to webpage:" + url);
                System.exit(-1);
            }

            try{
                String line;

                while((line = br.readLine()) != null){
                    JLabel tmp = new JLabel(line);
                    tmp.setAlignmentX(Component.LEFT_ALIGNMENT);
                    panel.add(tmp);
                    System.out.println(line);
                }
            }catch (IOException ioe){
                System.out.println("Cannot read from webpage:" + url );
            }
            frame.add(scrollPane);
            frame.setVisible(true);
        }

}
