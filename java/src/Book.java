import java.util.ArrayList;

public class Book {
    private String bookName;
    private String ownedBy;
    private String rentedBy;
    private String isRented;

    //GETSET
    void setBookName (String bookName){
        this.bookName = bookName;
    }
    void setOwnedBy (String ownedBy){
        this.ownedBy = ownedBy;
    }
    void setRentedBy (String rentedBy){
        this.rentedBy = rentedBy;
    }
    void setIsRented (String isRented){
        this.isRented = isRented;
    }
    String getBookName(){
        return this.bookName;
    }
    String getOwnedBy(){
        return this.ownedBy;
    }

    String getRentedBy(){
        return this.rentedBy;
    }

    String getIsRented(){
        return this.isRented;
    }


    Book(){
        bookName = "NA";
        ownedBy = "NA";
        rentedBy = "NA";
        isRented = "NA";
    }
    Book(String bookName, String ownedBy,String rentedBy,String isRented){
        this.bookName = bookName;
        this.ownedBy = ownedBy;
        this.rentedBy = rentedBy;
        this.isRented = isRented;
    }
}

class User {
    private String userName;
    private String userSurname;
    private String userPassword;
    private String userEmail;
    //GETTERANDSETTER
    void setUserName(String userName){
        this.userName = userName;
    }
    void setUserSurname(String userSurnameName){
        this.userSurname = userSurnameName;
    }
    void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }
    String getUserName(){
        return this.userName;
    }
    String getUserSurname(){
        return this.userSurname;
    }
    String getUserPassword(){
        return this.userPassword;
    }
    String getUserEmail(){
        return this.userEmail;
    }
    User(){
        userName = "NA";
        userSurname = "NA";
        userPassword = "NA";
        userEmail = "NA";
    }
    User(String userName,String userSurname,String userPassword,String userEmail){
        this.userName = userName;
        this.userSurname = userSurname;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}
