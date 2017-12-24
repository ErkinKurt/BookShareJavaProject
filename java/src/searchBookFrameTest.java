import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class searchBookFrameTest {
    @Test
    void getUrlMethod() {
        searchBookFrame testSearch = new searchBookFrame();
        try {
            URL url = new URL("http://i.dr.com.tr/cache/136x136-0/originals/0001716966002-1.jpg");
            assertEquals(url,testSearch.getUrlMethod(testSearch.displayBook("Sol ayagim")));
        }
        catch (Exception E){
            System.out.println("Exception " + E);
        }
    }

    @Test
    void displayBook() {
        searchBookFrame testSearch = new searchBookFrame();
        testSearch.displayBook("Kurtlar Sofrasi");
        assertEquals("http://www.dr.com.tr/search?q=Kurtlar%20Sofrasi",testSearch.bookQuery);
    }

}