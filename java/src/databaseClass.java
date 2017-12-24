import javax.swing.*;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class databaseClass {
    //JDBC DRIVER NAME AND DATABASE URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/javaproje";

    //Database credentials
    static final String USER = "root";
    static final String PASS = "";

    Book transfer;
    ArrayList <Book> myBookList;


    Connection conn = null;
    String [][] data = null;

    void updateBookData(String bookName,String userName){
        try{
            Statement stmt;
            System.out.println("BookUpdate");
            String sql = "UPDATE `books` SET  `rentedby`="+"'"+userName+"',"+"`isrelated`="+"'true'"+" WHERE 1 AND `bookName`=" + "'" + bookName + "'";
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }
        catch(Exception E){
            System.out.println("Update Error: " + E);
        }
    }

    void insertUserData(String name, String surname, String email, String password){
        try{
            Statement stmt;
            System.out.println("insertUserData");
            String sql = "INSERT INTO `userinformation`(`username`, `usersurname`, `useremail`,`userpassword`) VALUES (" + "'" + name + "'" + "," + "'"+ surname +"'" + "," +"'"+ email +"'"+ "," +"'"+ password+"'" + ")";
            System.out.println("SQL STATMENT::" + sql);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.execute(sql);
            DialogExample success = new DialogExample("<html>Successfully <br>registered!</html>");
        }
            catch (Exception E){
                System.out.println("Insert Error: "+ E);
                DialogExample fail = new DialogExample("<html>Username has <br>already taken!</html>");

            }
    }

    void getUserData(){
        try{
            ResultSet rs;
            Statement stmt;
            System.out.println("ingetData");
            String sql = "SELECT * FROM `userinformation` WHERE 1";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name  = rs.getString("username");
                String surname = rs.getString("usersurname");
                String email = rs.getString("useremail");
                System.out.println("Name : " + name + "Surname: " + surname + "Email: " + email);
            }
        }
        catch(Exception ex){
            System.out.println("Erro:  " + ex);
        }
    }

    User getUser(String userName){
        try{
            ResultSet rs;
            Statement stmt;
            System.out.println("ingetData");
            String sql = "SELECT * FROM `userinformation` WHERE 1 AND username = "+ "'"+userName+"'";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String name  = rs.getString("username");
                String surname = rs.getString("usersurname");
                String email = rs.getString("useremail");
                User newUser = new User();
                newUser.setUserName(name);
                newUser.setUserSurname(surname);
                newUser.setUserEmail(email);
                return newUser;
            }
        }
        catch(Exception ex){
            System.out.println("Erro:  " + ex);
        }
        return new User();
    }

    boolean validateUsername_Password(String name,String password) {
        try {
            ResultSet rs;
            Statement stmt;
            System.out.println("Validation..");
            String sql = "SELECT `username`,`userpassword` FROM `userinformation` WHERE 1";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String myName = rs.getString("username");
                System.out.println(myName);
                if (name.equals(rs.getString("username")) && password.equals(rs.getString("userpassword"))) {
                    System.out.println("Correct Validation");
                    return true;
                }
            }
            System.out.println("No match for id.");
            return false;
        } catch (Exception E) {
            System.out.println("Erro in validation:" + E);
            signUpFrame.failInsert = new JLabel("Already taken user name");
        }
        return false;
    }
    void insertBookData(String name, String ownedby, String rentedby, String isrelated){
        try{
            Statement stmt;
            System.out.println("insertUserData");
            String sql = "INSERT INTO `books`(`bookName`, `ownedby`, `rentedby`, `isrelated`) VALUES (" + "'" + name + "'" + "," + "'"+ ownedby +"'" + "," +"'"+ rentedby +"'"+ "," +"'"+ isrelated+"'" + ")";
            System.out.println("SQL STATMENT::" + sql);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("INSERTED");

        }
        catch (Exception E){
            System.out.println("Insert Error: "+ E);


        }
    }

    void transferBookData(String username){
        try{
            myBookList = new ArrayList<Book>();
            //I WANT TO USE ITERATOR...
            System.out.println("GG");
            ResultSet rs;
            Statement stmt;
            System.out.println("transferBookData");
            String sql = "SELECT * FROM `books` WHERE 1 AND ownedby ='" + username + "'";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("CALISIYOM");
                String name  = rs.getString("bookName");
                String ownedby = rs.getString("ownedby");
                String rentedby = rs.getString("rentedby");
                String isRented = rs.getString("isrelated");
                transfer = new Book();
                transfer.setBookName(name);
                transfer.setOwnedBy(ownedby);
                transfer.setRentedBy(rentedby);
                transfer.setIsRented(isRented);
                myBookList.add(transfer);
            }
        }
        catch(Exception ex){
            System.out.println("Erro:  " + ex);
        }
    }
    Book getBook(String bookName){
        try{
            //I WANT TO USE ITERATOR...
            System.out.println("GG");
            ResultSet rs;
            Statement stmt;
            System.out.println("transferBookData");
            String sql = "SELECT * FROM `books` WHERE 1 AND bookName ='" + bookName + "'";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("CALISIYOM");
                String name  = rs.getString("bookName");
                String ownedby = rs.getString("ownedby");
                String rentedby = rs.getString("rentedby");
                String isRented = rs.getString("isrelated");
                transfer = new Book();
                transfer.setBookName(name);
                transfer.setOwnedBy(ownedby);
                transfer.setRentedBy(rentedby);
                transfer.setIsRented(isRented);
                return transfer;
            }
        }
        catch(Exception ex){

            System.out.println("Erro:  " + ex);
        }
        return transfer;
    }
    void transferRentedBook(String username){
        try{
            myBookList = new ArrayList<Book>();
            //I WANT TO USE ITERATOR...
            System.out.println("GG");
            ResultSet rs;
            Statement stmt;
            System.out.println("transferBookData");
            String sql = "SELECT * FROM `books` WHERE 1 AND rentedby ='" + username + "'";
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("CALISIYOM");
                String name  = rs.getString("bookName");
                String ownedby = rs.getString("ownedby");
                String rentedby = rs.getString("rentedby");
                String isRented = rs.getString("isrelated");
                transfer = new Book();
                transfer.setBookName(name);
                transfer.setOwnedBy(ownedby);
                transfer.setRentedBy(rentedby);
                transfer.setIsRented(isRented);
                myBookList.add(transfer);
            }
        }
        catch(Exception ex){
            System.out.println("Erro:  " + ex);
        }
    }

int checkBookInDatabase(String bookName){
    try{
        ResultSet rs;
        Statement stmt;
        String sql = "SELECT * FROM `books` WHERE 1";
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            String name  = rs.getString("bookName");
            String isRented = rs.getString("isrelated");
            System.out.println("Check Book" + name);
            if(bookName.equals(name)){
                System.out.println("IF STATEMENT");
                if(isRented.equals("true") || isRented.equals(1)){
                    return 1;
                }
                else
                    return 0;
            }
        }
        return -1;
    }
    catch(Exception ex){
        System.out.println("Erro:  " + ex);
    }
    return 10;
}
}