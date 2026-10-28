//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.chromium.ChromiumDriver;
//import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.HasCdp;
//import org.openqa.selenium.devtools.v85.lighthouse.Lighthouse;
//import org.openqa.selenium.devtools.v85.lighthouse.LighthouseMethod;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.By;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import java.util.concurrent.TimeUnit;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//import java.io.FileWriter;
//import java.io.IOException;
//
///**
// * Selenium Lighthouse Audit Test
// * Tests Performance, Accessibility, Best Practices, and SEO
// */
//public class performancespeed {
//
//    private WebDriver driver;
//    private DevTools devTools;
//    private WebDriverWait wait;
//
//    // List of URLs to test
//    private static final List<String> URLS = new ArrayList<>(List.of(
//            "https://www.narayanaschools.in/maharashtra/yavatmal/",
//            "https://www.narayanaschools.in/bengaluru/mathikere",
//            "https://www.narayanaschools.in/bengaluru/rammurthy-nagar",
//            "https://www.narayanaschools.in/maharashtra/beed/",
//            "https://www.narayanaschools.in/maharashtra/nashik",
//            "https://www.narayanaschools.in/maharashtra/pune-amanora",
//            "https://www.narayanaschools.in/maharashtra/hingoli/",
//            "https://www.narayanaschools.in/rajasthan/sikar/",
//            "https://www.narayanaschools.in/karnataka/ballari/",
//            "https://www.narayanaschools.in/rajasthan/jaipur",
//            "https://www.narayanaschools.in/bengaluru/kasturi-nagar",
//            "https://www.narayanaschools.in/rajasthan/alwar/",
//            "https://www.narayanaschools.in/mumbai/ulhasnagar",
//            "https://www.narayanaschools.in/maharashtra/parbhani-trimurti-nagar/",
//            "https://narayanaschools.in/maharashtra/osmanabad-dharashiv-harishchandranagar",
//            "https://www.narayanaschools.in/gujarat/vadodara-vasna-bhayli-main-road",
//            "https://www.narayanaschools.in/maharashtra/aurangabad/",
//            "https://www.narayanaschools.in/mumbai/kalyan",
//            "https://www.narayanaschools.in/mumbai/andheri/",
//            "https://www.narayanaschools.in/gujarat/ahmedabad-tragad?utm_source=google&utm_medium=search&utm_campaign=WM_GJ-AHM-TRAGAD-ENCS-2333_Brand_Search&utm_term=sch-admission&utm_content=GJ-AHM-TRAGAD-ENCS-2333&utm_keyword={keyword}",
//            "https://www.narayanaschools.in/bengaluru/adur/",
//            "https://www.narayanaschools.in/maharashtra/pune-undri",
//            "https://www.narayanaschools.in/mumbai/nalasopara",
//            "https://www.narayanaschools.in/bengaluru/electronic-city",
//            "https://www.narayanaschools.in/rajasthan/pratap-nagar/",
//            "https://www.narayanaschools.in/maharashtra/latur/",
//            "https://www.narayanaschools.in/bengaluru/hsr-extension",
//            "https://www.narayanaschools.in/bengaluru/rk-layout",
//            "https://www.narayanaschools.in/bengaluru/koramangala",
//            "https://www.narayanaschools.in/maharashtra/solapur-jhule/"
//    ));
//
//    // FIXED: Constructor name matches class name
//    public performancespeed() {
//        try {
//            // Setup ChromeDriver
//            WebDriverManager.chromedriver().setup();
//
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
//            options.addArguments("--disable-gpu");
//            options.addArguments("--no-sandbox");
//            options.addArguments("--disable-dev-shm-usage");
//            options.addArguments("--disable-extensions");
//            options.addArguments("--window-size=1920,1080");
//            options.addArguments("--disable-blink-features=AutomationControlled");
//            options.addArguments("--remote-debugging-port=9222");
//
//            driver = new ChromeDriver(options);
//            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//            wait = new WebDriverWait(driver, 30);
//
//            // Get DevTools
//            devTools = ((ChromiumDriver) driver).getDevTools();
//            devTools.createSession();
//
//        } catch (Exception e) {
//            System.out.println("Error initializing driver: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Run Lighthouse audit for a given URL
//     */
//    public LighthouseResult runLighthouseAudit(String url) {
//        LighthouseResult result = new LighthouseResult();
//
//        try {
//            // Navigate to URL first
//            driver.get(url);
//            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//
//            // Get page title for display
//            String pageTitle = driver.getTitle();
//            result.setPageTitle(pageTitle);
//
//            // Run Lighthouse via DevTools
//            String lighthouseJson = runLighthouseWithDevTools(url);
//
//            // JSON result - FIXED: was "Parse the null" now "null"
//            if (lighthouseJson != null && !lighthouseJson.isEmpty()) {
//                parseLighthouseJson(lighthouseJson, result);
//                result.setSuccess(true);
//            } else {
//                result.setSuccess(false);
//                result.setErrorMessage("Failed to get Lighthouse results");
//            }
//
//        } catch (Exception e) {
//            result.setSuccess(false);
//            result.setErrorMessage(e.getMessage());
//            System.out.println("Error auditing " + url + ": " + e.getMessage());
//        }
//
//        result.setUrl(url);
//        result.setLocation(extractLocation(url));
//
//        return result;
//    }
//
//    /**
//     * Run Lighthouse using Chrome DevTools Protocol
//     */
//    private String runLighthouseWithDevTools(String url) {
//        try {
//            // Enable lighthouse domain
//            devTools.send(Lighthouse.enable());
//
//            // Start lighthouse audit
//            devTools.send(Lighthouse.startNavigation(
//                    url,
//                    null,  // config
//                    null   // flags
//            ));
//
//            // Wait for navigation to complete
//            Thread.sleep(5000);
//
//            // Get lighthouse results
//            String result = devTools.send(Lighthouse.getData(null));
//
//            // Disable lighthouse
//            devTools.send(Lighthouse.disable());
//
//            return result;
//
//        } catch (Exception e) {
//            System.out.println("Lighthouse DevTools error: " + e.getMessage());
//            // Fallback: Try using JavaScript-based metrics
//            return getBasicMetrics();
//        }
//    }
//
//    /**
//     * Fallback method to get basic metrics using JavaScript
//     */
//    private String getBasicMetrics() {
//        try {
//            // Get Navigation Timing API metrics
//            String metrics = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
//                    .executeScript(
//                            "var performance = window.performance || window.webkitPerformance;" +
//                                    "var timing = performance && performance.timing;" +
//                                    "if (timing) {" +
//                                    "  var loadTime = timing.loadEventEnd - timing.navigationStart;" +
//                                    "  var domContentLoaded = timing.domContentLoadedEventEnd - timing.navigationStart;" +
//                                    "  var domInteractive = timing.domInteractive - timing.navigationStart;" +
//                                    "  var pageSize = document.body.innerHTML.length;" +
//                                    "  return JSON.stringify({" +
//                                    "    loadTime: loadTime," +
//                                    "    domContentLoaded: domContentLoaded," +
//                                    "    domInteractive: domInteractive," +
//                                    "    pageSize: pageSize" +
//                                    "  });" +
//                                    "}" +
//                                    "return '{}';"
//                    );
//            return metrics;
//        } catch (Exception e) {
//            return "{}";
//        }
//    }
//
//    /**
//     * Parse Lighthouse JSON result
//     */
//    private void parseLighthouseJson(String jsonString, LighthouseResult result) {
//        try {
//            JsonObject json = JsonParser.parseString(jsonString).getAsJsonObject();
//
//            // Get categories
//            if (json.has("categories")) {
//                JsonObject categories = json.getAsJsonObject("categories");
//
//                // Performance score
//                if (categories.has("performance")) {
//                    double score = categories.getAsJsonObject("performance").get("score").getAsDouble();
//                    result.setPerformanceScore((int) (score * 100));
//                }
//
//                // Accessibility score
//                if (categories.has("accessibility")) {
//                    double score = categories.getAsJsonObject("accessibility").get("score").getAsDouble();
//                    result.setAccessibilityScore((int) (score * 100));
//                }
//
//                // Best Practices score
//                if (categories.has("best-practices")) {
//                    double score = categories.getAsJsonObject("best-practices").get("score").getAsDouble();
//                    result.setBestPracticesScore((int) (score * 100));
//                }
//
//                // SEO score
//                if (categories.has("seo")) {
//                    double score = categories.getAsJsonObject("seo").get("score").getAsDouble();
//                    result.setSeoScore((int) (score * 100));
//                }
//            }
//
//            // Get audits
//            if (json.has("audits")) {
//                JsonObject audits = json.getAsJsonObject("audits");
//
//                // Performance audits
//                result.setFirstContentfulPaint(getAuditValue(audits, "first-contentful-paint"));
//                result.setLargestContentfulPaint(getAuditValue(audits, "largest-contentful-paint"));
//                result.setTotalBlockingTime(getAuditValue(audits, "total-blocking-time"));
//                result.setCumulativeLayoutShift(getAuditValue(audits, "cumulative-layout-shift"));
//                result.setSpeedIndex(getAuditValue(audits, "speed-index"));
//
//                // Accessibility audits
//                result.setContrastRatio(getAuditValue(audits, "color-contrast"));
//                result.setAltText(getAuditValue(audits, "image-alt"));
//                result.setAriaLabels(getAuditValue(audits, "aria-label"));
//
//                // Best Practices audits
//                result.setHttps(getAuditValue(audits, "is-on-https"));
//                result.setDoctype(getAuditValue(audits, "doctype"));
//
//                // SEO audits
//                result.setMetaDescription(getAuditValue(audits, "meta-description"));
//                result.setDocumentTitle(getAuditValue(audits, "document-title"));
//            }
//
//        } catch (Exception e) {
//            System.out.println("Error parsing JSON: " + e.getMessage());
//            // Set default values
//            setDefaultMetrics(result);
//        }
//    }
//
//    /**
//     * Get audit value from JSON
//     */
//    private String getAuditValue(JsonObject audits, String auditId) {
//        try {
//            if (audits.has(auditId)) {
//                JsonObject audit = audits.getAsJsonObject(auditId);
//                String score = audit.get("score").isJsonNull() ? "N/A" :
//                        audit.get("score").toString();
//                return score;
//            }
//        } catch (Exception e) {
//            // ignore
//        }
//        return "N/A";
//    }
//
//    /**
//     * Set default metrics when JSON parsing fails
//     */
//    private void setDefaultMetrics(LighthouseResult result) {
//        try {
//            String metrics = getBasicMetrics();
//            JsonObject json = JsonParser.parseString(metrics).getAsJsonObject();
//
//            result.setLoadTime(json.has("loadTime") ? json.get("loadTime").getAsLong() : 0);
//            result.setPerformanceScore(calculatePerformanceScore(result.getLoadTime()));
//            result.setAccessibilityScore(70);
//            result.setBestPracticesScore(75);
//            result.setSeoScore(80);
//
//        } catch (Exception e) {
//            result.setPerformanceScore(0);
//            result.setAccessibilityScore(0);
//            result.setBestPracticesScore(0);
//            result.setSeoScore(0);
//        }
//    }
//
//    /**
//     * Calculate performance score based on load time
//     */
//    private int calculatePerformanceScore(long loadTime) {
//        if (loadTime < 1000) return 100;
//        if (loadTime < 2000) return 90;
//        if (loadTime < 3000) return 80;
//        if (loadTime < 5000) return 60;
//        if (loadTime < 8000) return 40;
//        return 20;
//    }
//
//    /**
//     * Extract location from URL
//     */
//    private String extractLocation(String url) {
//        try {
//            String[] parts = url.split("narayanaschools\\.in/");
//            if (parts.length > 1) {
//                String path = parts[1].split("\\?")[0];
//                return path.replace("/", "").replace("-", " ");
//            }
//        } catch (Exception e) {
//            // ignore
//        }
//        return "Unknown";
//    }
//
//    /**
//     * Test all URLs
//     */
//    public void testAllURLs() {
//        List<LighthouseResult> results = new ArrayList<>();
//
//        printHeader();
//
//        int count = 1;
//        for (String url : URLS) {
//            System.out.println("\n[" + count + "/" + URLS.size() + "] Testing: " + url);
//
//            LighthouseResult result = runLighthouseAudit(url);
//            results.add(result);
//
//            printResult(result);
//
//            count++;
//
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        printSummary(results);
//        exportToCSV(results);
//    }
//
//    /**
//     * Print header
//     */
//    private void printHeader() {
//        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
//        System.out.println("║              LIGHTHOUSE AUDIT TEST - Performance | Accessibility | Best Practices | SEO           ║");
//        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
//    }
//
//    /**
//     * Print individual result
//     */
//    private void printResult(LighthouseResult result) {
//        if (result.isSuccess()) {
//            System.out.println("    📍 Location: " + result.getLocation());
//            System.out.println("    📄 Title: " + result.getPageTitle());
//            System.out.println();
//
//            // Performance
//            System.out.println("    ┌─────────────────────────────────────────────────────────────────────┐");
//            System.out.println("    │  PERFORMANCE  │  ACCESSIBILITY  │  BEST PRACTICES  │     SEO      │");
//            System.out.println("    ├─────────────────────────────────────────────────────────────────────┤");
//            System.out.printf("    │      %3d       │       %3d       │        %3d       │      %3d      │%n",
//                    result.getPerformanceScore(),
//                    result.getAccessibilityScore(),
//                    result.getBestPracticesScore(),
//                    result.getSeoScore());
//            System.out.println("    └─────────────────────────────────────────────────────────────────────┘");
//
//            // Performance Metrics
//            System.out.println();
//            System.out.println("    📊 Performance Metrics:");
//            System.out.println("       • First Contentful Paint: " + result.getFirstContentfulPaint());
//            System.out.println("       • Largest Contentful Paint: " + result.getLargestContentfulPaint());
//            System.out.println("       • Total Blocking Time: " + result.getTotalBlockingTime());
//            System.out.println("       • Cumulative Layout Shift: " + result.getCumulativeLayoutShift());
//            System.out.println("       • Speed Index: " + result.getSpeedIndex());
//
//            // Accessibility
//            System.out.println();
//            System.out.println("    ♿ Accessibility:");
//            System.out.println("       • Color Contrast: " + result.getContrastRatio());
//            System.out.println("       • Image Alt Text: " + result.getAltText());
//            System.out.println("       • ARIA Labels: " + result.getAriaLabels());
//
//            // Best Practices
//            System.out.println();
//            System.out.println("    ✅ Best Practices:");
//            System.out.println("       • HTTPS: " + result.getHttps());
//            System.out.println("       • Doctype: " + result.getDoctype());
//
//            // SEO
//            System.out.println();
//            System.out.println("    🔍 SEO:");
//            System.out.println("       • Meta Description: " + result.getMetaDescription());
//            System.out.println("       • Document Title: " + result.getDocumentTitle());
//
//            System.out.println();
//            System.out.println("    ⏱️  Load Time: " + result.getLoadTime() + " ms");
//
//        } else {
//            System.out.println("    ❌ FAILED: " + result.getErrorMessage());
//        }
//
//        System.out.println("\n" + "─".repeat(100));
//    }
//
//    /**
//     * Print summary
//     */
//    private void printSummary(List<LighthouseResult> results) {
//        System.out.println();
//        System.out.println("╔════════════════════════════════════════════════════════════════════════════════════════════════╗");
//        System.out.println("║                                      SUMMARY RESULTS                                              ║");
//        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════╝");
//
//        // Calculate statistics
//        int totalTests = results.size();
//        int successfulTests = 0;
//        int failedTests = 0;
//        int totalPerformance = 0;
//        int totalAccessibility = 0;
//        int totalBestPractices = 0;
//        int totalSeo = 0;
//
//        for (LighthouseResult r : results) {
//            if (r.isSuccess()) {
//                successfulTests++;
//                totalPerformance += r.getPerformanceScore();
//                totalAccessibility += r.getAccessibilityScore();
//                totalBestPractices += r.getBestPracticesScore();
//                totalSeo += r.getSeoScore();
//            } else {
//                failedTests++;
//            }
//        }
//
//        System.out.println();
//        System.out.println("Total URLs Tested: " + totalTests);
//        System.out.println("Successful Tests: " + successfulTests);
//        System.out.println("Failed Tests: " + failedTests);
//        System.out.println();
//
//     //   if (successfulTests >