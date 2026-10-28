import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class test {




        public static void main(String[] args) {

            WebDriver driver = null;

            // =========================
            // URL LIST
            // =========================
            List<String> urls = Arrays.asList(
                    "https://storage.googleapis.com/file-narayanaschools-new/nirbhay-yadav-1664536192-1391378469.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/piyush-dagar-1664536215-1265527238.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/prerna-adhana-1664536239-1944895512.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/ridam-1664536263-811927129.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Rishita-jaitley-1664536284-821913542.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Rishita-thakran-1664536352-298122668.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Samira-mann-1664536377-1556333875.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/shivam-ambawata-1664536399-152406194.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/tanushree-1664536419-791903823.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/1-1664537046-335566684.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/2-1664537046-769019621.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/3-1664537047-1638111064.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/4-1664537047-1590036117.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/5-1664537047-2060880234.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/6-1664537047-781961266.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/7-1664537048-1035481567.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/8-1664537048-92137265.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/10-1664537048-1974568428.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/12-1664537048-455937446.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/14-1664537049-1779298166.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/15-1664537049-956835244.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/16-1664537049-1262591265.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/ADITI-1664537049-517612481.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/5454014_RITIKA-YADAV-1664538003-351865719.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/1848696_VISHAKHA-BHARDWAJ-1664538040-136122705.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/5358111_UJJWAL-LOHIA-1664538067-1594527664.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/5476236_TANYA-PANDEY-1664538092-1252497229.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/5399588_JAISVI-KODANDA-1664538112-1849031756.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/T.C.-DETAILS-1664362302-129534818.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Main_StudentTC_Details202223-1690179229-1486533741.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Main_StudentTC_Details202223-1690536077-1393998203.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/Main_StudentTC_Details202223-1690456629-1072338270.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC_IssueList-1758287258-1544935886.pdf",
                    "https://narayanaschools.in/assets/branches_pdf/TCIssuedDetails.pdf",
                    "https://webassets-gcp.narayanagroup.com/narayana-schools-in/TCs/EKLAVYA-CHATURVEDI-TC.pdf",
                    "https://webassets-gcp.narayanagroup.com/narayana-schools-in/TCs/MANSI-CHATURVEDI-TC.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC-issued-1664353805-1900632290.pdf",
                    "https://s3.ap-south-1.amazonaws.com/file.narayanaschools/IV-DIVYANKSHI-PRADIP-WYAPARI-1665580929-1523979367.pdf",
                    "https://s3.ap-south-1.amazonaws.com/file.narayanaschools/VII-VIVEK-VISHWANATH-PATIL-1665580952-1547174345.pdf",
                    "https://s3.ap-south-1.amazonaws.com/file.narayanaschools/Vedant-LC-Narayana--1665580977-1757970560.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC-DETAILS_THANE-1734010021-491389618.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TCIssuedDetails-1665579247-64033511.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/AVIKA-AGARWAL-1664534446-1735695244.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/EESHA-BAKSHI-1664534481-1680831875.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/HARSHADA-1664534516-283577944.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/ISHLEEN-KAUR-1664534554-207059810.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/PARAMVEER-1664534583-515537794.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/RISHANT-1664534617-697451172.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/SHIVANSH-SINGHAL-1664534645-1368390485.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC_24_25-1756111320-298216904.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC-1664365658-1961876197.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TCS-ISSUE-LIST-1665550848-833904896.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC-DETAILS-1664369585-183490702.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/TC-DETAILS-ADMISSION-1664364880-2063401895.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/K_KARTHIK_NAIK_TC-1689236941-128941188.pdf",
                    "https://storage.googleapis.com/file-narayanaschools-new/B_ABHIRAM_TC-1689236958-993877643.pdf"

            );

            // =========================
            // RESULT LISTS
            // =========================
            List<String> passedUrls = new ArrayList<>();
            List<String> failedUrls = new ArrayList<>();

            try {

                // =========================
                // CHROME OPTIONS
                // =========================
                ChromeOptions options = new ChromeOptions();

                // Optional:
                // options.addArguments("--start-maximized");
                // options.addArguments("--disable-notifications");

                // =========================
                // INITIALIZE DRIVER
                // =========================
                driver = new ChromeDriver(options);

                driver.manage().window().maximize();

                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                JavascriptExecutor js = (JavascriptExecutor) driver;

                System.out.println("=================================================");
                System.out.println("NARAYANA SCHOOL WEBSITE AUTOMATION STARTED");
                System.out.println("TOTAL URLS : " + urls.size());
                System.out.println("=================================================");

                // =========================
                // URL LOOP
                // =========================
                for (int i = 0; i < urls.size(); i++) {

                    String url = urls.get(i);

                    System.out.println();
                    System.out.println("-------------------------------------------------");
                    System.out.println("URL " + (i + 1) + " / " + urls.size());
                    System.out.println("Opening : " + url);
                    System.out.println("-------------------------------------------------");

                    try {

                        // Open URL
                        driver.get(url);

                        System.out.println("Page opened successfully.");

                        // Wait for page to load
                        Thread.sleep(3000);

                        // =========================
                        // SMOOTH SCROLL
                        // =========================
                        smoothScroll(driver);

                        // =========================
                        // WAIT 6 SECONDS
                        // =========================
                        System.out.println("Waiting 6 seconds...");

                        Thread.sleep(6000);

                        // Get page title
                        String title = driver.getTitle();

                        System.out.println("Page Title : " + title);

                        // Get current URL
                        String currentUrl = driver.getCurrentUrl();

                        System.out.println("Current URL : " + currentUrl);

                        passedUrls.add(url);

                        System.out.println("STATUS : PASSED");

                    } catch (TimeoutException e) {

                        System.out.println("STATUS : FAILED - PAGE LOAD TIMEOUT");
                        System.out.println("Error : " + e.getMessage());

                        failedUrls.add(url);

                    } catch (Exception e) {

                        System.out.println("STATUS : FAILED");
                        System.out.println("Error : " + e.getMessage());

                        failedUrls.add(url);
                    }
                }

            } catch (Exception e) {

                System.out.println("Driver initialization error:");
                e.printStackTrace();

            } finally {

                // =========================
                // CLOSE BROWSER
                // =========================
                if (driver != null) {

                    System.out.println();
                    System.out.println("Closing browser...");

                    driver.quit();
                }
            }

            // =========================
            // FINAL REPORT
            // =========================
            System.out.println();
            System.out.println("=================================================");
            System.out.println("AUTOMATION EXECUTION COMPLETED");
            System.out.println("=================================================");

            System.out.println("Total URLs  : " + urls.size());
            System.out.println("Passed URLs : " + passedUrls.size());
            System.out.println("Failed URLs : " + failedUrls.size());

            // =========================
            // FAILED URL REPORT
            // =========================
            if (!failedUrls.isEmpty()) {

                System.out.println();
                System.out.println("========== FAILED URLS ==========");

                for (String failedUrl : failedUrls) {

                    System.out.println(failedUrl);
                }
            }

            // =========================
            // PASSED URL REPORT
            // =========================
            System.out.println();
            System.out.println("========== PASSED URLS ==========");

            for (String passedUrl : passedUrls) {

                System.out.println(passedUrl);
            }

            System.out.println();
            System.out.println("=================================================");
            System.out.println("TEST EXECUTION FINISHED");
            System.out.println("=================================================");
        }

        // =================================================
        // SMOOTH SCROLL METHOD
        // =================================================
        public static void smoothScroll(WebDriver driver) throws InterruptedException {

            JavascriptExecutor js = (JavascriptExecutor) driver;

            long pageHeight = (long) js.executeScript(
                    "return document.body.scrollHeight"
            );

            long currentPosition = 0;

            int scrollStep = 500;

            while (currentPosition < pageHeight) {

                currentPosition += scrollStep;

                js.executeScript(
                        "window.scrollTo({top: arguments[0], behavior: 'smooth'});",
                        currentPosition
                );

                Thread.sleep(300);

                // Recalculate height because lazy-loaded content
                // may increase page height.
                pageHeight = (long) js.executeScript(
                        "return document.body.scrollHeight"
                );
            }

            // Scroll to bottom one final time
            js.executeScript(
                    "window.scrollTo({top: document.body.scrollHeight, behavior: 'smooth'});"
            );

            Thread.sleep(1000);

            // Scroll back to top
            js.executeScript(
                    "window.scrollTo({top: 0, behavior: 'smooth'});"
            );

            Thread.sleep(500);
        }
    }

