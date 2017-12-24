import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;

public class LoginInterface extends JFrame {
    String userName;
    String userPassword;
    static String passingUserName;
    static JLabel wrongInfo;

    public LoginInterface(){
         this.setSize(400,400);
         this.setResizable(false);
         this.setLocationRelativeTo(null);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         JPanel myPanel = new JPanel();
         myPanel.setLayout(null);
         wrongInfo = new JLabel("Wrong username or password!");
         wrongInfo.setBounds(150,300,200,30);
         JTextField usernameText = new JTextField("Username");
         usernameText.setBounds(10,10,150,30);
         JPasswordField userPasswordText = new JPasswordField("Password");
         userPasswordText.setBounds(10,45,150,30);
         JButton loginButton = new JButton("Login");
         loginButton.setBounds(170,10,100,30);
         JButton signUpButton = new JButton("Sign Up");
         signUpButton.setBounds(170,45,100,30);
         signUpButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 signUpFrame signUp = new signUpFrame();
             }
         });
         loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                //login check if the username and password is valid. Than create Frame.
                 databaseClass validationDB = new databaseClass();
                 validationDB.getUserData();
                 if(validationDB.validateUsername_Password(usernameText.getText(),userPasswordText.getText())){
                     passingUserName = usernameText.getText();
                     System.out.println("Logining...");
                     setVisible(false);
                     mainFrame loginFrame = new mainFrame();

                 }
                 else {
                     System.out.println("Login failed in login screen");
                     myPanel.add(wrongInfo);
                     myPanel.validate();
                     myPanel.repaint();
                 }
             }
         });

         myPanel.add(usernameText);
         myPanel.add(userPasswordText);
         myPanel.add(loginButton);
         myPanel.add(signUpButton);
         this.add(myPanel);
         this.setVisible(true);
    }
}

    class searchBookFrameDialog {
    private static JDialog d;
    BufferedImage dialogImage;
    searchBookFrameDialog() {
        JFrame f = new JFrame();
        d = new JDialog(f, "NO BOOK", true);
        f.setLocationRelativeTo(null);
        JButton b = new JButton();
        String src = "http://www.pptbackgrounds.org/thumb/flowers-and-vintage-blank-books-powerpoint-templates.jpg";
        try{
            URL imgUrl = new URL(src);
            dialogImage = ImageIO.read(imgUrl);

        }
        catch(Exception E){
            System.out.println("Exception in dialog." + E);
        }

        b.setIcon(new ImageIcon(dialogImage));
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchBookFrameDialog.d.setVisible(false);
            }
        });
        d.add(new JLabel("NO SUCH BOOK IN DATABASE."));
        d.add(b);
        d.setSize(250, 250);
        d.setVisible(true);
    }
}

    class DialogExample {
        private static JDialog d;

        DialogExample(String name) {
            JFrame f = new JFrame();
            d = new JDialog(f, "Login", true);
            f.setLocationRelativeTo(null);
            JButton b = new JButton(name);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DialogExample.d.setVisible(false);
                }
            });

            d.add(b);
            d.setSize(150, 150);
            d.setVisible(true);
        }
    }

class BookAditionDialog {
    private static JDialog d;

    BookAditionDialog() {
        JFrame f = new JFrame();
        d = new JDialog(f, "BookInserted", true);
        f.setLocationRelativeTo(null);
        JButton b = new JButton("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookAditionDialog.d.setVisible(false);
            }
        });
        d.add(new JLabel("Succesfully Inserted."));
        d.add(b);
        d.setSize(150, 150);
        d.setVisible(true);
    }
}

    class signUpFrame extends JFrame {
            String userEmail;
            String userName;
            String userSurname;
            String userPassword;
            static JLabel failInsert;
            public signUpFrame() {
                databaseClass registerDB = new databaseClass();
                this.setSize(400, 400);
                this.setLocationRelativeTo(null);

                JPanel signupPanel = new JPanel();

                JButton registerButton = new JButton("Register");
                JTextField nameTextField = new JTextField("Name");
                JTextField surnameTextField = new JTextField("Surname");
                JTextField passwordTextField = new JTextField("Password");
                JTextField emailTextField = new JTextField("Email");
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        registerDB.insertUserData(nameTextField.getText(), surnameTextField.getText(), emailTextField.getText(), passwordTextField.getText());
                        //DialogExample D = new DialogExample();
                        //supposed to close signup frame too.
                    }
                });

                signupPanel.add(registerButton);
                signupPanel.add(nameTextField);
                signupPanel.add(surnameTextField);
                signupPanel.add(passwordTextField);
                signupPanel.add(emailTextField);
                this.add(signupPanel);
                this.setVisible(true);
            }
        }

    class mainFrame extends JFrame{
        mainFrame(){
            this.setSize(384,288);
            this.setLocationRelativeTo(null);
            JPanel mainFramePanel = new JPanel();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JButton myBookButton = new JButton("MyBooks");
            JButton searchBook = new JButton("SearchBook");
            myBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    myBooksFrame newMyBooksFrame = new myBooksFrame();

                }
            });
            searchBook.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //Initiliaze search Frame
                    searchBookFrame newFrame = new searchBookFrame();
                }
            });
            mainFramePanel.add(myBookButton);
            mainFramePanel.add(searchBook);
            this.add(mainFramePanel);
            this.setVisible(true);
        }
    }

    class searchBookFrame extends JFrame{
    User bookOwner;
    BufferedImage image;
    static URL url;
    JLabel bookPicLabel;

    databaseClass searchBookDB;
    Book searchBook;
    String bookQuery;

    URL getUrlMethod(String myUrl) throws IOException {
            System.out.println("SCREEN: " + myUrl);
            int counter = 0;
            Document doc = Jsoup.connect(myUrl).get();
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
            return url;
        }

        String displayBook(String bookName){
            bookQuery = bookName;
            bookQuery = bookQuery.replaceAll("\\s+","%20");
            bookQuery = "http://www.dr.com.tr/search?q=" + bookQuery;
            System.out.println(bookQuery);
            return bookQuery;
        }

    searchBookFrame(){
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        JPanel searchBookPanel = new JPanel();
        searchBookPanel.setLayout(null);
        searchBookDB = new databaseClass();
        searchBook = new Book();

        bookPicLabel = new JLabel();
        JLabel bookName = new JLabel("BookName:");
        bookName.setBounds(50,130,100,30);

        JLabel book = new JLabel();
        book.setBounds(130,130,140,30);

        JLabel ownedBy = new JLabel("OwnedBy:");
        ownedBy.setBounds(50,170,100,30);

        JLabel owned = new JLabel("");
        owned.setBounds(130,170,100,30);

        JLabel available = new JLabel();
        available.setBounds(50, 210,250,30);

        bookOwner = new User();

        JButton searchBookButton = new JButton("<html><center>"+"Search"+"<br>"+"Book"+"</center></html>");
        searchBookButton.setBounds(160,20,100,50);

        JButton sendEmail = new JButton("<html><center>"+"Send"+"<br>"+ "Request"+"</center></html>");
        JTextField searchBookText = new JTextField("Search Book");

        searchBookText.setBounds(50,20,100,30);
        //SEARCH BUTTON ACTION LISTENER.
        searchBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (searchBookDB.checkBookInDatabase(searchBookText.getText())){
                    case 1:
                        //In database but RENTED.
                        System.out.println("Case 1");
                        searchBook = searchBookDB.getBook(searchBookText.getText());
                        book.setText(searchBook.getBookName());
                        owned.setText(searchBook.getOwnedBy());
                        bookOwner = searchBookDB.getUser(searchBook.getOwnedBy());
                            available.setText(searchBook.getBookName() + " is not available for rent .");
                        try{
                            image=ImageIO.read(getUrlMethod(displayBook(searchBook.getBookName())));
                        }
                        catch(Exception E){
                            System.out.println("Error..." + E);
                        }
                        bookPicLabel.setIcon(new ImageIcon(image));
                        bookPicLabel.setBounds(270,30,150,150);
                        sendEmail.setVisible(false);
                        searchBookPanel.add(bookPicLabel);
                        searchBookPanel.validate();
                        searchBookPanel.repaint();
                        break;
                    case 0:
                        //In database but NOT rented.
                        searchBook = searchBookDB.getBook(searchBookText.getText());
                        searchBook = searchBookDB.getBook(searchBookText.getText());
                        book.setText(searchBook.getBookName());
                        owned.setText(searchBook.getOwnedBy());
                        bookOwner = searchBookDB.getUser(searchBook.getOwnedBy());
                        if(searchBook.getIsRented().equals("false")) {
                            available.setText("<html>"+searchBook.getBookName() + " is available for rent <br> from " + searchBook.getOwnedBy() + "</html>");
                            sendEmail.setBounds(150, 260, 100, 50);
                            sendEmail.setVisible(true);

                            try {
                                image = ImageIO.read(getUrlMethod(displayBook(searchBook.getBookName())));
                                bookPicLabel.setIcon(new ImageIcon(image));
                                bookPicLabel.setBounds(270,30,150,150);
                                searchBookPanel.add(bookPicLabel);
                                searchBookPanel.validate();
                                searchBookPanel.repaint();

                            } catch (Exception E) {
                                System.out.println("Error..." + E);
                            }
                        }
                        break;
                    case -1:
                        //error. Not in Databese.
                        searchBookFrameDialog newDialog = new searchBookFrameDialog();
                        break;
                    default:
                        System.out.println("DEFAO");
                        break;
                }

            }
        });
        sendEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SEND EMAIL FROM USER.
                //CREATE NEW CLASS WITH JAVAEMAILAPK. YAPARSAN BAYA GUZEL OLUR
                User reciever = searchBookDB.getUser(LoginInterface.passingUserName);
                EmailController sendEmail = new EmailController();
                try{
                    sendEmail.generateAndSendEmail(bookOwner.getUserEmail(),searchBook.getBookName(),reciever.getUserName());
                    System.out.println("Email Has sent.");
                    searchBookDB.updateBookData(searchBook.getBookName(),reciever.getUserName());                }
                catch(Exception E){
                    System.out.println("Exception while sending Email" + E);
                }

            }
        });
        searchBookPanel.add(sendEmail);
        searchBookPanel.add(searchBookButton);
        searchBookPanel.add(searchBookText);
        searchBookPanel.add(owned);
        searchBookPanel.add(ownedBy);
        searchBookPanel.add(available);
        searchBookPanel.add(book);
        searchBookPanel.add(bookName);
        //searchBookPanel.updateUI();
        this.add(searchBookPanel);
        this.setVisible(true);
    }
    }


    class myBooksFrame extends JFrame{
        BufferedImage image;
        static URL url;
        //static String bookQuery;
        boolean flag;
        JLabel picLabel;
        JLabel rentedPicLabel;

        void getUrlMethod(String myUrl) throws IOException {
            System.out.println("SCREEN: " + myUrl);
            int counter = 0;
            Document doc = Jsoup.connect(myUrl).get();
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

        String displayBook(String bookName){
            String bookQuery = bookName;
            bookQuery = bookQuery.replaceAll("\\s+","%20");
            bookQuery = "http://www.dr.com.tr/search?q=" + bookQuery;
            System.out.println(bookQuery);
            return bookQuery;
        }

        myBooksFrame(){
            this.setSize(640,480);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            JPanel myBooks = new JPanel();
            myBooks.setLayout(null);
            JList ownedBookList = new JList();
            ownedBookList.setBounds(400,50,100,300);
            JList rentedBookList = new JList();
            rentedBookList.setBounds(510,50,100,300);
            //Book informations.
            JLabel bookName = new JLabel("BookName: ");
            bookName.setBounds(70,20,70,30);
            JLabel ownedBy = new JLabel ("Owned By: ");
            ownedBy.setBounds(70,50,70,30);
            JLabel rentedBy = new JLabel ("Rented By: ");
            rentedBy.setBounds(70,80,70,30);
            JLabel isRented = new JLabel("IsRented: ");
            isRented.setBounds(70,110,70,30);
            JLabel selectedBookName = new JLabel("NA");
            selectedBookName.setBounds(170,20,100,30);
            JLabel selectedBookOwnedBy = new JLabel("NA");
            selectedBookOwnedBy.setBounds(170,50,100,30);
            JLabel selectedBookRentedBy = new JLabel ("NA");
            selectedBookRentedBy.setBounds(170,80,100,30);
            JLabel selectedBookIsRented = new JLabel("NA");
            selectedBookIsRented.setBounds(170,110,30,30);
            databaseClass bookDB = new databaseClass();
            JButton addBookButton = new JButton("AddNewBook");
            addBookButton.setBounds(70,140,150,30);
            JTextField newBookText = new JTextField("NewBook");
            newBookText.setBounds(220,140,150,30);
            System.out.println("BOOKFRAMECALISIYOR");
            addBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Inserting Book");
                    System.out.println("NEWBOOKTEXT: " + newBookText.getText());
                    bookDB.insertBookData(newBookText.getText(),LoginInterface.passingUserName,"NA","false");
                    BookAditionDialog insertion =  new BookAditionDialog();
                    ownedBookList.validate();
                    ownedBookList.repaint();
                }
            });
            //List Selection...
            //TO BE ABLE TO DRAW THE IMAGE I HAD TO CREATE JLABEL TO STORE THE IMAGE VIA IMAGEICON. Than i updated the panel.
            picLabel = new JLabel(new ImageIcon());
            //rentedPicLabel = new JLabel(new ImageIcon());
            rentedBookList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()){
                        Book resultBook = bookDB.getBook(rentedBookList.getSelectedValue().toString());
                        selectedBookName.setText(resultBook.getBookName());
                        selectedBookOwnedBy.setText(resultBook.getOwnedBy());
                        selectedBookRentedBy.setText(resultBook.getRentedBy());
                        selectedBookIsRented.setText(resultBook.getIsRented());

                        try{
                            getUrlMethod(displayBook(rentedBookList.getSelectedValue().toString()));
                            image = ImageIO.read(url);
                            ImageIcon newIcon = new ImageIcon(image);
                            picLabel.setIcon(newIcon);
                            picLabel.setBounds(80,150,150,300);
                            myBooks.add(picLabel);
                            myBooks.validate();
                            myBooks.repaint();


                        }
                        catch (IOException E){
                            System.out.println("Exception");
                        }
                    }
                }
            });
            DefaultListModel <String> DLM_owned =  new DefaultListModel();
            //ADDING BOOK TO THE DLM_OWNED + BookList.
            System.out.println("Username ? : " + LoginInterface.passingUserName);
            bookDB.transferBookData(LoginInterface.passingUserName);
            Iterator<Book> bookIterator = bookDB.myBookList.iterator();
            while(bookIterator.hasNext()){
                DLM_owned.addElement(bookIterator.next().getBookName());

            }
            ownedBookList.updateUI();
            //Rented DefaultListModel.
            ownedBookList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if(!e.getValueIsAdjusting()){
                        Book resultBook = bookDB.getBook(ownedBookList.getSelectedValue().toString());
                        selectedBookName.setText(resultBook.getBookName());
                        selectedBookOwnedBy.setText(resultBook.getOwnedBy());
                        selectedBookRentedBy.setText(resultBook.getRentedBy());
                        selectedBookIsRented.setText(resultBook.getIsRented());
                        try{
                            getUrlMethod(displayBook(ownedBookList.getSelectedValue().toString()));
                            image = ImageIO.read(url);
                            ImageIcon newIcon = new ImageIcon(image);
                            picLabel.setIcon(newIcon);
                            picLabel.setBounds(80,150,150,300);
                            myBooks.add(picLabel);
                            myBooks.validate();
                            myBooks.repaint();


                        }
                        catch (IOException E){
                            System.out.println("Exception");
                        }
                    }
                }
            });

            JLabel ownedBookLabel = new JLabel("Owned Books");
            JLabel rentedBookLabel = new JLabel("Rented Books");
            ownedBookLabel.setBounds(400,20,100,20);
            rentedBookLabel.setBounds(510,20,100,20);
            //Owned DefaultListModel.

            DefaultListModel<String> DLM_rented = new DefaultListModel();
            databaseClass rentedBookDB = new databaseClass();
            rentedBookDB.transferRentedBook(LoginInterface.passingUserName);
            Iterator<Book> rentedBookIterator = rentedBookDB.myBookList.iterator();
            while(rentedBookIterator.hasNext()){
                DLM_rented.addElement(rentedBookIterator.next().getBookName());
            }


            rentedBookList.setModel(DLM_rented);
            rentedBookList.updateUI();
            ownedBookList.setModel(DLM_owned);
            myBooks.add(rentedBookLabel);
            myBooks.add(selectedBookName);
            myBooks.add(selectedBookOwnedBy);
            myBooks.add(selectedBookRentedBy);
            myBooks.add(selectedBookIsRented);
            myBooks.add(ownedBookLabel);
            myBooks.add(addBookButton);
            myBooks.add(newBookText);
            myBooks.add(bookName);
            myBooks.add(rentedBy);
            myBooks.add(ownedBy);
            myBooks.add(isRented);
            myBooks.add(ownedBookList);
            myBooks.add(rentedBookList);
            myBooks.validate();
            myBooks.repaint();
            this.add(myBooks);
            this.setVisible(true);
        }
    }
