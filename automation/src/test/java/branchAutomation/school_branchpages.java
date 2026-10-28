//package branchAutomation;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.TimeoutException;
//import org.openqa.selenium.StaleElementReferenceException;
//import org.openqa.selenium.ElementClickInterceptedException;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterSuite;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.time.Duration;
//import java.util.ArrayList;
//import java.util.List;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//public class school_branchpages {
//}
//
//
//
//public class NarayanaNavigationTest {
//
//    // =========================================================
//    // CONFIGURATION
//    // =========================================================
//
//    private static final int ELEMENT_TIMEOUT_SECONDS = 15;
//
//    // 2-second pause between submenu clicks
//    private static final int CLICK_PAUSE_SECONDS = 2;
//
//    // 2-minute manual inspection
//    private static final int MANUAL_INSPECTION_SECONDS = 120;
//
//    // URLs file
//    private static final String URL_FILE = "websites.txt";
//
//
//    // =========================================================
//    // ABOUT US SUBMENU ITEMS
//    // =========================================================
//
//    private static final String[] ABOUT_US_ITEMS = {
//
//            "Vision & Mission",
//            "Why Narayana",
//            "Our Management",
//            "Our Faculty",
//            "Around Campus"
//    };
//
//
//    // =========================================================
//    // INFRASTRUCTURE SUBMENU ITEMS
//    // =========================================================
//
//    private static final String[] INFRASTRUCTURE_ITEMS = {
//
//            "Class Room",
//            "Lab & Activity Room",
//            "Sporting Facilities",
//            "Transport",
//            "Safety & Security"
//    };
//
//
//    // =========================================================
//    // TESTNG DATA PROVIDER
//    // =========================================================
//
//    @DataProvider(
//            name = "websites",
//            parallel = false
//    )
//    public Object[][] websites() {
//
//        List<String> urls = loadUrlsFromFile(URL_FILE);
//
//        Object[][] data = new Object[urls.size()][1];
//
//        for (int i = 0; i < urls.size(); i++) {
//
//            data[i][0] = urls.get(i);
//        }
//
//        System.out.println();
//        System.out.println("==========================================");
//        System.out.println("TOTAL WEBSITES LOADED: " + urls.size());
//        System.out.println("==========================================");
//
//        return data;
//    }
//
//
//    // =========================================================
//    // MAIN TEST
//    // =========================================================
//
//    @Test(
//            dataProvider = "websites",
//            description = "Validate About Us and Infrastructure navigation"
//    )
//    public void validateWebsiteNavigation(String baseUrl) {
//
//        WebDriver driver = null;
//
//        long startTime = System.currentTimeMillis();
//
//        try {
//
//            System.out.println();
//            System.out.println();
//            System.out.println(
//                    "===================================================="
//            );
//
//            System.out.println(
//                    "STARTING WEBSITE: " + baseUrl
//            );
//
//            System.out.println(
//                    "===================================================="
//            );
//
//
//            // =================================================
//            // CREATE FRESH BROWSER
//            // =================================================
//
//            driver = new ChromeDriver();
//
//            driver.manage().window().maximize();
//
//            driver.manage().timeouts().pageLoadTimeout(
//                    Duration.ofSeconds(45)
//            );
//
//            driver.manage().timeouts().scriptTimeout(
//                    Duration.ofSeconds(30)
//            );
//
//
//            WebDriverWait wait =
//                    new WebDriverWait(
//                            driver,
//                            Duration.ofSeconds(
//                                    ELEMENT_TIMEOUT_SECONDS
//                            )
//                    );
//
//
//            // =================================================
//            // OPEN BASE URL
//            // =================================================
//
//            openBaseUrl(
//                    driver,
//                    wait,
//                    baseUrl
//            );
//
//
//            // =================================================
//            // ABOUT US
//            // =================================================
//
//            System.out.println();
//            System.out.println(
//                    "---------- ABOUT US ----------"
//            );
//
//            processMenu(
//                    driver,
//                    wait,
//                    baseUrl,
//                    "About us",
//                    ABOUT_US_ITEMS
//            );
//
//
//            // =================================================
//            // INFRASTRUCTURE
//            // =================================================
//
//            System.out.println();
//            System.out.println(
//                    "---------- INFRASTRUCTURE ----------"
//            );
//
//            processMenu(
//                    driver,
//                    wait,
//                    baseUrl,
//                    "Infrastructure",
//                    INFRASTRUCTURE_ITEMS
//            );
//
//
//            // =================================================
//            // MANUAL INSPECTION
//            // =================================================
//
//            System.out.println();
//            System.out.println(
//                    "=========================================="
//            );
//
//            System.out.println(
//                    "NAVIGATION COMPLETED"
//            );
//
//            System.out.println(
//                    "MANUAL INSPECTION STARTED"
//            );
//
//            System.out.println(
//                    "You have "
//                            + MANUAL_INSPECTION_SECONDS
//                            + " seconds."
//            );
//
//            System.out.println(
//                    "=========================================="
//            );
//
//
//            countdown(
//                    MANUAL_INSPECTION_SECONDS
//            );
//
//
//            System.out.println();
//            System.out.println(
//                    "Manual inspection completed."
//            );
//
//
//        } catch (Exception e) {
//
//            // =================================================
//            // IMPORTANT:
//            // One website failure does NOT stop remaining sites.
//            // =================================================
//
//            System.err.println();
//            System.err.println(
//                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
//            );
//
//            System.err.println(
//                    "WEBSITE FAILED:"
//            );
//
//            System.err.println(baseUrl);
//
//            System.err.println(
//                    "ERROR: " + e.getMessage()
//            );
//
//            System.err.println(
//                    "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
//            );
//
//            e.printStackTrace();
//
//
//        } finally {
//
//            // =================================================
//            // ALWAYS CLOSE CURRENT BROWSER
//            // =================================================
//
//            if (driver != null) {
//
//                try {
//
//                    driver.quit();
//
//                    System.out.println();
//                    System.out.println(
//                            "Browser closed for: "
//                                    + baseUrl
//                    );
//
//                } catch (Exception e) {
//
//                    System.err.println(
//                            "Could not close browser: "
//                                    + e.getMessage()
//                    );
//                }
//            }
//
//
//            long endTime = System.currentTimeMillis();
//
//            long totalSeconds =
//                    (endTime - startTime) / 1000;
//
//            System.out.println();
//
//            System.out.println(
//                    "Website execution time: "
//                            + totalSeconds
//                            + " seconds"
//            );
//
//            System.out.println(
//                    "Moving to next website..."
//            );
//        }
//    }
//
//
//    // =========================================================
//    // OPEN BASE URL
//    // =========================================================
//
//    private void openBaseUrl(
//            WebDriver driver,
//            WebDriverWait wait,
//            String baseUrl) {
//
//        System.out.println(
//                "Opening: " + baseUrl
//        );
//
//        driver.get(baseUrl);
//
//        wait.until(
//                ExpectedConditions.presenceOfElementLocated(
//                        By.tagName("body")
//                )
//        );
//
//        System.out.println(
//                "Page loaded successfully."
//        );
//    }
//
//
//    // =========================================================
//    // PROCESS MENU
//    // =========================================================
//
//    private void processMenu(
//            WebDriver driver,
//            WebDriverWait wait,
//            String baseUrl,
//            String menuName,
//            String[] submenuItems)
//            throws InterruptedException {
//
//
//        System.out.println();
//        System.out.println(
//                "Opening menu: " + menuName
//        );
//
//
//        for (String submenuItem : submenuItems) {
//
//            try {
//
//                System.out.println();
//                System.out.println(
//                        "Testing: "
//                                + menuName
//                                + " -> "
//                                + submenuItem
//                );
//
//
//                // =============================================
//                // IMPORTANT
//                //
//                // Return to base URL before every submenu item.
//                // This makes the test independent of where the
//                // previous submenu click navigated.
//                // =============================================
//
//                openBaseUrl(
//                        driver,
//                        wait,
//                        baseUrl
//                );
//
//
//                // =============================================
//                // Open menu
//                // =============================================
//
//                WebElement menu =
//                        findVisibleMenu(
//                                driver,
//                                menuName
//                        );
//
//
//                if (menu == null) {
//
//                    throw new RuntimeException(
//                            "Menu not found: "
//                                    + menuName
//                    );
//                }
//
//
//                openMenu(
//                        driver,
//                        menu
//                );
//
//
//                // =============================================
//                // Find submenu freshly
//                // =============================================
//
//                WebElement submenu =
//                        findVisibleSubmenu(
//                                driver,
//                                submenuItem
//                        );
//
//
//                if (submenu == null) {
//
//                    throw new RuntimeException(
//                            "Submenu not found: "
//                                    + submenuItem
//                    );
//                }
//
//
//                // =============================================
//                // Scroll submenu into view
//                // =============================================
//
//                scrollIntoView(
//                        driver,
//                        submenu
//                );
//
//
//                Thread.sleep(500);
//
//
//                // =============================================
//                // Click submenu
//                // =============================================
//
//                clickElement(
//                        driver,
//                        submenu
//                );
//
//
//                System.out.println(
//                        "Clicked successfully: "
//                                + submenuItem
//                );
//
//
//                // =============================================
//                // Wait for navigation
//                // =============================================
//
//                waitForPageLoad(driver);
//
//
//                System.out.println(
//                        "Navigation completed: "
//                                + driver.getCurrentUrl()
//                );
//
//
//                // =============================================
//                // 2 SECOND PAUSE
//                // =============================================
//
//                Thread.sleep(
//                        CLICK_PAUSE_SECONDS * 1000L
//                );
//
//
//            } catch (Exception e) {
//
//                // =============================================
//                // Log individual submenu failure
//                // Continue with next submenu
//                // =============================================
//
//                System.err.println(
//                        "FAILED: "
//                                + menuName
//                                + " -> "
//                                + submenuItem
//                );
//
//                System.err.println(
//                        "Reason: "
//                                + e.getMessage()
//                );
//            }
//        }
//    }
//
//
//    // =========================================================
//    // FIND VISIBLE MENU
//    // =========================================================
//
//    private WebElement findVisibleMenu(
//            WebDriver driver,
//            String menuName) {
//
//
//        List<WebElement> elements =
//                driver.findElements(
//                        By.xpath(
//                                "//*[self::a or self::button or self::div]" +
//                                        "[normalize-space()="
//                                        + xpathString(menuName)
//                                        + "]"
//                        )
//                );
//
//
//        for (WebElement element : elements) {
//
//            try {
//
//                if (element.isDisplayed()) {
//
//                    return element;
//                }
//
//            } catch (StaleElementReferenceException ignored) {
//            }
//        }
//
//
//        return null;
//    }
//
//
//    // =========================================================
//    // OPEN MENU
//    // =========================================================
//
//    private void openMenu(
//            WebDriver driver,
//            WebElement menu)
//            throws InterruptedException {
//
//
//        Actions actions =
//                new Actions(driver);
//
//
//        try {
//
//            // First try hover
//            actions
//                    .moveToElement(menu)
//                    .perform();
//
//            Thread.sleep(800);
//
//
//            // If dropdown is not visible after hover,
//            // click the menu.
//            if (!isAnyDropdownVisible(driver)) {
//
//                try {
//
//                    menu.click();
//
//                } catch (Exception e) {
//
//                    ((JavascriptExecutor) driver)
//                            .executeScript(
//                                    "arguments[0].click();",
//                                    menu
//                            );
//                }
//            }
//
//
//        } catch (StaleElementReferenceException e) {
//
//            /*
//             * Re-find menu if React/Next.js refreshed DOM.
//             */
//
//            throw e;
//        }
//    }
//
//
//    // =========================================================
//    // FIND VISIBLE SUBMENU
//    // =========================================================
//
//    private WebElement findVisibleSubmenu(
//            WebDriver driver,
//            String submenuName) {
//
//
//        List<WebElement> elements =
//                driver.findElements(
//                        By.xpath(
//                                "//a[normalize-space()="
//                                        + xpathString(submenuName)
//                                        + "]"
//                        )
//                );
//
//
//        /*
//         * If submenu is implemented as button/div,
//         * try those too.
//         */
//
//        if (elements.isEmpty()) {
//
//            elements =
//                    driver.findElements(
//                            By.xpath(
//                                    "//*[self::button or self::div]" +
//                                            "[normalize-space()="
//                                            + xpathString(submenuName)
//                                            + "]"
//                            )
//                    );
//        }
//
//
//        /*
//         * Always return the currently visible element.
//         */
//
//        for (WebElement element : elements) {
//
//            try {
//
//                if (element.isDisplayed()) {
//
//                    return element;
//                }
//
//            } catch (StaleElementReferenceException ignored) {
//            }
//        }
//
//
//        return null;
//    }
//
//
//    // =========================================================
//    // CLICK ELEMENT
//    // =========================================================
//
//    private void clickElement(
//            WebDriver driver,
//            WebElement element) {
//
//
//        try {
//
//            element.click();
//
//        } catch (
//                ElementClickInterceptedException |
//                StaleElementReferenceException e) {
//
//
//            System.out.println(
//                    "Normal click failed. Using JavaScript click."
//            );
//
//
//            ((JavascriptExecutor) driver)
//                    .executeScript(
//                            "arguments[0].click();",
//                            element
//                    );
//        }
//    }
//
//
//    // =========================================================
//    // SCROLL INTO VIEW
//    // =========================================================
//
//    private void scrollIntoView(
//            WebDriver driver,
//            WebElement element) {
//
//
//        ((JavascriptExecutor) driver)
//                .executeScript(
//                        "arguments[0].scrollIntoView(" +
//                                "{block:'center', inline:'nearest'});",
//                        element
//                );
//    }
//
//
//    // =========================================================
//    // WAIT FOR PAGE
//    // =========================================================
//
//    private void waitForPageLoad(
//            WebDriver driver) {
//
//
//        try {
//
//            new WebDriverWait(
//                    driver,
//                    Duration.ofSeconds(15)
//            ).until(d -> {
//
//                JavascriptExecutor js =
//                        (JavascriptExecutor) d;
//
//                String state =
//                        (String) js.executeScript(
//                                "return document.readyState;"
//                        );
//
//                return "complete".equals(state);
//            });
//
//        } catch (TimeoutException e) {
//
//            System.out.println(
//                    "Page load wait timed out. Continuing..."
//            );
//        }
//    }
//
//
//    // =========================================================
//    // CHECK DROPDOWN
//    // =========================================================
//
//    private boolean isAnyDropdownVisible(
//            WebDriver driver) {
//
//
//        /*
//         * Generic check for visible navigation lists.
//         *
//         * This is intentionally broad because the websites
//         * may use different HTML structures.
//         */
//
//        List<WebElement> lists =
//                driver.findElements(
//                        By.cssSelector(
//                                "header ul, nav ul"
//                        )
//                );
//
//
//        for (WebElement list : lists) {
//
//            try {
//
//                if (list.isDisplayed()) {
//
//                    return true;
//                }
//
//            } catch (Exception ignored) {
//            }
//        }
//
//
//        return false;
//    }
//
//
//    // =========================================================
//    // MANUAL INSPECTION COUNTDOWN
//    // =========================================================
//
//    private void countdown(
//            int seconds)
//            throws InterruptedException {
//
//
//        for (int remaining = seconds;
//             remaining > 0;
//             remaining--) {
//
//
//            if (remaining <= 10 ||
//                    remaining % 10 == 0) {
//
//                System.out.println(
//                        "Manual inspection remaining: "
//                                + formatTime(remaining)
//                );
//            }
//
//
//            Thread.sleep(1000);
//        }
//    }
//
//
//    // =========================================================
//    // FORMAT TIME
//    // =========================================================
//
//    private String formatTime(
//            int totalSeconds) {
//
//
//        int minutes =
//                totalSeconds / 60;
//
//        int seconds =
//                totalSeconds % 60;
//
//
//        return String.format(
//                "%02d:%02d",
//                minutes,
//                seconds
//        );
//    }
//
//
//    // =========================================================
//    // LOAD URLS FROM FILE
//    // =========================================================
//
//    private List<String> loadUrlsFromFile(
//            String fileName) {
//
//
//        List<String> urls =
//                new ArrayList<>();
//
//
//        try (
//                BufferedReader reader =
//                        new BufferedReader(
//                                new FileReader(fileName)
//                        )
//        ) {
//
//
//            String line;
//
//
//            while ((line = reader.readLine()) != null) {
//
//                line = line.trim();
//
//
//                /*
//                 * Ignore blank lines.
//                 */
//
//                if (line.isEmpty()) {
//
//                    continue;
//                }
//
//
//                /*
//                 * Ignore comments.
//                 */
//
//                if (line.startsWith("#")) {
//
//                    continue;
//                }
//
//
//                urls.add(line);
//            }
//
//
//        } catch (Exception e) {
//
//            throw new RuntimeException(
//                    "Could not read URL file: "
//                            + fileName,
//                    e
//            );
//        }
//
//
//        if (urls.isEmpty()) {
//
//            throw new RuntimeException(
//                    "No website URLs found in "
//                            + fileName
//            );
//        }
//
//
//        return urls;
//    }
//
//
//    // =========================================================
//    // XPATH STRING HELPER
//    // =========================================================
//
//    private String xpathString(
//            String value) {
//
//
//        /*
//         * Handles strings containing apostrophes.
//         */
//
//        if (!value.contains("'")) {
//
//            return "'" + value + "'";
//        }
//
//
//        if (!value.contains("\"")) {
//
//            return "\"" + value + "\"";
//        }
//
//
//        String[] parts =
//                value.split("'");
//
//
//        StringBuilder result =
//                new StringBuilder(
//                        "concat("
//                );
//
//
//        for (int i = 0;
//             i < parts.length;
//             i++) {
//
//
//            if (i > 0) {
//
//                result.append(
//                        ", \"'\", "
//                );
//            }
//
//
//            result.append(
//                    "'"
//            );
//
//            result.append(
//                    parts[i]
//            );
//
//            result.append(
//                    "'"
//            );
//        }
//
//
//        result.append(")");
//
//        return result.toString();
//    }
//}