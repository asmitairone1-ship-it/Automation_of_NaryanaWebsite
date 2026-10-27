package narayanagroup;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.Set;

//import static jdk.internal.agent.Agent.getText;
import java.util.ArrayList;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

public class GROUP_WEBSITE {

    WebDriver driver;
    Actions actions;
    WebDriverWait wait;
    WebDriverWait waitbeforemethod;
    JavascriptExecutor js;
    WebDriverWait flexablewait;
    String phonenumber = "7700820345";//use in the enquary from and contact us
    String phonenumberjio = "6203681030";// use for the answer and keys solution in the home page
    String targetState = "ANDHRA PRADESH";
    String targetcity = "NELLORE";
    String targetbords ="SSC";
    String targetreservation="Day Scholar";
    String[] whyWearUniforms = {

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[1]/div[1]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[1]/div[2]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[1]/div[3]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[1]/div[4]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[2]/div[1]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[2]/div[2]",

            "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div[2]/div[3]"
    };

    @BeforeMethod
    public void openWebsite() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://narayanagroup.com/");
        //driver.get("https://staging.narayanagroup.com/");

        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitbeforemethod = new WebDriverWait(driver, Duration.ofSeconds(2));
        js = (JavascriptExecutor) driver;
        System.out.println("Website opened successfully");
        Thread.sleep(1000);
        try {
            waitbeforemethod
                    .withTimeout(Duration.ofSeconds(2))
                    .until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//img[@alt='close']")))
                    .click();
        } catch (TimeoutException e) {
            System.out.println("Popup not displayed");
        }

    }
    public void selectFromDropdown(List<WebElement> elements, String targetText, JavascriptExecutor js) {
        WebElement selected = elements.stream()
                .filter(el -> {
                    String text = el.getAttribute("innerText");
                    return text != null && text.trim().equalsIgnoreCase(targetText);
                })
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Option not found: " + targetText));

        String textBeforeClick = selected.getAttribute("innerText").trim();
        js.executeScript("arguments[0].click();", selected);
        System.out.println("Selected: " + textBeforeClick);
    }
    // function is make to scrioll thing again again
    public void scrollsmooth(int endnumber, int scrollnumber, int waittime) throws InterruptedException {
        for (int i = 0; i < endnumber; i++) {
            js.executeScript("window.scrollBy(0," + scrollnumber + ")");
            Thread.sleep(waittime);
        }
    }

    // this function is use for the wait time limits
    public void waitwait(int sec) {

        flexablewait = new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    @Test(enabled = true)
    public void testHomepageTitle() {

        String title = driver.getTitle();
        System.out.println("Page title: " + title);

        Assert.assertTrue(title.contains("Narayana"), "Title verification failed");
    }

    @Test(enabled = false)
    public void scrollfunction() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");

        System.out.println("Scrolled down by 1000 pixels");
    }

    @Test(enabled = true)
    public void ExperienceNarayana() throws InterruptedException {
        Thread.sleep(3000);
        // 1. Hover on Experience chevron/menu
        WebElement experience = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//img[@src='/optimised-images/icons/chevron-white-left.svg'])[1]")
                ));
        actions.moveToElement(experience).perform();
        Thread.sleep(500);
        // 2. Hover on About Us menu
        WebElement aboutus = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//a[@href='/about-us'])[1] | //span[text()='About Us']")
                ));
        actions.moveToElement(aboutus).perform();
        Thread.sleep(500);

        // 3. Hover on Our Management
        WebElement management = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[text()='Our Management']")
                ));
        actions.moveToElement(management).perform();

        // 4. Hover on Vision and Mission
        WebElement visionAndMission = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//span[normalize-space()='Vision and Mission']")
                ));
        actions.moveToElement(visionAndMission).perform();

        // 5. Re-fetch "About Us" fresh to avoid StaleElementReferenceException
        WebElement freshAboutUs = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='About Us']"))
        );
        js.executeScript("arguments[0].click();", freshAboutUs);

        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,-200);");

        driver.findElement(By.xpath("(//main/div/div/div/button)[2]")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,-1800);");
        driver.findElement(By.xpath("(//main/div/div/div/button)[3]")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,-2400);");
        driver.findElement(By.xpath("(//main/div/div/div/button)[4]")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,-2800);");
        Thread.sleep(600);
        driver.findElement(By.xpath("(//main/div/div/div/button)[5]")).click();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(600);
            js.executeScript("window.scrollBy(0,150);");
        }

        for (int i = 1; i <= 11; i++) {
            WebElement photo = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("[alt='Student " + i + "']")
                    )
            );
            actions.moveToElement(photo).perform();
        }
    }

    @Test(enabled = true)//done with otp
    public void contactus() throws InterruptedException {
        driver.findElement(By.cssSelector("[href='/contact-us']")).click();
        Thread.sleep(2000);
        js.executeScript("window.scroll(0,900);");
        driver.findElement(By.cssSelector("[placeholder='Enter full name']")).sendKeys("punit kumar");
        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']")).sendKeys(phonenumber);//phone number enter
        driver.findElement(By.xpath("//button[normalize-space()='Get OTP']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@placeholder='Enter OTP here']")).click();
        Thread.sleep(25000);
        driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
        driver.findElement(By.cssSelector("[placeholder='Enter email']")).sendKeys("punitnaagvanshi@gmail.com");
        driver.findElement(By.cssSelector("[placeholder='Enter message']")).sendKeys("automationtesting");
        driver.findElement(By.cssSelector("[type='checkbox']")).click();
        js.executeScript("window.scrollBy(0,500);");

        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        WebElement contactbutton = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        Assert.assertTrue(contactbutton.isEnabled(),
                "Submit button is not enabled");
        // Click submit button
        submitButton.click();

    }



    @Test(enabled = true)
    public void EnquireNowschool() throws InterruptedException {
        waitwait(40);
        driver.findElement(By.cssSelector("[href='/admission-enquiry']")).click();
        flexablewait.until(ExpectedConditions.urlContains("https://narayanagroup.com/admission-enquiry"));

        // 1. Scroll and open Academic Year dropdown
        WebElement yearDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='h-[48px] w-full py-2 px-3 xl:py-[12px] flex items-center xl:px-[16px] pr-10 rounded-[12px] border font-inter text-[14px] transition-all duration-150 border-[#E5E5E5] xl:border-[#D6D6D6] bg-white text-[#999] cursor-pointer  h-[48px]'])[1]")
        ));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", yearDropdown);
        Thread.sleep(800);
        yearDropdown.click();

        // 2. Select Academic Year (2026-2027 or 2025-2026)
        WebElement selectedYear = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(normalize-space(),'2027-2028') or contains(normalize-space(),'2026-2027')]")
        ));
        selectedYear.click();
        Thread.sleep(1000);

        // 2. Select State
        WebElement stateDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select state')]")
        ));
        js.executeScript("arguments[0].click();", stateDropdown);
        Thread.sleep(1500); // Wait for state dropdown menu to open
        List<WebElement> states =
                driver.findElements(By.cssSelector(".block.truncate"));
        selectFromDropdown(states,targetState,js);

//        WebElement selectedState = states.stream()
//                .filter(element -> {
//                    // innerText handles elements that may not yet be in the visible viewport
//                    String text = element.getAttribute("innerText");
//                    return text != null && text.trim().equalsIgnoreCase(targetState);
//                })
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException(targetState + " not found"));

// Capture text before clicking to prevent StaleElementReferenceException
//        String stateText = selectedState.getAttribute("innerText").trim();
//
//        js.executeScript("arguments[0].click();", selectedState);
//
//        System.out.println("Selected State: " + stateText);
        Thread.sleep(1500);

        // 3. Select City
        WebElement cityDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select city')]")
        ));
        js.executeScript("arguments[0].click();", cityDropdown);
        Thread.sleep(1500); // Fixed: Wait for city dropdown menu to open before findElements
        List<WebElement> citys = driver.findElements(By.cssSelector(".block.truncate"));
        selectFromDropdown(citys,targetcity,js);// only this line  select the city
        Thread.sleep(1500);

        // 4. Select Branch
        WebElement branchDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select branch')]")
        ));
        js.executeScript("arguments[0].click();", branchDropdown);
        Thread.sleep(1500);
        List<WebElement> branchs = driver.findElements(By.cssSelector(".block.truncate"));
        for (WebElement branch : branchs) {
            String branchText = branch.getText().trim();
            if (branchText.contains("Vinjamur")) {
                js.executeScript("arguments[0].click();", branch);
                System.out.println("Selected Branch: " + branchText);
                break;
            }
        }
        Thread.sleep(1500);

        // 5. Select Board
        WebElement boardDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select board')]")
        ));
        js.executeScript("arguments[0].click();", boardDropdown);
        Thread.sleep(2000); // Fixed: Wait for board dropdown menu to open before findElements
        List<WebElement> boards = driver.findElements(By.cssSelector(".block.truncate"));
//        for (WebElement board : boards) {
//            String boardText = board.getText().trim();
//            if (boardText.contains("State")) {
//                js.executeScript("arguments[0].click();", board);
//                System.out.println("Selected Board: " + boardText);
//                break;
//            }
//        }
        selectFromDropdown(boards,targetbords,js);
        Thread.sleep(2000);

        // 6. Select Reservation Type
        WebElement resDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select reservation type')]")
        ));
        js.executeScript("arguments[0].click();", resDropdown);
        Thread.sleep(1500); // Fixed: Wait for reservation dropdown menu to open before findElements
        List<WebElement> reservations = driver.findElements(By.cssSelector(".block.truncate"));
//        for (WebElement res : reservations) {
//            String resText = res.getText().trim();
//            if (resText.contains("Day Scholar")) {
//                js.executeScript("arguments[0].click();", res);
//                System.out.println("Selected Reservation: " + resText);
//                break;
//            }
//        }
        selectFromDropdown(reservations,targetreservation,js);
        Thread.sleep(1500);

        // 7. Select Class
        WebElement classDropdown = flexablewait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select class')]")
        ));
        js.executeScript("arguments[0].click();", classDropdown);
        Thread.sleep(1500);
        List<WebElement> classList = driver.findElements(By.cssSelector(".block.truncate"));
        for (WebElement element : classList) {
            String classText = element.getText().trim();
            if (classText.contains("E-CHAMPS-5") || classText.contains("5")) {
                js.executeScript("arguments[0].click();", element);
                System.out.println("Selected Class: " + classText);
                break;
            }
        }
        Thread.sleep(1500);

        // 8. Fill Details
        System.out.println("Step 9: Filling Student Details...");
        WebElement nameInput = flexablewait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[placeholder='Enter student name']")));
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", nameInput);
        Thread.sleep(500);
        nameInput.sendKeys("PUNIT KUMAR");
        driver.findElement(By.cssSelector("[placeholder='Enter parent email id']")).sendKeys("punit.kumar987@gmail.com");
        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']")).sendKeys(phonenumber);

        System.out.println("Step 10: Clicking Get OTP...");
        driver.findElement(By.xpath("//button[normalize-space()='Get OTP']")).click();

        // Wait for the OTP field to appear before clicking it
        WebElement otpInput = flexablewait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Enter OTP here']")));
        otpInput.click();

        System.out.println("Waiting 30 seconds for manual OTP entry...");
        Thread.sleep(30000);

        System.out.println("Step 11: Verifying OTP and Submitting...");
        driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
        Thread.sleep(1000);
        WebElement checkbox = driver.findElement(By.cssSelector("[type='checkbox']"));
        js.executeScript("arguments[0].click();", checkbox);
        WebElement enquiresubmitbutton = driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement enquirebutton = wait.until(ExpectedConditions.elementToBeClickable(enquiresubmitbutton));
        Assert.assertTrue(enquirebutton.isEnabled(), "Submit button is not enabled");
        js.executeScript("arguments[0].click();", enquiresubmitbutton);
        System.out.println("Form submitted successfully!");
    }


    @Test(enabled = true)
    public void homepage() throws InterruptedException {
        for (int i = 0; i < 42; i++) {
            js.executeScript("window.scrollBy(0,100);");
            Thread.sleep(150);
        }

        String courseNameJEE = "NEET"; //CBSE Class 10 , Andhra Pradesh State Class 10// only change the card name
        boolean found = false;

// Wait for cards — same selector as your working CBSE code
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector("[class='h-[264px] xl:h-[328px] relative rounded-2xl overflow-hidden']")
        ));

        Thread.sleep(1000);

// Get all cards
        List<WebElement> cards = driver.findElements(
                By.cssSelector("[class='h-[264px] xl:h-[328px] relative rounded-2xl overflow-hidden']")
        );

        System.out.println("Total cards found: " + cards.size());

        for (WebElement card : cards) {
            String text = card.getText();
            System.out.println("Card Text: " + text);

            if (text.toLowerCase().contains(courseNameJEE.toLowerCase())) {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
                Thread.sleep(1000);

                //  From HTML: Download is an <a> tag with href
                WebElement downloadLink = card.findElement(By.tagName("a"));

                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", downloadLink);
                Thread.sleep(800);

                //  JS click directly since it's an anchor tag
                js.executeScript("arguments[0].click();", downloadLink);

                System.out.println("Download clicked for: " + courseNameJEE);
                found = true;
                break;
            }
        }

//   If not found in visible cards, cycle through slider dots
        if (!found) {
            System.out.println("JEE Main not in visible cards, trying slider dots...");

            // Dot indicators inside div.px-6.rounded-[56px]
            List<WebElement> dots = driver.findElements(
                    By.cssSelector("div.px-6.rounded-\\[56px\\] > div")
            );

            System.out.println("Dots found: " + dots.size());

            for (int d = 0; d < dots.size(); d++) {
                js.executeScript("arguments[0].click();", dots.get(d));
                Thread.sleep(1500); // Wait for slide transition

                // Refresh cards after dot click
                List<WebElement> refreshedCards = driver.findElements(
                        By.cssSelector("div.h-\\[264px\\].xl\\:h-\\[328px\\].relative.rounded-2xl.overflow-hidden")
                );

                for (WebElement card : refreshedCards) {
                    String text = card.getText();
                    System.out.println("Card Text after dot " + d + ": " + text);

                    if (text.toLowerCase().contains(courseNameJEE.toLowerCase())) {
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
                        Thread.sleep(1000);

                        WebElement downloadLink = card.findElement(By.tagName("a"));
                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", downloadLink);
                        Thread.sleep(800);
                        js.executeScript("arguments[0].click();", downloadLink);

                        System.out.println("Download clicked for: " + courseNameJEE + " after dot: " + d);
                        found = true;
                        break;
                    }
                }

                if (found) break;
            }
        }

        if (!found) {
            System.out.println("WARNING: JEE Main card not found!");
        }

        //Scroll again new scroll method
        WebElement selectStateBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//span[contains(text(),'Select state')]")
        ));

        // Element ko screen ke center mein scroll karega (top se door)
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", selectStateBtn);
        Thread.sleep(800);

        // Select State
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Select state')]")
        )).click();

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
                .sendKeys(phonenumberjio);

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

        Thread.sleep(3000);

        WebElement solution = driver.findElement
                (By.xpath("(//img[@src='/optimised-images/icons/download.svg'])[2]"));
        wait.until(ExpectedConditions.elementToBeClickable(solution)).click();
        Thread.sleep(4000);

        // ===== WINDOW HANDLING START =====
        String parentWindow = driver.getWindowHandle();

        // Wait for new window to open
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        driver.close();
        driver.switchTo().window(parentWindow);
        // ===== WINDOW HANDLING END =====

        System.out.println("Successfully returned to parent window");
        driver.navigate().back();

        for (int i = 0; i < 12; i++) {
            js.executeScript("window.scrollBy(0,100);");
            Thread.sleep(150);
        }
    }

    @Test(enabled = true)
    public void Digital() throws InterruptedException {
            wait.until(ExpectedConditions.urlContains("narayana"));
            Thread.sleep(1000);
            // 1. Locate and hover on the Digital dropdown button
            WebElement digitalbutton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("(//img[@src='/optimised-images/icons/chevron-white-left.svg'])[2]")));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles: true}));",
                digitalbutton
        );
            // 2. Hover over nConnect
            WebElement nConnect = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[@href='/n-connect']")));
            actions.moveToElement(nConnect).perform();
            // 3. Re-locate nlearn right before clicking to prevent StaleElementReferenceException
          By nlearn = By.xpath("(//a[@href='/nlearn'])[2]");

           wait.until(
                ExpectedConditions.elementToBeClickable(nlearn)
        ).click();
            Thread.sleep(1000);
        for (int i = 0; i < 32; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(200);
        }
        WebElement visistnlearn = driver.findElement(By.cssSelector("[href='https://nlearn.nspira.in/']"));
        wait.until(ExpectedConditions.visibilityOf(visistnlearn)).click();
        String parentwindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        for (String web : windows) {
            if (web.equals(parentwindow)) {
                driver.switchTo().window(web);
            }
            break;
        }
        actions.moveToElement(digitalbutton).build().perform();
        Thread.sleep(4000);
        WebElement nConnects = driver.findElement(By.cssSelector("[href='/n-connect']"));
        actions.moveToElement(nConnects).build().perform();
        nConnects.click();
        //scroll properties remain
        driver.navigate().back();
    }

    @Test()
    public void Academics() throws InterruptedException {
        driver.findElement(By.xpath("(//a[@href='/academics'])[1]")).click();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Schools']")
        ));

        btn.click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,400);");
        driver.findElement(By.xpath("(//div/div/button)[7]")).click();
        Thread.sleep(5000);
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {

            if (window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }
        js.executeScript("window.scrollBy(0,-600);");
        driver.findElement(By.xpath("//button[normalize-space()='Junior Colleges']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[normalize-space()='Explore More'])[2]")
        )).click();
        Thread.sleep(4000);
        for (String window : allWindows) {

            if (window.equals(parentWindow)) {
                driver.switchTo().window(window);
            }
        }
        js.executeScript("window.scrollBy(0,-1250);");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Professional Colleges']")
        )).click();
        Thread.sleep(2000);
        String[] profficenalcollege = {
                "Gudur Engineering",
                "Nellore Engineering",
                "Nursing College",
                "Medical Institutions",
                "Pharmacy College",
                "Dental College"
        };

        for (int i = 0; i < profficenalcollege.length; i++) {

            String college = profficenalcollege[i];

            // click using alt attribute
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector("[alt='" + college + "']"))).click();

            Thread.sleep(4000);

            for (String window : allWindows) {

                if (window.equals(parentWindow)) {

                    // switch to child window
                    driver.switchTo().window(window);
                }
            }
        }
        js.executeScript("window.scrollBy(0,-2000);");
        driver.findElement(By.xpath("//button[normalize-space()='Institutes for civil services']")).click();
        Thread.sleep(2000);
        //++++++++++++++++++++++++++++++IAS website close  by the team ++++++++++++++++++++++++++++++++++++++++++
       /* wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@style='opacity: 1; will-change: opacity, transform; transform: none;'])[3]")
        )).click();
        Thread.sleep(4000);

        for (String window : allWindows) {

            if (window.equals(parentWindow)) {

                // switch to child window
                driver.switchTo().window(window);
            }
        }*/
        // ++++++++++++++++++++++++++++++IAS website close by the team ++++++++++++++++++++++++++++++++++++++++++
        js.executeScript("window.scrollBy(0,-2650);");
        driver.findElement(By.xpath("//button[normalize-space()='Coaching Centres']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@style='opacity: 1; will-change: opacity, transform; transform: none;'])[3]")
        )).click();
        Thread.sleep(4000);

        for (String window : allWindows) {

            if (window.equals(parentWindow)) {

                // switch to child window
                driver.switchTo().window(window);
            }
        }
    }

    @Test
    public void Uniforms() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href='/uniforms']")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,1000);");
        for (int row = 1; row <= 2; row++) {

            int maxCol = (row == 1) ? 4 : 3;

            for (int col = 1; col <= maxCol; col++) {

                String xpath =
                        "//*[@id='lenis-root']/main/section[1]/div[2]/div/div[3]/div["
                                + row + "]/div[" + col + "]";

                WebElement element =
                        driver.findElement(By.xpath(xpath));

                actions.moveToElement(element).perform();

                Thread.sleep(1500);
            }
        }
        js.executeScript("window.scrollTo(0,800)");
        driver.findElement(By.xpath("//*[@id='lenis-root']/main/section[3]/div[2]/div[1]/img")).click();
        WebElement rightbutton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='lenis-root']/main/section[3]/div[2]/div[7]/div/div[2]/button[2]/img")));
        for (int i = 0; i <= 5; i++) {
            rightbutton.click();
            Thread.sleep(1500);
        }
        driver.findElement(By.xpath("(//img[@alt='close'])[1]")).click();
        js.executeScript("window.scrollBy(0,-800)");
        driver.findElement(By.xpath("//*[@id='lenis-root']/main/section[2]/div[2]/div/div/button[2]")).click();
        js.executeScript("window.scrollBy(0,800)");
        driver.findElement(By.xpath("//*[@id='lenis-root']/main/section[3]/div[2]/div[1]/img")).click();
        WebElement rightbutton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@alt='scroll right']")));
        for (int i = 0; i <= 5; i++) {
            rightbutton1.click();
            Thread.sleep(1500);
        }
        driver.findElement(By.xpath("(//img[@alt='close'])[1]")).click();


    }

    @Test
    public void nsports() throws InterruptedException {

        driver.findElement(By.xpath("//*[@id='lenis-root']/div/div[2]/div/div/div[2]/div[5]/a/span")).click();
        for (int i = 0; i <= 20; i++) {
            js.executeScript("window.scrollBy(0,100)");
            Thread.sleep(100);
        }

    }

    @Test
    public void careers() throws InterruptedException {
        WebElement careerbutton = driver.findElement(By.xpath
                ("//*[@id='lenis-root']/div/div[2]/div/div/div[2]/div[7]/a/span"));
        wait.until(ExpectedConditions.visibilityOf(careerbutton)).click();
        js.executeScript("window.scrollBy(0,600)");
        driver.findElement(By.xpath("//*[contains(text(),'Learn More')]")).click();
        String windowngroup = driver.getWindowHandle();
        Set<String> allwindow = driver.getWindowHandles();
        for (String window : allwindow) {
            if (window.equals(windowngroup)) {
                Thread.sleep(2000);// this wait is for see the website is open
                driver.switchTo().window(window);
            }
        }
        driver.findElement(By.xpath("//*[@id='lenis-root']/main/div[2]/div/div[2]/div[2]/div[2]/button")).click();
        Set<String> allwindow1 = driver.getWindowHandles();
        for (String window : allwindow1) {
            if (window.equals(windowngroup)) {
                Thread.sleep(2000);// this wait is for see the website is open
                driver.switchTo().window(window);
            }
        }
        for (int i = 0; i <= 12; i++) {
            js.executeScript("window.scrollBy(0,50)");
            Thread.sleep(100);
        }
    }

    @Test
    public void centres() throws InterruptedException {

        driver.findElement(By.xpath("(//a[@href='/centers'])[1]")).click();
        js.executeScript("window.scrollBy(0,350);");
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//span[@class='relative group inline-block w-full'])[2]")).click();

        Thread.sleep(2000);

        List<WebElement> states = driver.findElements(
                By.cssSelector("[class='block truncate w-[90%]']"));

        for (WebElement state : states) {
            String mystate = state.getText();
            System.out.println(mystate);
            String myystate = "WEST BENGAL";
            if (mystate.equalsIgnoreCase(myystate)) {
                state.click();
                break;
            }
        }

        driver.findElement(By.xpath("(//span[@class='relative group inline-block w-full'])[3]")).click();
        Thread.sleep(2000);
        List<WebElement> citys = driver.findElements(By.cssSelector("[class='px-4 py-2 cursor-pointer flex items-center hover:bg-gray-100']"));
        for (WebElement city : citys) {
            String mycity = city.getText();
            System.out.println(mycity);
            if (mycity.equalsIgnoreCase("NADIA")) {
                city.click();

                break;
            }
        }

    }

    @Test
    public void Blogs() throws InterruptedException {
        // 1. Click the blog link when clickable
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#lenis-root a.w-\\[34px\\]"))).click();

        // 2. Wait for the blog list to be visible and get the total count
        By blogLocator = By.xpath("//span[contains(@class,'text-[#F68627]')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(blogLocator));
        List<WebElement> blogs = driver.findElements(blogLocator);
        int totalBlogs = blogs.size();
        System.out.println("Total blogs: " + totalBlogs);

        // Store listing URL for verification
        String listPageUrl = driver.getCurrentUrl();

        // 3. Click blog items sequentially
        for (int i = 1; i <= totalBlogs; i++) {
            try {
                // Dynamically locate the "Read More" button at index i
                By readMoreLocator = By.xpath("(//span[text()='Read More'])[" + i + "]");

                // Wait for the button to be in DOM, then scroll to it
                WebElement scrollTarget = wait.until(ExpectedConditions.presenceOfElementLocated(readMoreLocator));
                js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", scrollTarget);

                // Click using locator to dynamically find the element and prevent stale references
                wait.until(ExpectedConditions.elementToBeClickable(readMoreLocator)).click();

                // Wait for the detail page URL to load
                wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(listPageUrl)));

                // Capture the detail page HTML root before navigating back to check for staleness
                WebElement detailPageRoot = driver.findElement(By.tagName("html"));
                Thread.sleep(2000);
                // Navigate back
                driver.navigate().back();

                // Robust transition wait: Ensure detail page is destroyed and list page is restored
                wait.until(ExpectedConditions.stalenessOf(detailPageRoot));
                wait.until(ExpectedConditions.urlToBe(listPageUrl));
                wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));

            } catch (NoSuchSessionException e) {
                System.out.println("Session lost at blog index " + i + ", aborting remaining iterations");
                break;
            } catch (Exception e) {
                System.out.println("Error at blog index " + i + ": " + e.getMessage());
            }
        }
    }

    @Test
    public void gallery() {
        waitwait(5);

        // Step 1 — click gallery link
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[@href='/gallery'])[1]")
        )).click();

        wait.until(ExpectedConditions.urlContains("/gallery"));

        // Step 2 — instant scroll to trigger lazy load
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Step 3 — wait for all 6 cards
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("[class='text-[#2B2B2B] text-[18px] md:text-[24px] font-inter font-semibold text-center py-4']"), 5
        ));

        // Step 4 — scroll back to top
        js.executeScript("window.scrollTo(0, 0)");

        // Step 5 — collect titles once, reuse throughout (no re-fetching in loop)
        List<WebElement> cardsgallery = driver.findElements(By.cssSelector(
                "[class='text-[#2B2B2B] text-[18px] md:text-[24px] font-inter font-semibold text-center py-4']"
        ));

        int total = cardsgallery.size();
        if (total == 0) {
            System.out.println("[WARN] No gallery cards found!");
            return;
        }

        List<String> cardTitles = new ArrayList<>();
        for (WebElement card : cardsgallery) {
            cardTitles.add(card.getText().trim());
        }
        System.out.println("[INFO] Cards detected: " + cardTitles);

        for (int i = 0; i < total; i++) {
            String cardName = cardTitles.get(i);
            System.out.printf("%n[TEST %d/%d] Card: \"%s\"%n", i + 1, total, cardName);

            try {
                // Re-fetch only the specific card needed (avoids fetching all)
                List<WebElement> freshCards = driver.findElements(By.cssSelector(
                        "[class='text-[#2B2B2B] text-[18px] md:text-[24px] font-inter font-semibold text-center py-4']"
                ));

                WebElement target = freshCards.get(i);
                js.executeScript("arguments[0].scrollIntoView(true);", target);
                js.executeScript("arguments[0].click();", target);

                // Wait for navigation away from gallery
                wait.until(ExpectedConditions.not(
                        ExpectedConditions.urlContains("/gallery")
                ));
                System.out.println("  → URL   : " + driver.getCurrentUrl());
                System.out.println("  → Title : " + driver.getTitle());

                js.executeScript("window.history.back()");

                // Combined wait: URL back AND cards present in one condition
                wait.until(d -> d.getCurrentUrl().contains("/gallery") &&
                        !d.findElements(By.cssSelector(
                                "[class='text-[#2B2B2B] text-[18px] md:text-[24px] font-inter font-semibold text-center py-4']"
                        )).isEmpty()
                );

                System.out.printf("  [PASS] \"%s\"%n", cardName);

            } catch (Exception e) {
                System.out.printf("  [FAIL] \"%s\" → %s%n", cardName, e.getMessage());

                try {
                    js.executeScript("window.history.back()");
                    wait.until(ExpectedConditions.urlContains("/gallery"));
                } catch (Exception ignored) {
                }
            }
        }
    }


    @AfterMethod(enabled = true)
    public void closeTab() {
        driver.quit();
    }
}
