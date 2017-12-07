import oracle.jrockit.jfr.JFR;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


//import javax.lang.model.util.Elements;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Test {
    public static void main(String[] args) throws IOException {
        Frame myFrame = new Frame();
        GUI myGUI = new GUI();
        myFrame.add(myGUI);
    }
}

    class GUI extends JPanel{
        JTextField queryTextField = new JTextField("Query");
        JButton queryButton = new JButton("Send");
        static String query;
        String parsedQuery;
        public GUI(){
            queryTextField.setBounds(0,10,200,200);
            queryButton.setBounds(0,0,20,20);
            queryButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    query = queryTextField.getText();
                    parsedQuery = query;
                    parsedQuery = parsedQuery.replaceAll("\\s+","%20");

                    System.out.println("TEST REGULAR: "+ query);
                    System.out.println("TEST: " + parsedQuery);
                    query = "http://www.dr.com.tr/search?q=" + parsedQuery;
                    System.out.println("LAST TEST : "+ query);

                    Screen newScreen = new Screen();
                    JFrame newWindow = new JFrame();
                    newWindow.add(newScreen);
                    newWindow.setSize(400,400);
                    newWindow.setVisible(true);


                }
            });
            this.add(queryButton);
            this.add(queryTextField);
        }

    }

   class Screen extends JPanel{
        BufferedImage image;
        static URL url;

        void getUrlMethod() throws IOException{
            System.out.println("SCREEN: " + GUI.query);
            int counter = 0;
            Document doc = Jsoup.connect(GUI.query).get();
            Elements img = doc.getElementsByTag("img");
            //Elements img = doc.getElementsByTag("img");
            for (Element el : img) {

                String src = el.absUrl("src");
                counter++;

                if (counter == 2) {
                    url = new URL(src);
                    break;
                }
            }
            System.out.println("URL : " + url);
        }

        public Screen(){
            try{
                getUrlMethod();
                image = ImageIO.read(url);
            }
            catch (IOException E){
                System.out.println("Exception");
            }
            repaint();
        }
        public void paint(Graphics g){
            g.drawImage(image,20,20,null);
        }
    }

   class Frame extends JFrame{
        Frame(){
            this.setSize(600,450);

            GUI myGui = new GUI();
            this.add(myGui);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setVisible(true);
        }
    }





