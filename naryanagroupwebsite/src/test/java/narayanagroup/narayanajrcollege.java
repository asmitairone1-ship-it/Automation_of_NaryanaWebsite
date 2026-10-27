package narayanagroup;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Set;

public class narayanajrcollege {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;
    String parentWindow;

    // Test Data
    String state = "West Bengal";
    String districts = "North 24 Parganas";
    String selectcampus = "Day Scholar & Hostel";
    String selectlocation = "BARASAT";
    String selectbranch = "Narayana College - Barasat";
    String strem = "Board+JEE Main+JEE Advance";
    String mobilenumberairtrl = "7700820345";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        driver.get("https://staging.narayanajuniorcolleges.com/");

        // Handle popup if exists
        try {
            WebElement popup = wait.withTimeout(Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("(//button[@type='button'])[3]")));
            popup.click();
        } catch (Exception e) {
            System.out.println("Popup not displayed");
        }
    }

    public void selectDropdown(By locator, String value) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(dropdown);
        wait.until(driver->select.getOptions().size() >1);
        select.selectByVisibleText(value);
        System.out.println("Selected : " + value);
    }

    @Test(priority = 2)
    public void enquiry_form() throws InterruptedException {

        // Store parent window
        parentWindow = driver.getWindowHandle();

        // Banner navigation
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#hero button.slider-prev"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#hero i.fa-arrow-right"))).click();

        // STATE
        selectDropdown(By.name("search_state"), state);

        // DISTRICT
        selectDropdown(By.id("search_district"), districts);

        // CAMPUS
        wait.until(driver ->
                new Select(driver.findElement(By.id("search_reservation_type")))
                        .getOptions().size() > 1);
        selectDropdown(By.id("search_reservation_type"), selectcampus);

        // LOCATION

//        Select locationSelect = new Select(driver.findElement(By.name("search_location")));
//
//        System.out.println("===== LOCATION OPTIONS =====");
//
//        for (WebElement option : locationSelect.getOptions()) {
//            System.out.println(option.getText());
//
//            if (option.getText().equalsIgnoreCase(selectlocation)) {
//                locationSelect.selectByVisibleText(option.getText());
//                break;
//            }
//        }
//        locationSelect.selectByVisibleText(selectlocation);
        selectDropdown(By.name("search_location"),selectlocation);

        // BRANCH
//        wait.until(driver ->
//                new Select(driver.findElement(By.name("search_branch")))
//                        .getOptions().size() > 1);
//        Select branch = new Select(driver.findElement(By.name("search_branch")));
//        branch.selectByVisibleText(selectbranch);
        selectDropdown(By.name("search_branch"),selectbranch);

        // SEARCH
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.th-btn.style3"))).click();

        // WINDOW SWITCH
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // COURSE
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("course")));
        Select course = new Select(driver.findElement(By.name("course")));

        System.out.println("===== COURSE OPTIONS =====");
        for (WebElement option : course.getOptions()) {
            System.out.println(option.getText());
        }
        course.selectByVisibleText(strem);

        // STUDENT NAME
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")))
                .sendKeys("Punit Kumar");

        // PARENT NAME
        driver.findElement(By.name("parent_name")).sendKeys("Ashok Bhagat");

        // EMAIL
        driver.findElement(By.name("email")).sendKeys("punit@kumar5643gmail.com");

        // MOBILE
        driver.findElement(By.name("mobile")).sendKeys(mobilenumberairtrl);

        // CONTINUE
        wait.until(ExpectedConditions.elementToBeClickable(By.id("info-btn"))).click();

        // OTP - Wait for manual entry
        WebElement otp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("otp")));
        otp.click();
        System.out.println("Enter OTP Manually");

        Thread.sleep(20000);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("otp-btn"))).click();

        System.out.println("Enquiry form test completed");
    }
    @Test(priority = 1)
    public void homepage() throws InterruptedException {
        for (int i = 0; i <= 10; i++) {
            js.executeScript("window.scrollBy(0,150)");
            Thread.sleep(100);
        }
        driver.findElement(By.xpath("(//a[contains(@class, 'th-btn')])[2]")).click();

        
        // JEE ADVANCE
        for (int i = 1; i <= 10; i++) {

            if (i > 9 && i % 2 == 0) {

                js.executeScript("window.scrollBy(0,250)");

                WebElement button = driver.findElement(
                        By.xpath("(//*[normalize-space()='Read More..'])[" + i + "]"));

                button.click();

                driver.navigate().back();
            } else if (i % 2 == 0) {

                WebElement button = driver.findElement(
                        By.xpath("(//*[normalize-space()='Read More..'])[" + i + "]"));
                button.click();
                selectDropdown
                        (By.cssSelector("select[name='search_state']"),state);


                driver.navigate().back();
            }
        }
        driver.navigate().back();

    }

    @AfterMethod
    public void teardown() {
        try {
            // Close child window if open
            for (String window : driver.getWindowHandles()) {
                driver.switchTo().window(window);
                driver.close();
            }
        } catch (Exception e) {
            System.out.println("No child window to close");
        }

        // Switch to parent window
        try {
            driver.switchTo().window(parentWindow);
            driver.close();
        } catch (Exception e) {
            // Parent already closed, ignore
        }
    }
}