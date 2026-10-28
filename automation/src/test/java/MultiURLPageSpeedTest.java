import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

public class MultiURLPageSpeedTest {

    private WebDriver driver;
    private WebDriverWait wait;

    // List of URLs to test
  /*  private static final List<String> URLS1 = new ArrayList<>(List.of(
            "https://www.narayanaschools.in/",
            "https://www.narayanajuniorcolleges.com/",
            "https://narayanagroup.com/",
            "https://www.narayanacoachingcenters.in/",
            "https://narayanamedicalcollege.com/",
            "https://narayanadentalcollege.com/",
            "https://www.narayanapharmacycollege.com/",
            "http://narayanacoachingcenters.in/",
            "http://chaitrapublicschool.com/",
            "http://narayanacollegeofphysiotherapy.com/",
            "http://narayanaetechnoschoolbhopal1.com/",
            "https://narayanaetechnoschoolgwalior.com/",
            "https://narayanaetechnoschoolindore.com/",
            "https://narayanaetechnoschoolshivpuri.com/",
            "https://narayanaetechnoschoolujjjain.in/",
            "http://narayanagomathyinternationalschool.com/",
            "https://narayanaschoolbhatinda.com/",
            "https://narayanaschoolbluevalleyamritsar.com/",
            "https://narayanaschoolchdroad.com/",
            "https://narayanaschoolhisar.com/",
            "https://narayanaschoolindoresiliconcity.com/",
            "https://narayanaschoolkanpur.com/",
            "https://narayanaiasacademy.com/",
            "https://narayananursingcollege.com/",
            "https://narayananursingjournal.com/",
            "https://necg.ac.in/",
            "https://necn.ac.in/",
            "https://neetjeenius.com/",
            "https://nmcmededu.com/",
            "https://nspira.in/",
            "https://reachdisha.com/",
            "https://sdaschool.in/",
            "https://stanfordpublicschool.com/",
            "https://www.narayanacoachingcenters.in/live-classes/",
            "https://college.narayanagroup.com/",
            "https://gemsakademia.in/landing/",
            "https://wbnarayanaschools.com/",
            "https://nsat.narayanagroup.com/",
            "https://nsatscore.narayanagroup.com/",
            "https://recruitment.narayanagroup.com/",
            "https://narayanaahmedabad-nasat.com/",
            "http://narayanaiasacademy.com/workshops",
            "https://nsat.narayanagroup.com/west-bengal/",
            "https://stage-prodigy.narayanagroup.com/"
    ));*/


    private static final List<String> URLS = new ArrayList<>(List.of(
            "https://www.narayanaschools.in/maharashtra/yavatmal/",
            "https://www.narayanaschools.in/bengaluru/mathikere",
            "https://www.narayanaschools.in/bengaluru/rammurthy-nagar",
            "https://www.narayanaschools.in/maharashtra/beed/",
            "https://www.narayanaschools.in/maharashtra/nashik",
            "https://www.narayanaschools.in/maharashtra/pune-amanora",
            "https://www.narayanaschools.in/maharashtra/hingoli/",
            "https://www.narayanaschools.in/rajasthan/sikar/",
            "https://www.narayanaschools.in/karnataka/ballari/",
            "https://www.narayanaschools.in/rajasthan/jaipur",
            "https://www.narayanaschools.in/bengaluru/kasturi-nagar",
            "https://www.narayanaschools.in/rajasthan/alwar/",
            "https://www.narayanaschools.in/mumbai/ulhasnagar",
            "https://www.narayanaschools.in/maharashtra/parbhani-trimurti-nagar/",
            "https://narayanaschools.in/maharashtra/osmanabad-dharashiv-harishchandranagar",
            "https://www.narayanaschools.in/gujarat/vadodara-vasna-bhayli-main-road",
            "https://www.narayanaschools.in/maharashtra/aurangabad/",
            "https://www.narayanaschools.in/mumbai/kalyan",
            "https://www.narayanaschools.in/mumbai/andheri/",
            "https://www.narayanaschools.in/gujarat/ahmedabad-tragad?utm_source=google&utm_medium=search&utm_campaign=WM_GJ-AHM-TRAGAD-ENCS-2333_Brand_Search&utm_term=sch-admission&utm_content=GJ-AHM-TRAGAD-ENCS-2333&utm_keyword={keyword}",
            "https://www.narayanaschools.in/bengaluru/adur/",
            "https://www.narayanaschools.in/maharashtra/pune-undri",
            "https://www.narayanaschools.in/mumbai/nalasopara",
            "https://www.narayanaschools.in/bengaluru/electronic-city",
            "https://www.narayanaschools.in/rajasthan/pratap-nagar/",
            "https://www.narayanaschools.in/maharashtra/latur/",
            "https://www.narayanaschools.in/bengaluru/hsr-extension",
            "https://www.narayanaschools.in/bengaluru/rk-layout",
            "https://www.narayanaschools.in/bengaluru/koramangala",
            "https://www.narayanaschools.in/maharashtra/solapur-jhule/",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-garladinne",
            "https://www.narayanaschools.in/andhra-pradesh/rayadurgam",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-kothacheruvu",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-guntakal",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-tadipatri",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-ram-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-kadiri",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-kalyandurg",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-panduranga-nagar-olympaid",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-dwaraka-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/khajanagar",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur",
            "https://www.narayanaschools.in/andhra-pradesh/papampeta",
            "https://www.narayanaschools.in/andhra-pradesh/anantapur-hindupur",
            "https://www.narayanaschools.in/andhra-pradesh/srinivas-mangapuram-boys-hostel",
            "https://www.narayanaschools.in/andhra-pradesh/olympiad-srinivas-mangapuram",
            "https://www.narayanaschools.in/andhra-pradesh/mangalam",
            "https://www.narayanaschools.in/andhra-pradesh/ramchandrapuram",
            "https://www.narayanaschools.in/andhra-pradesh/tirupathi-jeevakona",
            "https://www.narayanaschools.in/andhra-pradesh/tata-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/nagari",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-madanapalli",
            "https://www.narayanaschools.in/andhra-pradesh/tirupathi-air-bypass-road",
            "https://www.narayanaschools.in/andhra-pradesh/srinivas-puram",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-srikalahasti",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-tirupati-puttur",
            "https://www.narayanaschools.in/andhra-pradesh/tirupathi-mr-palli",
            "https://www.narayanaschools.in/andhra-pradesh/tirupathi-amaravathi-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/srinivas-mangapuram-girls-hostel",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-venkatagiri-kota",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-b-kothakota",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-greamspet",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-kuppam",
            "https://www.narayanaschools.in/andhra-pradesh/kongareddy-palli",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-pileru",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-palamaner",
            "https://www.narayanaschools.in/andhra-pradesh/olympiad-palamaner",
            "https://www.narayanaschools.in/andhra-pradesh/chittoor-punganur",
            "https://www.narayanaschools.in/andhra-pradesh/pithapuram",
            "https://www.narayanaschools.in/andhra-pradesh/anaparthi",
            "https://www.narayanaschools.in/andhra-pradesh/ramaraopeta",
            "https://www.narayanaschools.in/andhra-pradesh/amalapuram",
            "https://www.narayanaschools.in/andhra-pradesh/lakshminarayana-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/ashok-nagar"

    ));

    public MultiURLPageSpeedTest() {
        try {
            // Setup ChromeDriver automatically
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-blink-features=AutomationControlled");

            driver = new ChromeDriver(options);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30) );

        } catch (Exception e) {
            System.out.println("Error initializing driver: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Measures page loading speed for a given URL
     */
    public PageLoadResult measurePageLoadSpeed(String url) {
        PageLoadResult result = new PageLoadResult();

        try {
            // Clean up before each test
            driver.manage().deleteAllCookies();

            long startTime = System.currentTimeMillis();

            // Navigate to URL
            driver.get(url);

            // Wait for page to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            long endTime = System.currentTimeMillis();
            long pageLoadTime = endTime - startTime;

            result.setUrl(url);
            result.setPageLoadTime(pageLoadTime);
            result.setPageSize(driver.getPageSource().length());
            result.setSuccess(true);

            // Extract city/location from URL for display
            result.setLocation(extractLocation(url));

        } catch (Exception e) {
            result.setUrl(url);
            result.setSuccess(false);
            result.setErrorMessage(e.getMessage());
            result.setPageLoadTime(0);
        }

        return result;
    }
  
    /**
     * Extract location from URL for better display
     */
    private String extractLocation(String url) {
        try {
            String[] parts = url.split("narayanaschools\\.in/");
            if (parts.length > 1) {
                String path = parts[1].split("\\?")[0]; // Remove query params
                return path.replace("/", "").replace("-", " ");
            }
        } catch (Exception e) {
            // ignore
        }
        return "Unknown";
    }

    /**
     * Test all URLs and print results
     */
    public void testAllURLs() {
        List<PageLoadResult> results = new ArrayList<>();

        System.out.println("=".repeat(100));
        System.out.println("                    PAGE LOAD SPEED TEST RESULTS");
        System.out.println("=".repeat(100));
        System.out.println();

        int count = 1;
        for (String url : URLS) {
            System.out.println("Testing URL " + count + " of " + URLS.size() + ": " + url);

            PageLoadResult result = measurePageLoadSpeed(url);
            results.add(result);

            // Print result for this URL
            printResult(result);

            count++;

            // Small delay between tests
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print summary
        printSummary(results);
    }

    /**
     * Print individual result
     */
    private void printResult(PageLoadResult result) {
        if (result.isSuccess()) {
            System.out.println("    ✓ Location: " + result.getLocation());
            System.out.println("    ✓ Page Load Time: " + result.getPageLoadTime() + " ms (" +
                    String.format("%.2f", result.getPageLoadTime() / 1000.0) + " seconds)");
            System.out.println("    ✓ Page Size: " + String.format("%,d", result.getPageSize()) + " bytes");
            System.out.println();
        } else {
            System.out.println("    ✗ FAILED: " + result.getErrorMessage());
            System.out.println();
        }
        System.out.println("-".repeat(80));
    }

    /**
     * Print summary of all results
     */
    private void printSummary(List<PageLoadResult> results) {
        System.out.println();
        System.out.println("=".repeat(100));
        System.out.println("                           SUMMARY");
        System.out.println("=".repeat(100));
        System.out.println();

        // Calculate statistics
        long totalTime = 0;
        long successfulTests = 0;
        long failedTests = 0;
        long minTime = Long.MAX_VALUE;
        long maxTime = 0;

        List<Long> loadTimes = new ArrayList<>();

        for (PageLoadResult result : results) {
            if (result.isSuccess()) {
                successfulTests++;
                totalTime += result.getPageLoadTime();
                loadTimes.add(result.getPageLoadTime());

                if (result.getPageLoadTime() < minTime) {
                    minTime = result.getPageLoadTime();
                }
                if (result.getPageLoadTime() > maxTime) {
                    maxTime = result.getPageLoadTime();
                }
            } else {
                failedTests++;
            }
        }

        double averageTime = successfulTests > 0 ? (double) totalTime / successfulTests : 0;

        System.out.println("Total URLs Tested: " + results.size());
        System.out.println("Successful Tests: " + successfulTests);
        System.out.println("Failed Tests: " + failedTests);
        System.out.println();
        System.out.println("Average Page Load Time: " + String.format("%.2f", averageTime) + " ms");
        System.out.println("Fastest Page Load Time: " + minTime + " ms");
        System.out.println("Slowest Page Load Time: " + maxTime + " ms");
        System.out.println("Total Time: " + totalTime + " ms");
        System.out.println();

        // Sort and print top 5 fastest
        loadTimes.sort(Long::compareTo);
        System.out.println("=".repeat(100));
        System.out.println("                       TOP 5 FASTEST PAGES");
        System.out.println("=".repeat(100));

        int topCount = 0;
        for (Long time : loadTimes) {
            if (topCount >= 5) break;
            for (PageLoadResult result : results) {
                if (result.isSuccess() && result.getPageLoadTime() == time) {
                    topCount++;
                    System.out.println(topCount + ". " + result.getLocation() + " - " + time + " ms");
                    break;
                }
            }
        }

        System.out.println();
        System.out.println("=".repeat(100));
        System.out.println("                       TOP 5 SLOWEST PAGES");
        System.out.println("=".repeat(100));

        int slowCount = 0;
        for (int i = loadTimes.size() - 1; i >= 0; i--) {
            if (slowCount >= 5) break;
            Long time = loadTimes.get(i);
            for (PageLoadResult result : results) {
                if (result.isSuccess() && result.getPageLoadTime() == time) {
                    slowCount++;
                    System.out.println(slowCount + ". " + result.getLocation() + " - " + time + " ms");
                    break;
                }
            }
        }

        System.out.println();

        // Print all results in table format
        System.out.println("=".repeat(100));
        System.out.println("                       ALL RESULTS TABLE");
        System.out.println("=".repeat(100));
        System.out.printf("%-5s %-40s %-15s %-15s%n", "No.", "Location", "Load Time", "Status");
        System.out.println("-".repeat(100));

        int index = 1;
        for (PageLoadResult result : results) {
            String status = result.isSuccess() ? "✓ SUCCESS" : "✗ FAILED";
            String loadTime = result.isSuccess() ? result.getPageLoadTime() + " ms" : "-";
            String location = result.getLocation().length() > 40 ?
                    result.getLocation().substring(0, 37) + "..." : result.getLocation();

            System.out.printf("%-5d %-40s %-15s %-15s%n",
                    index++, location, loadTime, status);
        }

        System.out.println("=".repeat(100));
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        MultiURLPageSpeedTest tester = new MultiURLPageSpeedTest();

        try {
            tester.testAllURLs();
        } catch (Exception e) {
            System.out.println("Error during testing: " + e.getMessage());
            e.printStackTrace();
        } finally {
            tester.close();
        }
    }
}

// Result class
class PageLoadResult {
    private String url;
    private String location;
    private long pageLoadTime;
    private long pageSize;
    private boolean success;
    private String errorMessage;

    // Getters and setters
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public long getPageLoadTime() { return pageLoadTime; }
    public void setPageLoadTime(long pageLoadTime) { this.pageLoadTime = pageLoadTime; }
    public long getPageSize() { return pageSize; }
    public void setPageSize(long pageSize) { this.pageSize = pageSize; }
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
}