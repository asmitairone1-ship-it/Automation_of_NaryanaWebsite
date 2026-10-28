import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class updateisgoingonlinkcheckup {

    // Configuration
    private static final int HTTP_TIMEOUT = 10000;
    private static final int PAGE_LOAD_TIMEOUT = 35;
    private static final boolean HEADLESS = true;

    // Domains that require browser verification
    private static final Set<String> BROWSER_ONLY_DOMAINS = new HashSet<>();
    static {
        BROWSER_ONLY_DOMAINS.add("forms.gle");
        BROWSER_ONLY_DOMAINS.add("forms.g");
        BROWSER_ONLY_DOMAINS.add("google.com");
        BROWSER_ONLY_DOMAINS.add("docs.google.com");
        BROWSER_ONLY_DOMAINS.add("sheets.google.com");
    }

    public static void main(String[] args) {
        WebDriver driver = null;

        try {
            // Setup WebDriverManager
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            if (HEADLESS) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            String[] websites = {
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
            };

            int totalLinksChecked = 0;
            int brokenLinksCount = 0;

            for (String site : websites) {
                System.out.println("\n" + "=".repeat(60));
                System.out.println("Checking Website: " + site);
                System.out.println("=".repeat(60));

                try {
                    long startTime = System.currentTimeMillis();
                    driver.get(site);

                    // WAIT FOR PAGE LOAD - WORKS IN ALL SELENIUM VERSIONS
                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
                    wait.until(webDriver -> {
                        JavascriptExecutor js = (JavascriptExecutor) webDriver;
                        String state = (String) js.executeScript("return document.readyState");
                        return "complete".equals(state);
                    });

                    long endTime = System.currentTimeMillis();
                    long loadTime = endTime - startTime;
                    System.out.println("Page Load Time: " + loadTime + " ms");

                    List<WebElement> linkElements = driver.findElements(By.tagName("a"));
                    Set<String> uniqueLinks = new HashSet<>();

                    for (WebElement link : linkElements) {
                        String href = link.getAttribute("href");
                        if (href != null && !href.isEmpty() && !href.startsWith("javascript:") && !href.startsWith("#")) {
                            uniqueLinks.add(href);
                        }
                    }

                    for (String linkUrl : uniqueLinks) {
                        totalLinksChecked++;
                        if (checkLinkStatus(linkUrl, site, driver)) {
                            brokenLinksCount++;
                        }
                    }

                } catch (Exception e) {
                    System.out.println("⚠️ Error loading page: " + e.getMessage());
                }
            }

            System.out.println("\n" + "=".repeat(60));
            System.out.println("SUMMARY");
            System.out.println("=".repeat(60));
            System.out.println("Total Unique Links Checked: " + totalLinksChecked);
            System.out.println("Broken Links Found: " + brokenLinksCount);
            System.out.println("=".repeat(60));

        } catch (Exception e) {
            System.err.println("❌ Main Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    /**
     * Checks link status (HTTP or Browser based on domain)
     */
    private static boolean checkLinkStatus(String linkUrl, String baseUrl, WebDriver driver) {
        try {
            URL fullUrl = resolveUrl(baseUrl, linkUrl);
            if (fullUrl == null) {
                System.out.println(linkUrl + " --> Skipped (Invalid URL)");
                return false;
            }

            String protocol = fullUrl.getProtocol();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                System.out.println(linkUrl + " --> Skipped (Non-HTTP Protocol: " + protocol + ")");
                return false;
            }

            String domain = fullUrl.getHost().toLowerCase();
            boolean isBrowserOnly = BROWSER_ONLY_DOMAINS.stream()
                    .anyMatch(browserDomain -> domain.contains(browserDomain));

            if (isBrowserOnly) {
                return checkLinkViaBrowser(linkUrl, driver);
            } else {
                return checkLinkViaHttp(linkUrl);
            }

        } catch (Exception e) {
            System.out.println(linkUrl + " --> Exception ❗ (" + e.getMessage() + ")");
            return true;
        }
    }

    /**
     * Checks link using HTTP Connection
     */
    private static boolean checkLinkViaHttp(String linkUrl) {
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(linkUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(HTTP_TIMEOUT);
            conn.setReadTimeout(HTTP_TIMEOUT);

            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36");
            conn.setInstanceFollowRedirects(true);

            int statusCode = conn.getResponseCode();
            conn.disconnect();

            if (statusCode >= 400) {
                System.out.println(linkUrl + " --> Broken Link ❌ (" + statusCode + ")");
                return true;
            } else {
                System.out.println(linkUrl + " --> Valid Link ✅ (" + statusCode + ")");
                return false;
            }

        } catch (IOException e) {
            System.out.println(linkUrl + " --> Exception ❗ (" + e.getMessage() + ")");
            return true;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * Checks link using Browser
     */
    private static boolean checkLinkViaBrowser(String linkUrl, WebDriver driver) {
        try {
            driver.get(linkUrl);

            // WAIT FOR PAGE LOAD - WORKS IN ALL SELENIUM VERSIONS
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(webDriver -> {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                String state = (String) js.executeScript("return document.readyState");
                return "complete".equals(state);
            });

            String title = driver.getTitle();
            String currentUrl = driver.getCurrentUrl();

            boolean isError = title.contains("Error") || title.contains("404") ||
                    title.contains("401") || title.contains("403") ||
                    title.contains("410") || title.contains("Gone");

            if (currentUrl.contains("error") || currentUrl.contains("404") || currentUrl.contains("401")) {
                isError = true;
            }

            if (isError) {
                System.out.println(linkUrl + " --> Broken Link ❌ (Browser Error)");
                return true;
            } else {
                System.out.println(linkUrl + " --> Valid Link ✅ (Browser Verified)");
                return false;
            }

        } catch (Exception e) {
            System.out.println(linkUrl + " --> Exception ❗ (" + e.getMessage() + ")");
            return true;
        }
    }

    /**
     * Resolves relative URLs against the base URL
     */
    private static URL resolveUrl(String baseUrl, String relativeUrl) {
        try {
            if (relativeUrl.startsWith("http://") || relativeUrl.startsWith("https://")) {
                return new URL(relativeUrl);
            }
            if (relativeUrl.startsWith("//")) {
                return new URL(baseUrl.replace(baseUrl.split(":")[0], "https") + relativeUrl);
            }
            if (relativeUrl.startsWith("/")) {
                String base = baseUrl.split("/")[0] + "//" + baseUrl.split("/")[2];
                return new URL(base + relativeUrl);
            }
            String base = baseUrl.substring(0, baseUrl.lastIndexOf("/") + 1);
            return new URL(base + relativeUrl);
        } catch (Exception e) {
            return null;
        }
    }
}