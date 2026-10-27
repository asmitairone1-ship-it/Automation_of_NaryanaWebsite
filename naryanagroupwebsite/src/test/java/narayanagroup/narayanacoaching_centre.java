package narayanagroup;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class narayanacoaching_centre {
    WebDriver driver;
    String phonenumberairtel = "6206681050";
    String phoneNumber = "7700820345";
    WebDriverWait wait;
    JavascriptExecutor js;
    Actions action;

    @BeforeMethod
    public void preconduction() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://staging.narayanacoachingcenters.in/");
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
    }

    // This function is use for the select method inter only the loctor with type of the locator and the value
    public void selectfromdropdown(By locator, String value) {
        WebElement options = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(options);
        wait.until(driver -> driver.findElement(locator).isEnabled());
        wait.until(driver -> select.getOptions().size() > 1);
        select.selectByVisibleText(value);

    }

    public void scrollsmooth(int endnumber, int scrollnumber, int waittime) throws InterruptedException {
        for (int i = 0; i < endnumber; i++) {
            js.executeScript("window.scrollBy(0," + scrollnumber + ")");
            Thread.sleep(waittime);
        }
    }
    public void window_switch(){
        String parents = driver.getWindowHandle();
        Set <String> childs = driver.getWindowHandles();
        for(String nacsturl: childs ){
            if(!nacsturl.equals(parents)){
                driver.switchTo().window(nacsturl);
            }
        }
    }
    public void moree(String select) {
        WebElement more = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[@class='dropdown-selected !font-noto'])[2]")));
        action.moveToElement(more).build().perform();
        List<WebElement> moreblog = driver.findElements(By.cssSelector("[style='margin: 4px 0px;']"));
        // select the blog button in the dropdown
        for (WebElement bolg : moreblog) {
            String chal = bolg.getText();
            if (chal.equalsIgnoreCase(select)) {
                bolg.click();
                break;
            }
        }
    }
    public void clickElementInsideCard(String cardClass, String cardText, String clickText) {

        By element = By.xpath(
                "//div[contains(@class,'" + cardClass + "')]" +
                        "[.//*[normalize-space()='" + cardText + "']]" +
                        "//*[normalize-space()='" + clickText + "']"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(element)
        ).click();
    }


    public void smoothScrollDown() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        long scrollHeight = (long) js.executeScript("return document.body.scrollHeight");
        long viewportHeight = (long) js.executeScript("return window.innerHeight");
        long scrollStep = 300; // pixels per step (smaller = smoother/slower)
        long currentPosition = 0;

        while (currentPosition < scrollHeight) {
            js.executeScript("window.scrollTo(0, arguments[0]);", currentPosition);
            currentPosition += scrollStep;
            Thread.sleep(150); // pause between steps for smoothness
        }

        // ensure it reaches the very bottom
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Thread.sleep(300);
    }
    public void fillFields(Map<By, String > fieldValues) {
        fieldValues.forEach((locator, value) ->
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(value)
        );
    }
    @Test
    public void quick_contact() throws InterruptedException {

//        driver.findElement(By.xpath("(//*[@name='full_name'])[1]")).sendKeys("punit kumar");
//        driver.findElement(By.cssSelector("[placeholder='eg. xxxx']")).sendKeys("Ashok Bhagat");
//        driver.findElement(By.cssSelector("div[class='css-k4xbc7'] input[placeholder='eg. test@example.com']"))
//                .sendKeys("punit.selenium@gmail.com");
//        driver.findElement(By.xpath("(//input[@placeholder='Enter Mobile Number'])[1]"))
//                .sendKeys("7700820345");
        Map<By, String> fields = new LinkedHashMap<>();// use of the java8 and stream for the from
        fields.put(By.cssSelector("[placeholder='eg. xxxx']"), "Ashok Bhagat");
        fields.put(By.cssSelector("div[class='css-k4xbc7'] input[placeholder='eg. test@example.com']"), "punit.selenium@gmail.com");
        fields.put(By.xpath("(//input[@placeholder='Enter Mobile Number'])[1]"),"7700820345");

        fillFields(fields);

        // THIS BUTTON BELONGS TO THE TRIGGER THE OTP
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='chakra-button css-6knctr'])[1]")))
                .click();
        // THIS BUTTON FOR THE CLICK ON THE FIELD OF THE OTP
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(8));
        WebElement otp = wait1.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@placeholder='Enter OTP'])[1]")
                )
        );
        otp.click();
        Thread.sleep(15000);
        // THIS BUTTON FOR THE VERIFY THE OTP
        driver.findElement(By.xpath("(//button[@class='chakra-button css-6knctr'])[1]")).click();

        // THIS ARE USE TO THE SELECT FROM THE DROPDOWN BRANCH, STREAM , COURSE , CLASS
        selectfromdropdown(By.xpath("(//select[@name='center'])[1]"), "PANJABI BAGH");
        selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='stream']"), "IIT-JEE");
        selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='course']"), "CLASS XII+ ONE YEAR PROGRAM");
        // selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='class']"),"XII PASS");
        // THIS FOR THE CHECK BUTTON
        driver.findElement(By.cssSelector("div[class='css-k4xbc7'] span[class='chakra-checkbox__control css-8ddrgo']")).click();
        // THIS FOR THE FINAL SUBMIT
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Submit'])[1]"))).click();

    }

    @Test
    public void Contact_us() throws InterruptedException {

        // Click Contact Us button
        driver.findElement(By.cssSelector("div.css-1v3fdw7")).click();

        // Wait for contact popup/form
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//div[@class='css-1l74wav'])[2]")
        ));

        // Enter Full Name
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//*[@name='full_name'])[2]")
        )).sendKeys("Asmit Bhagat");

        // Enter Email
        driver.findElement(
                By.xpath("(//input[@placeholder='eg. test@example.com'])[2]")
        ).sendKeys("asmit.selenium@gmail.com");

        // Enter Mobile Number
        driver.findElement(
                By.xpath("(//input[@placeholder='Enter Mobile Number'])[2]")
        ).sendKeys("7700820345");

        // Click Send OTP button
        WebElement sendOtpBtn = driver.findElement(
                By.xpath("/html/body/div[1]/div[9]/div/div/div/div[2]/div/form/div[1]/div[3]/div[2]/div/button")
        );
        js.executeScript("arguments[0].click();", sendOtpBtn);

        // Wait for OTP field
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(8));

        WebElement otp = wait1.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//input[@placeholder='Enter OTP'])[2]")
                )
        );

        otp.click();

        // Manual OTP wait
        Thread.sleep(15000);

        // Open dropdown
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(
                        "div.css-yuu4xi div.css-p1s8ev div.chakra-input__right-addon.css-1bkan80"
                )
        )).click();

        // Select Branch
        selectfromdropdown(
                By.xpath("//*[@id='contact-us']/div/div/div/div[2]/div/form/div[1]/div[5]/div/div[2]/select"),
                "Naupada"
        );

        // Select Goal
        selectfromdropdown(
                By.xpath("//*[@id='contact-us']/div/div/div/div[2]/div/form/div[1]/div[6]/div/div[2]/select"),
                "CRASH COURSE - IIT JEE MAIN"
        );

        // Select Course
        selectfromdropdown(
                By.xpath("(//*[@name='course'])[2]"),
                "CLASS XII ONE YEAR PROGRAM"
        );
        // Checkbox click (Scrolled into center & clicked via JS to prevent interception)
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("(//span[contains(@class, 'chakra-checkbox__control')])[2]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", checkbox);
        js.executeScript("arguments[0].click();", checkbox);
        // Submit form
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit' and contains(@class,'chakra-button')])[2]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", submitBtn);
        submitBtn.click();
    }


    @Test
    public void Home() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            driver.findElement(
                    By.cssSelector("[type='button'][class='chakra-button nextBtn css-n4fobg']")
            ).click();
        }
        scrollsmooth(10, 200, 150);
        Thread.sleep(2000);
        driver.switchTo().frame(0);

        WebElement playBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[aria-label='Play video']")));

        playBtn.click();
        Thread.sleep(10000);

        driver.findElement(By.cssSelector("[id='player-control-overlay']")).click();


        driver.switchTo().defaultContent();
        Thread.sleep(2000);

        scrollsmooth(11, 200, 150);
//==================================================================================================================================
        String courseNameJEE = "Andhra Pradesh State Class 10"; // CBSE Class 10 , Andhra Pradesh State Class 10 ,NEET
        boolean found = false;

        // Wait for 3rd slider
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("(//div[contains(@class, 'slick-slider') and contains(@class, 'slick-initialized')])[3]")
        ));

        Thread.sleep(1000);

        // Get individual slides inside the 3rd slider (excluding clones)
        List<WebElement> cards = driver.findElements(
                By.xpath("(//div[contains(@class, 'slick-slider')])[3]//div[contains(@class, 'slick-slide') and not(contains(@class, 'slick-cloned'))]")
        );

        System.out.println("Total cards found: " + cards.size());

        for (WebElement card : cards) {
            // Use innerText instead of getText() to read text from hidden/off-screen slides
            String text = card.getAttribute("innerText");
            if (text == null || text.trim().isEmpty()) {
                text = card.getAttribute("textContent");
            }

            System.out.println("Card Text: " + (text != null ? text.replace("\n", " ").trim() : ""));

            if (text != null && text.toLowerCase().contains(courseNameJEE.toLowerCase())) {
                System.out.println(">>> Target matched: " + courseNameJEE);

                // Scroll the matched card into view
                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", card);
                Thread.sleep(800);

                // Find and click the download link inside this specific card
                WebElement downloadLink = card.findElement(By.tagName("a"));

                js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", downloadLink);
                Thread.sleep(500);

                js.executeScript("arguments[0].click();", downloadLink);

                System.out.println("SUCCESS: Download clicked for: " + courseNameJEE);
                found = true;
                break;
            }
        }

        // Fallback: If not found, try slick-dots or next arrows
        if (!found) {
            System.out.println(courseNameJEE + " not found, checking controls...");

            List<WebElement> controls = driver.findElements(
                    By.xpath("(//div[contains(@class, 'slick-slider')])[3]//button[contains(@class, 'slick-arrow')] | //button[contains(@class, 'slick-next')] | //ul[contains(@class, 'slick-dots')]//li")
            );

            for (WebElement control : controls) {
                js.executeScript("arguments[0].click();", control);
                Thread.sleep(1000);

                List<WebElement> refreshedCards = driver.findElements(
                        By.xpath("(//div[contains(@class, 'slick-slider')])[3]//div[contains(@class, 'slick-slide') and not(contains(@class, 'slick-cloned'))]")
                );

                for (WebElement card : refreshedCards) {
                    String text = card.getAttribute("innerText");
                    if (text != null && text.toLowerCase().contains(courseNameJEE.toLowerCase())) {
                        WebElement downloadLink = card.findElement(By.tagName("a"));
                        js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", downloadLink);
                        Thread.sleep(500);
                        js.executeScript("arguments[0].click();", downloadLink);

                        System.out.println("SUCCESS: Download clicked for: " + courseNameJEE);
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
        }

        if (!found) {
            System.out.println("WARNING: " + courseNameJEE + " card not found!");
        }
        //==============================================================================================================
        String parentwindows = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {

            if (!window.equals(parentwindows)) {
                driver.switchTo().window(window);
            }
        }
        // Select State
        By stateLocator = By.xpath("//span[contains(normalize-space(), 'Select state')]");

        WebElement stateSelect = wait.until(
                ExpectedConditions.presenceOfElementLocated(stateLocator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", stateSelect);

        wait.until(ExpectedConditions.elementToBeClickable(stateLocator)).click();

        List<WebElement> states = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("[class='px-4 py-2 cursor-pointer flex items-center hover:bg-gray-100']")
        ));
        for (WebElement state : states) {
            if (state.getText().equalsIgnoreCase("bihar")) {
                state.click();
                break;
            }
        }

        Thread.sleep(1000);

        // Select District
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select district')]")
        )).click();

        List<WebElement> districts = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("[class='px-4 py-2 cursor-pointer flex items-center hover:bg-gray-100']")
        ));

        for (WebElement district : districts) {
            if (district.getText().equalsIgnoreCase("darbhanga")) {
                district.click();
                break;
            }
        }

        // Fill Form
        driver.findElement(By.cssSelector("[name='location']"))
                .sendKeys("testingnagar near automation wali gali");

        driver.findElement(By.cssSelector("[placeholder='Enter student name']"))
                .sendKeys("Punit kumar");
        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']"))
                .sendKeys("7700820345");

        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Get OTP']")
        )).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter OTP here']")).click();
        Thread.sleep(20000);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Verify OTP']")
        )).click();

        driver.findElement(By.cssSelector("[placeholder='Enter email']"))
                .sendKeys("punitnaagvanshi@gmail.com");
        driver.findElement(By.cssSelector("[placeholder='Enter current school']"))
                .sendKeys("selenium");

        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,150);");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Submit']")
        )).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//img[@src='/optimised-images/icons/download.svg'])[2]")
        )).click();

    }

    @Test
    public void aboutus() throws InterruptedException {
        WebElement aboutus = driver.findElement(By.cssSelector("[href='/aboutus']"));
        wait.until(ExpectedConditions.elementToBeClickable(aboutus)).click();
        scrollsmooth(10, 150, 150);
    }

    @Test
    public void coursepage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 1. Open the course dropdown on home page
        WebElement courseDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("(//span[contains(@class,'dropdown-selected')])[1]")
        ));
        action.moveToElement(courseDropdown).perform();

        // 2. Wait for all course options
        List<WebElement> courseall = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(".dropdown-item"), 2
        ));

        // 3. Store all course names
        List<String> courseNames = new ArrayList<>();
        for (WebElement course : courseall) {
            courseNames.add(course.getText().trim());
        }

        System.out.println("Total courses found: " + courseNames.size());

        // 4. Loop through each course
        for (String courseName : courseNames) {
            System.out.println("Testing course: " + courseName);

            // Reset scroll position to top
            js.executeScript("window.scrollTo(0, 0);");
            Thread.sleep(500);

            // Reset mouse position to trigger a fresh hover
            action.moveToElement(driver.findElement(By.tagName("body")), 0, 0).perform();

            // Locate and hover on course dropdown
            WebElement dropdownTrigger = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//span[contains(@class,'dropdown-selected')])[1]")
            ));
            action.moveToElement(dropdownTrigger).perform();

            // Wait for dropdown items to appear
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                    By.cssSelector(".dropdown-item"), 2
            ));

            // Click the matching course
            driver.findElements(By.cssSelector(".dropdown-item"))
                    .stream()
                    .filter(e -> e.getText().trim().equalsIgnoreCase(courseName))
                    .findFirst()
                    .ifPresent(WebElement::click);

            // Optional popup check (kept short: 3-5 seconds instead of 53 seconds)
            try {
                WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(4));
                WebElement popupCloseButton = popupWait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector("button.chakra-button.css-1o8yzt8")
                        )
                );
                popupCloseButton.click();
                System.out.println("Popup appeared and was closed.");
            } catch (TimeoutException e) {
                System.out.println("Popup not displayed. Continuing test...");
            }

            // Wait for URL to confirm navigation
            wait.until(ExpectedConditions.urlContains("courses?coId="));

            // Smooth scroll demonstration
            scrollsmooth(22, 58, 300);

            // ---> RETURN TO PREVIOUS PAGE FOR NEXT ITERATION <---
            driver.navigate().back();

            // Wait for the original page/dropdown trigger to reload
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//span[contains(@class,'dropdown-selected')])[1]")
            ));
        }
    }
    @Test
    public void jeecentres() throws InterruptedException {
        // Step 1: Select the course "JEE"
        WebElement courseDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//span[@class='dropdown-selected !font-noto'])[1]")));
        action.moveToElement(courseDropdown).click().perform();

        // Wait for dropdown options and select "jee"
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector(".dropdown-item"), 2));
        driver.findElements(By.cssSelector(".dropdown-item")).stream()
                .filter(course -> "jee".equalsIgnoreCase(course.getText().trim()))
                .findFirst()
                .ifPresent(WebElement::click);

        // Step 2: Open the Centre dropdown once
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='css-785691'])[1]"))).click();

        // Step 3: Extract all city names
        List<WebElement> initialCentres = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".css-yd53b5"))
        );
        List<String> cityNames = initialCentres.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        int centersCount = cityNames.size();
        System.out.println("Cities: " + cityNames);

        for (int i = 0; i < centersCount; i++) {
            if (i > 0) {
                // Re-open dropdown for subsequent cities
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='css-785691'])[1]"))).click();
            }

            // Fetch the latest list of centres to avoid stale elements
            List<WebElement> currentCentres = wait.until(
                    ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".css-yd53b5"))
            );

            WebElement targetCityElement = currentCentres.get(i);
            String targetCity = cityNames.get(i);
            System.out.println("Processing city: " + targetCity);

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", targetCityElement);

            // Click the city element
            try {
                wait.until(ExpectedConditions.elementToBeClickable(targetCityElement)).click();
            } catch (ElementClickInterceptedException e) {
                js.executeScript("arguments[0].click();", targetCityElement);
            }

            // Generate URL matching patterns
            String cityLower = targetCity.toLowerCase();

            // Wait for URL to contain either hyphenated or stripped version of city name
            // Wait for URL to contain city name or hyphenated name
            // Assume cityLower is already defined before the try block
            try {
                new WebDriverWait(driver, Duration.ofSeconds(60)).until(driver -> {
                    String url = driver.getCurrentUrl().toLowerCase();
                    String hyphenCity = cityLower.replaceAll("\\s+", "-");
                    String strippedCity = cityLower.replaceAll("[^a-z0-9]", "");
                    return url.contains(strippedCity) || url.contains(hyphenCity);
                });
            } catch (TimeoutException e) {
                System.out.println("Timeout waiting for URL to contain: " + targetCity);
                // Additional debugging or fallback actions
            }

            // Wait for page load completion
            wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

            System.out.println("Verified URL: " + driver.getCurrentUrl());

            // Perform smooth scroll or other actions
            scrollsmooth(20, 100, 100);
        }

        // Optional: wait at the end
        Thread.sleep(2000);
    }
    @Test
    public void nacst() throws InterruptedException {
       driver.findElement(By.xpath("(//p[@class='chakra-text css-u93ii4'])[1]")).click();
        window_switch();
       scrollsmooth(28,150,100);
       driver.findElement(By.cssSelector("[href='javascript:void(0);']")).click();
       //scrollsmooth(24, -150,100);
       ///Registration is currently not available so close328732
//       WebElement namefield =driver.findElement(By.cssSelector("[id='name']"));
//       wait.until(ExpectedConditions.visibilityOf(namefield));
//       namefield.sendKeys("PUNIT KUMAR");
//        WebElement emailfield =driver.findElement(By.cssSelector("[id='email']"));
//        wait.until(ExpectedConditions.visibilityOf(emailfield));
//       emailfield.sendKeys("punitautomationtesting@gmail.com");
//        WebElement mobilefield =driver.findElement(By.cssSelector("[id='mobile']"));
//        wait.until(ExpectedConditions.visibilityOf(mobilefield));
//        mobilefield.sendKeys("7700820345");
//        WebElement otp_button =driver.findElement(By.cssSelector("[id='otp_button']"));
//        wait.until(ExpectedConditions.visibilityOf(otp_button));
//        otp_button.click();
//        //Thread.sleep(20000);
//        WebElement otpField = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(By.id("otp")));
//        otpField.click();
//
//        Thread.sleep(20000);// Enter OTP manually
//
//        WebElement validateBtn = wait.until(
//                ExpectedConditions.elementToBeClickable(By.id("validate")));
//
//        validateBtn.click();

    }
    @Test
    public void LiveClasses() throws InterruptedException {
         driver.findElement(By.xpath("(//p[@class='chakra-text css-u93ii4'])[2]")).click();
         window_switch(); // this function use for the window switch
         scrollsmooth(50, 150,100);
         scrollsmooth(50, -150,100);
         driver.findElement(By.cssSelector("[id='name']")).sendKeys("PUNIT KUMAR");
         driver.findElement(By.cssSelector("[id='father_name']")).sendKeys("ASHOK BHAGAT");
         driver.findElement(By.cssSelector("[id='email']")).sendKeys("punitnanda@gmail.com");
         selectfromdropdown(By.cssSelector("[id='class']"),"X");
         selectfromdropdown(By.cssSelector("[id='howdoyouKnow']"),"Youtube");
         driver.findElement(By.id("mobile")).sendKeys("7700820378");
         driver.findElement(By.id("get_otp")).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(By.cssSelector("[class='swal2-actions']")));
            button.click();
        } catch (TimeoutException e) {
            System.out.println("Button did not appear.");
            driver.findElement(By.id("otp")).click();
            Thread.sleep(Long.parseLong("15000"));
            driver.findElement(By.xpath("(//button[@class='common_btn get_otp'])[2]")).click();
        }

    }

    @Test
    public void Blogs() throws InterruptedException {
       moree("Blogs");
        window_switch();
        // first scroll the 1to 20 page but at the 20 page next button is invisible so scroll up fill the from
        int totalPages = 20;

        for (int page = 1; page <= totalPages; page++) {

            scrollsmooth(53, 150, 10);

            if (page < totalPages) {
                wait.until(ExpectedConditions.elementToBeClickable(
                        By.cssSelector(".next.page-numbers"))).click();
            } else {
                scrollsmooth(53, -150, 20);
            }
        }
        // blog page from
         selectfromdropdown(By.id("state"),"MADHYA PRADESH");
         selectfromdropdown(By.id("city"),"GWALIOR");
         selectfromdropdown(By.id("coaching_branch"),"GWALIOR");
         driver.findElement(By.id("name")).sendKeys("Punit Kumar");
         driver.findElement(By.id("email")).sendKeys("punitnadaa@gmail.com");
         driver.findElement(By.id("phone")).sendKeys("6203681030");
         selectfromdropdown(By.id("stream"),"IIT-JEE");
         selectfromdropdown(By.id("course"),"CLASS XII ONE YEAR PROGRAM");
         selectfromdropdown(By.id("classes"),"XII STUDY");
         driver.findElement(By.id("submit_btn")).click();
         driver.findElement(By.id("otp")).click();
         Thread.sleep(20000);
         driver.findElement(By.id("validate_otp_btn")).click();
         wait.until(ExpectedConditions.urlContains("https://www.narayanacoachingcenters.in/thankyou.php"));

    }
    @Test
    public void results() throws InterruptedException {
       moree("Results");
       scrollsmooth(30,100,150);
    }
    @Test
    public void neet_repeater() throws InterruptedException {
        moree("NEET-REPEATER");
       WebElement element = driver.findElement(By.cssSelector(".css-kp02cl"));
        js.executeScript("arguments[0].scrollIntoView({behavior:'smooth', block:'center'});", element);
        wait.until(ExpectedConditions.visibilityOf(element));
        driver.findElement(By.cssSelector("[id='field-:rh:']")).sendKeys("punit kumar");
        driver.findElement(By.cssSelector("[id='field-:ri:']")).sendKeys("asmit@gmail.com");
        selectfromdropdown(By.cssSelector("[id='field-:rj:']"),"RAIGANJ");
        selectfromdropdown(By.cssSelector("[id='field-:rk:']"),"NEET Repeater (2 Year)");
        driver.findElement(By.cssSelector("[id='field-:rl:']")).sendKeys("7700820345");
    }
    @Test
    public void centers_from() throws InterruptedException {
        wait.until(ExpectedConditions.urlContains("narayanacoachingcenters.in"));
        driver.findElement(By.xpath("(//button[@class='chakra-button shimmer-effect css-knmkwo'])[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='chakra-modal-:r1:']")));
        List<WebElement> citys = driver.findElements(By.cssSelector("[class='chakra-button css-yd53b5']"));
        for (WebElement city : citys) {
            String stringcity = city.getText();
            if (stringcity.equalsIgnoreCase("JHARKHAND")) {
                city.click();
                wait.until(ExpectedConditions.urlContains("jharkhand"));
                break;
            }
        }
        selectfromdropdown(By.cssSelector("Select.chakra-select"), "DHANBAD");
        wait.until(ExpectedConditions.urlContains("https://staging.narayanacoachingcenters.in/jharkhand/courses?coId=2&centerId=6"));
        scrollsmooth(8, 100, 50);
        clickElementInsideCard(
                "styles_container__CvGcZ",
                "XII JEE Advanced (MPC) - AITS-ONLINE [REGULAR] (1 Year)", // it change admission card
                "Explore Program"
        );
        By locenrollnoe = By.cssSelector("[type='button'][class='chakra-button css-1brw8s0']");
        // feature add wait url
        wait.until(ExpectedConditions.visibilityOfElementLocated(locenrollnoe))
                .click();
        By locphonenumber = By.cssSelector("[name='phone_number']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locphonenumber))
                .sendKeys("7700820345");
        By locheckbutton = By.cssSelector("[class='chakra-checkbox__control css-8ddrgo']");
        // feature add wait url
        wait.until(ExpectedConditions.visibilityOfElementLocated(locheckbutton))
                .click();
        By locotp = By.xpath("(//button[@type='submit'])[1]");
        // feature add wait url
        wait.until(ExpectedConditions.visibilityOfElementLocated(locotp))
                .click();
        Thread.sleep(25000);
        By locverifyotp = By.cssSelector("[type='button'][class='chakra-button css-1brw8s0']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(locverifyotp))
                .click();
    }

    @Test
    public void register() throws InterruptedException {
        //please use the new number
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[text()='Login/Register'])[1]"))).click();
        wait.until(ExpectedConditions.urlToBe("https://staging.narayanacoachingcenters.in/verify"));
        driver.findElement(By.cssSelector("[placeholder='9999 999 999']")).sendKeys("7700820345");// need new phone number
        driver.findElement(By.className("css-8ddrgo")).click();
        driver.findElement(By.className("css-1brw8s0")).click();
        driver.findElement(By.xpath("(//input[@type='number'])[1]")).click();
        Thread.sleep(20000);
        driver.findElement(By.cssSelector("[class='chakra-button css-1brw8s0']")).click();
        wait.until(ExpectedConditions.urlContains(
                "https://staging.narayanacoachingcenters.in/verify/"+phoneNumber));


/*888888888888888888888888888888888888888888888888888888888888888888888*/

//        driver.findElement(By.xpath("(//*[@name='full_name'])[1]")).sendKeys("punit kumar");
//        driver.findElement(By.cssSelector("[placeholder='eg. xxxx']")).sendKeys("Ashok Bhagat");
//        driver.findElement(By.cssSelector("div[class='css-k4xbc7'] input[placeholder='eg. test@example.com']"))
//                .sendKeys("punit.selenium@gmail.com");
//        driver.findElement(By.xpath("(//input[@placeholder='Enter Mobile Number'])[1]"))
//                .sendKeys("7700820345");
//        // THIS BUTTON BELONGS TO THE TRIGGER THE OTP
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='chakra-button css-6knctr'])[1]")))
//                .click();
//        // THIS BUTTON FOR THE CLICK ON THE FIELD OF THE OTP
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(8));
//        WebElement otp = wait1.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.xpath("(//input[@placeholder='Enter OTP'])[1]")
//                )
//        );
//        otp.click();
//        Thread.sleep(15000);
//        // THIS BUTTON FOR THE VERIFY THE OTP
//        driver.findElement(By.xpath("(//button[@class='chakra-button css-6knctr'])[1]")).click();
//
//        // THIS ARE USE TO THE SELECT FROM THE DROPDOWN BRANCH, STREAM , COURSE , CLASS
//        selectfromdropdown(By.xpath("(//select[@name='center'])[1]"), "PANJABI BAGH");
//        selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='stream']"), "IIT-JEE");
//        selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='course']"), "CLASS XII+ ONE YEAR PROGRAM");
//        // selectfromdropdown(By.cssSelector("div[class='css-k4xbc7'] select[name='class']"),"XII PASS");
//        // THIS FOR THE CHECK BUTTON
//        driver.findElement(By.cssSelector("div[class='css-k4xbc7'] span[class='chakra-checkbox__control css-8ddrgo']")).click();
//        // THIS FOR THE FINAL SUBMIT
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[normalize-space()='Submit'])[1]"))).click();


    }

    @AfterMethod
        public void close_window () throws InterruptedException {
        //Thread.sleep(10000);
            driver.quit();
        }

    }



