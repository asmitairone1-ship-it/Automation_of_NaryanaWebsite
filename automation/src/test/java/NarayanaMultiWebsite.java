import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NarayanaMultiWebsite {

    // =========================================================
    // WEBSITES
    // =========================================================

    static String[] websites = {

            "https://www.narayanaschools.in/andhra-pradesh/macheral",
            "https://www.narayanaschools.in/andhra-pradesh/machilipatnam",
            "https://www.narayanaschools.in/andhra-pradesh/madhurapudi",
            "https://www.narayanaschools.in/andhra-pradesh/madhurawada",
            "https://www.narayanaschools.in/andhra-pradesh/madhurawada-hostel",
            "https://www.narayanaschools.in/andhra-pradesh/mandapeta",
            "https://www.narayanaschools.in/andhra-pradesh/mangalagari",
            "https://www.narayanaschools.in/andhra-pradesh/mangalam",
            "https://www.narayanaschools.in/andhra-pradesh/moghalrajpuram",
            "https://www.narayanaschools.in/andhra-pradesh/nagari",
            "https://www.narayanaschools.in/andhra-pradesh/nandigama",
            "https://www.narayanaschools.in/andhra-pradesh/nandyal-padmavathi-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/narasaraopet",
            "https://www.narayanaschools.in/andhra-pradesh/narsapuram",
            "https://www.narayanaschools.in/andhra-pradesh/narsingrao-peta",
            "https://www.narayanaschools.in/andhra-pradesh/narsipatnam",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-buchireddypalem",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-dhanalakshmi-puram",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-dhanalakshmi-puram-co-school",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-dhanalakshmi-puram-girls-campus",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-gudur",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-gupta-park",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-kavali",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-kovur",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-naidupet",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-podalakur",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-prashanti-nagar-mypadu-road",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-ramesh-reddy-nagar-olympaid",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-stonehouse-pet",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-sullurpet",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-venkatagiri",
            "https://www.narayanaschools.in/andhra-pradesh/nellore-vinjamur",
            "https://www.narayanaschools.in/andhra-pradesh/ngo-colony-kadapa",
            "https://www.narayanaschools.in/andhra-pradesh/Nidadavole",
            "https://www.narayanaschools.in/andhra-pradesh/nuziveedu",
            "https://www.narayanaschools.in/andhra-pradesh/olympiad-eluru",
            "https://www.narayanaschools.in/andhra-pradesh/olympiad-palamaner",
            "https://www.narayanaschools.in/andhra-pradesh/olympiad-srinivas-mangapuram",
            "https://www.narayanaschools.in/andhra-pradesh/ongole-bhagya-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/ongole-gopal-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/palakollu",
            "https://www.narayanaschools.in/andhra-pradesh/papampeta",
            "https://www.narayanaschools.in/andhra-pradesh/pattabhipuram",
            "https://www.narayanaschools.in/andhra-pradesh/peda-palakaluru",
            "https://www.narayanaschools.in/andhra-pradesh/peddapuram",
            "https://www.narayanaschools.in/andhra-pradesh/pendurthi",
            "https://www.narayanaschools.in/andhra-pradesh/pithapuram",
            "https://www.narayanaschools.in/andhra-pradesh/ponnur",
            "https://www.narayanaschools.in/andhra-pradesh/prakasham-chirala",
            "https://www.narayanaschools.in/andhra-pradesh/prakasham-darsi"

    };



    // =========================================================
    // WAIT TIME = 5 MINUTES
    // =========================================================

    static final long WAIT_TIME =
            1* 2 * 1000L;


    public static void main(String[] args)
            throws InterruptedException {


        for (int i = 0; i < websites.length; i++) {

            String website = websites[i];

            System.out.println();
            System.out.println(
                    "================================================"
            );

            System.out.println(
                    "STARTING WEBSITE "
                            + (i + 1)
                            + " OF "
                            + websites.length
            );

            System.out.println(
                    "URL: " + website
            );

            System.out.println(
                    "================================================"
            );


            WebDriver driver = null;


            try {

                // =================================================
                // OPEN NEW BROWSER
                // =================================================

                driver = new ChromeDriver();

                driver.manage().window().maximize();

                driver.manage().timeouts().implicitlyWait(
                        Duration.ofSeconds(10)
                );


                // =================================================
                // OPEN WEBSITE
                // =================================================

                System.out.println(
                        "Opening website..."
                );

                driver.get(website);


                System.out.println(
                        "Website opened successfully."
                );


                Thread.sleep(3000);


                // =================================================
                // SCRAPE BRANCH URLs
                // =================================================

                Map<String, String> branches =
                        scrapeBranches(driver);


                System.out.println();

                System.out.println(
                        "Total branch URLs found: "
                                + branches.size()
                );


                // =================================================
                // PRINT URLs
                // =================================================

                int count = 1;

                for (Map.Entry<String, String> entry :
                        branches.entrySet()) {

                    System.out.println(
                            count
                                    + ". "
                                    + entry.getValue()
                    );

                    System.out.println(
                            "   "
                                    + entry.getKey()
                    );

                    count++;
                }


                // =================================================
                // WAIT 5 MINUTES
                // =================================================

                System.out.println();

                System.out.println(
                        "Website will remain open for 2 minutes..."
                );

                Thread.sleep(WAIT_TIME);


                System.out.println(
                        "5 minutes completed."
                );


            } catch (Exception e) {

                System.out.println();

                System.out.println(
                        "ERROR on website:"
                );

                System.out.println(
                        website
                );

                e.printStackTrace();


            } finally {


                // =================================================
                // CLOSE CURRENT WEBSITE
                // =================================================

                if (driver != null) {

                    System.out.println(
                            "Closing browser..."
                    );

                    driver.quit();

                    System.out.println(
                            "Browser closed."
                    );
                }
            }


            // =====================================================
            // NEXT WEBSITE
            // =====================================================

            System.out.println();

            System.out.println(
                    "Moving to next website..."
            );

            System.out.println();


            /*
             * Small gap between websites.
             * You can remove this if not required.
             */

            Thread.sleep(2000);
        }


        // =========================================================
        // ALL WEBSITES COMPLETED
        // =========================================================

        System.out.println();
        System.out.println(
                "================================================"
        );

        System.out.println(
                "ALL WEBSITES COMPLETED"
        );

        System.out.println(
                "================================================"
        );
    }


    // =============================================================
    // SCRAPE BRANCHES
    // =============================================================

    private static Map<String, String> scrapeBranches(
            WebDriver driver) {


        Map<String, String> branchData =
                new LinkedHashMap<>();


        try {


            JavascriptExecutor js =
                    (JavascriptExecutor) driver;


            // =====================================================
            // SCROLL TO BOTTOM
            // =====================================================

            js.executeScript(
                    "window.scrollTo(0, document.body.scrollHeight);"
            );


            Thread.sleep(2000);


            // =====================================================
            // FIND BRANCH LINKS
            // =====================================================

            List<WebElement> branches =
                    driver.findElements(
                            By.cssSelector(
                                    "a.group[href]"
                            )
                    );


            System.out.println(
                    "Branch elements found: "
                            + branches.size()
            );


            // =====================================================
            // EXTRACT URLS
            // =====================================================

            for (WebElement branch : branches) {


                String branchName =
                        branch.getText().trim();


                String url =
                        branch.getAttribute("href");


                if (url != null &&
                        !url.trim().isEmpty()) {


                    /*
                     * LinkedHashMap removes duplicate URLs.
                     */

                    branchData.put(
                            url.trim(),
                            branchName
                    );
                }
            }


        } catch (Exception e) {

            System.out.println(
                    "Could not scrape branch URLs."
            );

            e.printStackTrace();
        }


        return branchData;
    }
}