package branchAutomation;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class NEWTYPEOFBRANCHPAGETESTING {

    private static final String[] URLS = {

            "https://www.narayanaschools.in/andhra-pradesh/vinukonda",
            "https://www.narayanaschools.in/andhra-pradesh/visakhapatnam-paradesipalem-nhs",
            "https://www.narayanaschools.in/andhra-pradesh/visakhapatnam-paradesipalem-v-nem",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-asilimetta",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-baji-junction",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-cbm-compound",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-gajuwaka",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-kommadi",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-krm-colony-elite-school",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-kurmannpalem",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-mvp-colony",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-nad",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-nad-cross-road",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-ram-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-simon-nagar",
            "https://www.narayanaschools.in/andhra-pradesh/vishakapatnam-waltair-uplands-co-school",
            "https://www.narayanaschools.in/andhra-pradesh/vizianagaram-bobilli",
            "https://www.narayanaschools.in/andhra-pradesh/vizianagaram-parvati-puram",
            "https://www.narayanaschools.in/andhra-pradesh/vizianagaram-pradeep-nagar-dharmapuri-road",
            "https://www.narayanaschools.in/andhra-pradesh/vizianagaram-three-lamp-junction",
            "https://www.narayanaschools.in/andhra-pradesh/vl-puram",
            "https://www.narayanaschools.in/andhra-pradesh/vuyyuru",
            "https://www.narayanaschools.in/andhra-pradesh/waltair",
            "https://www.narayanaschools.in/andhra-pradesh/west-godavari-jangareddygudem",
            "https://www.narayanaschools.in/andhra-pradesh/yelukuru-bunglows",
            "https://www.narayanaschools.in/andhra-pradesh/yendada"

    };

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Use explicit waits below; avoid mixing implicit and explicit waits.
            for (String url : URLS) {
                System.out.println("Testing: " + url);

                try {
                    testPage(driver, url);
                    System.out.println("Completed: " + url);
                } catch (Exception e) {
                    System.err.println("Failed: " + url);
                    e.printStackTrace();
                }
            }
        } finally {
            driver.quit();
        }
    }

    private static void testPage(WebDriver driver, String url)
            throws InterruptedException {

        driver.manage().deleteAllCookies();
        driver.get(url);
       Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        // Fill fields only. This method does not click Submit or request an OTP.
        testEnquiryForm(driver);

        Actions actions = new Actions(driver);

        String[] aboutUsSections = {
                "Vision & Mission",
                "Why Narayana",
                "Our Management",
                "Around Campus",
                "Our Faculty"
        };

        clickMenuSections(driver, actions, "About us", aboutUsSections);

        String[] academicsSections = {
                "eKidz",
                "eChamps",
                "eTechno",
                "Sr.Secondary"
        };
        clickMenuSections(driver, actions, "Academics", academicsSections);

        String[] infrastructureSections = {
                "Class Room",
                "Lab & Activity Room",
                "Sporting Facilities",
                "Transport",
                "Safety & Security"
        };
        clickInfrastructureSections(driver, actions, infrastructureSections);

        String[] digitalInitiativeSections = {
                "nLearn",
                "nLearn Kids",
                "nConnect",
                "Smart Classroom"
        };
        clickMenuSections(
                driver,
                actions,
                "Digital Initiatives",
                digitalInitiativeSections
        );
    }


    private static void testEnquiryForm(WebDriver driver) {

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10));

        By nameLocator = By.cssSelector(
                "input[name='name'], input[placeholder*='Name' i]");

        By phoneLocator = By.cssSelector(
                "input[name='mobile'], input[type='tel']");

        By emailLocator = By.cssSelector(
                "input[name='email'], input[type='email']");

        try {

            // 1. Select Day Scholar / Residential
            By typeDropdownLocator = By.xpath(
                    "//*[normalize-space()='Select Day Scholar / Residential' or normalize-space()='Day Scholar' or normalize-space()='Residential']");

            wait.until(ExpectedConditions.elementToBeClickable(
                    typeDropdownLocator)).click();
           Thread.sleep(3000);
            // Try Residential first
            try {
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[normalize-space()='Day Scholar']")))
                        .click();

            } catch (TimeoutException e) {

                // If Residential is not available, select Day Scholar
                wait.until(ExpectedConditions.elementToBeClickable(
                                By.xpath("//*[normalize-space()='Residential']")))
                        .click();
            }

            // 2. Open Class dropdown
            By classDropdownLocator = By.xpath(
                    "//*[normalize-space()='Select Class']");

            wait.until(ExpectedConditions.elementToBeClickable(
                    classDropdownLocator)).click();

            // 3. Select VII STUDY
            By classOptionLocator = By.xpath(
                    "//div[normalize-space()='VII STUDY' or normalize-space()='VI STUDY' or normalize-space()='E-KIDZ-PP1 (L.K.G)' or normalize-space()='CLASS-6']");

            wait.until(ExpectedConditions.elementToBeClickable(
                    classOptionLocator)).click();

            // 4. Enter Name
            WebElement name = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(nameLocator));

            name.clear();
            name.sendKeys("Selenium Test");

            // 5. Enter Phone Number
            WebElement phone = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(phoneLocator));

            phone.clear();
            phone.sendKeys("9876543210");

            // 6. Enter Email
            WebElement email = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(emailLocator));

            email.clear();
            email.sendKeys("selenium-test@example.com");

            System.out.println(
                    "Enquiry form fields filled successfully: "
                            + driver.getCurrentUrl());

            // Form is not submitted.

        } catch (TimeoutException e) {

            System.out.println(
                    "Element not found or not clickable within 10 seconds: "
                            + e.getMessage());

        } catch (Exception e) {

            System.out.println(
                    "Error while filling enquiry form: "
                            + e.getMessage());
        }
    }


    private static void clickMenuSections(
            WebDriver driver,
            Actions actions,
            String menuName,
            String[] sections) throws InterruptedException {

        for (String section : sections) {
            try {
                WebElement menu = findVisibleText(driver, menuName);
                actions.moveToElement(menu).perform();
                Thread.sleep(800);

                WebElement sectionElement = waitForVisibleText(driver, section, 8);

                // Scroll into view, then click.
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript(
                                "arguments[0].scrollIntoView({block:'center'});",
                                sectionElement);

                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(sectionElement))
                        .click();

                System.out.println("Clicked: " + section);
                Thread.sleep(1500);

            } catch (TimeoutException | NoSuchElementException e) {
                System.out.println(
                        "Skipping unavailable/hidden menu item: " + section
                                + " under " + menuName);
            }
        }
    }

    private static void clickInfrastructureSections(
            WebDriver driver,
            Actions actions,
            String[] sections) throws InterruptedException {

        for (String section : sections) {
            try {
                WebElement menu = findVisibleText(driver, "Infrastructure");
                actions.moveToElement(menu).perform();
                Thread.sleep(800);

                WebElement sectionElement = waitForVisibleText(driver, section, 8);


//                ((org.openqa.selenium.JavascriptExecutor) driver)
//                        .executeScript("window.scrollBy(0, 200);");

                new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.elementToBeClickable(sectionElement))
                        .click();

                // Scroll down 200 pixels after clicking
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("window.scrollBy(0, 200);");

                System.out.println("Clicked: " + section);
                Thread.sleep(2000);

            } catch (TimeoutException | NoSuchElementException e) {
                System.out.println(
                        "Skipping unavailable/hidden Infrastructure item: "
                                + section);
            }
        }
    }
    private static WebElement waitForVisibleText(
            WebDriver driver, String text, int timeoutSeconds) {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        return wait.until(d -> {
            for (WebElement element : d.findElements(
                    By.xpath("//*[normalize-space(.)='" + text + "']"))) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    }
                } catch (org.openqa.selenium.StaleElementReferenceException ignored) {
                    // The page updated; try locating it again.
                }
            }
            return null;
        });
    }

    private static WebElement findVisibleText(WebDriver driver, String text) {
        for (WebElement element : driver.findElements(
                By.xpath("//*[normalize-space(.)='" + text + "']"))) {
            try {
                if (element.isDisplayed()) {
                    return element;
                }
            } catch (org.openqa.selenium.StaleElementReferenceException ignored) {
                // Continue checking other matches.
            }
        }

        throw new NoSuchElementException("Visible element not found: " + text);
    }


}