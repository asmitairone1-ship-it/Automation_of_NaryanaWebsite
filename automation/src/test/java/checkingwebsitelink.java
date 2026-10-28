import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class checkingwebsitelink {
    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String[] websites = {
                "https://narayanagroup.com/",
                "https://www.narayanaschools.in/",
                "https://www.narayanajuniorcolleges.com/",
                "https://www.narayanacoachingcenters.in/",
//                "http://aksharainternationalschool.co.in",                  //1
//                "https://www.chaitrapublicschool.com/",               //2
//                "http://narayanacoachingcenters.in/",                //3
//                "http://narayanacollegeofphysiotherapy.com/",        //4
//                "https://www.narayanaetechnoschoolbhopal1.com/",     //5
//                "https://www.narayanaetechnoschoolgwalior.com/",    //6
//                "http://narayanaetechnoschoolindore.com",                  //7
//                "https://narayanaetechnoschoolshivpuri.com/",        //8
//                "http://narayanaetechnoschoolujjjain.in",                  //9
//                "http://narayanagomathyinternationalschool.com/",    //10
//                "http://narayanajuniorcolleges.com",                       //11
//                "http://narayanamedicalcollege.com",                       //12
//                "http://narayananursingcollege.com",                       //13
//                "http://narayananursingjournal.com",                       //14
//                "http://narayanapharmacycollege.com/",             //15
//                "https://www.narayanaschools.in/",                  //16
                "https://www.necg.ac.in",                                     //17 THIS DOUBLE TIME WRITTEN THE SHEET ALSO
                "https://www.necn.ac.in",                                     //18
                "https://www.neetjeenius.com",                                  //19
                "https://nmcmededu.com/",                                    //20
                "https://www.nspira.in",                                        //21
                "https://reachdisha.com/",                                   //22
                "https://www.sdaschool.in",                                     //23
                "https://www.stanfordpublicschool.com",                         //24
                "https://www.narayanacoachingcenters.in/live-classes/",//25
                "https://narayanaschoolchdroad.com/",               //26
                "https://college.narayanagroup.com/",               //27
                "https://gemsakademia.in/landing/",                  //28
                "https://wbnarayanaschools.com/",                   //29
                "https://theoneschool.com/",                        //30
                "https://tedxnarayanaetechno.com/"            //31
//                "https://www.narayanagroup.com" ,                                     //32/
//                "https://www.narayanashikshayatan.com/",          //33
//                "https://valmikitotsnashik.com/",                  //34
//                "https://admissions.theoneschool.com/",           //35
//                "https://gtet.in/",                                 //36
//                "https://www.narayanainternationalschools.com/index.php", //37
//                "http://kennedyhighschool.in",                      //38
//                "https://www.narayanacoachingcenters.in/naits/",    //39
//                "https://www.narayanacoachingcenters.in/NACST/",    //40
//                "https://nsat.narayanagroup.com/",                  //41
//                "https://nsatscore.narayanagroup.com/",             //42
//                "https://recruitment.narayanagroup.com/",           //43
//                "https://prodigy.narayanagroup.com/",               //44
//                "http://narayanaahmedabad-nasat.com/",              //45
//                "https://nsat.narayanagroup.com/west-bengal/"       //46
//
////
        };



        int totalLinksChecked = 0;

        for (String site : websites) {

            System.out.println("\n============================= Checking Website: " + site + " " +
                    "======================================");

            try {
                long startTime = System.currentTimeMillis();
                driver.get(site);
                long endTime = System.currentTimeMillis();

                System.out.println("Page Load Time: " + (endTime - startTime) + " ms");

            } catch (Exception e) {
                System.out.println("Website not opening ❌");
                continue;
            }

            List<WebElement> linkElements = driver.findElements(By.tagName("a"));

            // 🔹 Using Set to remove duplicate links
            Set<String> uniqueLinks = new HashSet<>();

            for (WebElement link : linkElements) {

                String url = link.getAttribute("href");

                if (url == null || url.isEmpty() || url.startsWith("javascript")) {
                    continue;
                }

                uniqueLinks.add(url);
            }

            // 🔁 Check only unique links
            for (String linkUrl : uniqueLinks) {

                totalLinksChecked++;

                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(linkUrl).openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.connect();

                    int statusCode = conn.getResponseCode();

                    if (statusCode >= 400) {
                        System.out.println(linkUrl + " --> Broken Link ❌ (" + statusCode + ")");
                    } else {
                        System.out.println(linkUrl + " --> Valid Link ✅ (" + statusCode + ")");
                    }

                    conn.disconnect();

                } catch (Exception e) {
                    System.out.println(linkUrl + " --> Exception ❗");
                }
            }
        }

        System.out.println("\nTotal Unique Links Checked = " + totalLinksChecked);

        driver.quit();
    }

}
