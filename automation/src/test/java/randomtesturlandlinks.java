import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class randomtesturlandlinks {

    public static void main(String[] args) {   // <-- MAIN METHOD

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.narayanaschools.in/andhra-pradesh/vishakapatnam-kommadi" );
        System.out.println("Website Title: " + driver.getTitle());

        // Get all links
        List<WebElement> alllinks = driver.findElements(By.tagName("a"));
        System.out.println("Total Links Found: " + alllinks.size());

        // Loop each link
        for (WebElement link : alllinks) {

            String url = link.getAttribute("href");

            if (url == null || url.isEmpty() || url.startsWith("javascript")) {
                continue;
            }

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("HEAD");
                conn.connect();

                int statusCode = conn.getResponseCode();

                if (statusCode >= 400) {
                    System.out.println(url + " --> Broken Link ❌ (" + statusCode + ")");
                } else {
                    System.out.println(url + " --> Valid Link ✅ (" + statusCode + ")");
                }

            } catch (Exception e) {
                System.out.println(url + " --> Exception ❗");
            }
        }

        driver.quit();  // <-- Close the browser

    }  // <-- END OF MAIN METHOD

}  // <-- END OF CLASS