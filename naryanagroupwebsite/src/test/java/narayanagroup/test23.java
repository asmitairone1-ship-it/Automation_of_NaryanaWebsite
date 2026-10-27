//
//
//package narayanagroup;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.annotations.*;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import java.time.Duration;
//import java.util.List;
//import java.util.Set;
//
//import static java.lang.Thread.sleep;
//
//public class test23 {
//
//    private WebDriver driver;
//    private Actions actions;
//    private WebDriverWait wait;
//    private JavascriptExecutor js;
//
//    // Test Data
//    private static final String BASE_URL = "https://narayanagroup.com/";
//    private static final String PHONE_NUMBER = "7700820345";
//    private static final String PHONE_NUMBER_JIO = "6203681030";
//    private static final String TEST_EMAIL = "punitnaagvanshi@gmail.com";
//    private static final String STUDENT_NAME = "Punit Kumar";
//type="button"
//    @BeforeMethod
//    public void setup() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().deleteAllCookies();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//
//        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        actions = new Actions(driver);
//        js = (JavascriptExecutor) driver;
//
//        driver.get(BASE_URL);
//        System.out.println("✅ Website opened: " + BASE_URL);
//    }
//
//    @Test(priority = 1)
//    public void testExperienceNarayana() {
//        AboutUsPage aboutUs = new AboutUsPage(driver, wait, actions, js);
//        aboutUs.navigateToAboutUsMenu()
//                .exploreAboutUsButtons()
//                .viewStudentPhotos();
//    }
//
//    @Test(priority = 2)
//    public void testContactUs() {
//        ContactUsPage contactUs = new ContactUsPage(driver, wait, js);
//        contactUs.fillAndSubmitContactForm(PHONE_NUMBER, TEST_EMAIL);
//    }
//
//    @Test(priority = 3)
//    public void testSchoolEnquiry() {
//        SchoolEnquiryPage enquiry = new SchoolEnquiryPage(driver, wait, js);
//        enquiry.selectAcademicYear("2026-2027")
//                .selectState("PUNJAB")
//                .selectCity("BATHINDA")
//                .selectBranch("Bathinda")
//                .selectBoard("CBSE")
//                .selectReservation("Day Scholar")
//                .selectClass("E-CHAMPS-3")
//                .fillStudentDetails(STUDENT_NAME, TEST_EMAIL, PHONE_NUMBER)
//                .submitEnquiry();
//    }
//
//    @Test(priority = 4)
//    public void testHomepageEnquiry() {
//        HomepageEnquiryForm form = new HomepageEnquiryForm(driver, wait, js, actions);
//        form.selectCourse("CBSE Class 10")
//                .selectState("bihar")
//                .selectDistrict("darbhanga")
//                .fillFormDetails(PHONE_NUMBER_JIO, TEST_EMAIL)
//                .submitAndDownloadBrochure();
//    }
//
//    @Test(priority = 5)
//    public void testDigitalProducts() {
//        DigitalProductsPage digital = new DigitalProductsPage(driver, wait, actions, js);
//        digital.exploreNLearn()
//                .exploreNConnect();
//    }
//
//    @Test(priority = 6)
//    public void testAcademics() {
//        AcademicsPage academics = new AcademicsPage(driver, wait, js);
//        academics.exploreAllSections();
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
//
//// ========== PAGE OBJECT CLASSES ==========
//
//class AboutUsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//    private JavascriptExecutor js;
//
//    public AboutUsPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js) {
//        this.driver = driver;
//        this.wait = wait;
//        this.actions = actions;
//        this.js = js;
//    }
//
//    public AboutUsPage navigateToAboutUsMenu() {
//        hover("(//img[@src='/optimised-images/icons/chevron-white-left.svg'])[1]", "Experience Narayana");
//        hover("//span[text()='About Us']", "About Us");
//        hover("//span[text()='Founder']", "Founder");
//        hover("//span[text()='Our Management']", "Our Management");
//        hover("//span[normalize-space()='Vision and Mission']", "Vision and Mission");
//        return this;
//    }
//
//    public AboutUsPage exploreAboutUsButtons() {
//        driver.findElement(By.xpath("//span[text()='About Us']")).click();
//        scroll(-200);
//
//        int[] buttonPositions = {2, 3, 4, 5};
//        int[] scrollValues = {-1800, -2400, -2800, 0};
//
//        for (int i = 0; i < buttonPositions.length; i++) {
//            scroll(scrollValues[i]);
//            sleep(2000);
//            clickButton(buttonPositions[i]);
//            if (i < buttonPositions.length - 1) sleep(2000);
//        }
//        return this;
//    }
//
//    private void scroll(int scrollValue) {
//    }
//
//    public AboutUsPage viewStudentPhotos() {
//        scrollToStudents(10, 150, 600);
//        for (int i = 1; i <= 11; i++) {
//            WebElement photo = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.cssSelector("[alt='Student " + i + "']")));
//            actions.moveToElement(photo).perform();
//        }
//        return this;
//    }
//
//    private void hover(String xpath, String name) {
//        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        actions.moveToElement(element).perform();
//        System.out.println("👆 Hovered: " + name);
//    }
//
//    private void clickButton(int position) {
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(//main/div/div/div/button)[" + position + "]"))).click();
//    }
//
//    private void scrollToStudents(int iterations, int scrollAmount, int sleepTime) {
//        for (int i = 0; i < iterations; i++) {
//            scroll(scrollAmount);
//            sleep(sleepTime);
//        }
//    }
//}
//
//class ContactUsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private JavascriptExecutor js;
//
//    public ContactUsPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
//        this.driver = driver;
//        this.wait = wait;
//        this.js = js;
//        navigate();
//    }
//
//    private void navigate() {
//        driver.findElement(By.cssSelector("[href='/contact-us']")).click();
//        scroll(900);
//    }
//
//    private void scroll(int i) {
//    }
//
//    public void fillAndSubmitContactForm(String phone, String email) {
//        fillBasicDetails(phone);
//        handleOtp();
//        fillRemainingDetails(email);
//        submitForm();
//    }
//
//    private void fillBasicDetails(String phone) {
//        driver.findElement(By.cssSelector("[placeholder='Enter full name']")).sendKeys("Punit Kumar");
//        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']")).sendKeys(phone);
//        clickGetOtp();
//    }
//
//    private void clickGetOtp() {
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[normalize-space()='Get OTP']"))).click();
//        sleep(2000);
//    }
//
//    private void handleOtp() {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//input[@placeholder='Enter OTP here']"))).click();
//        sleep(20000); // Manual OTP entry time
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//button[normalize-space()='Verify OTP']"))).click();
//    }
//
//    private void fillRemainingDetails(String email) {
//        driver.findElement(By.cssSelector("[placeholder='Enter email']")).sendKeys(email);
//        driver.findElement(By.cssSelector("[placeholder='Enter message']")).sendKeys("automation testing");
//        driver.findElement(By.cssSelector("[type='checkbox']")).click();
//        scroll(500);
//    }
//
//    private void submitForm() {
//        driver.findElement(By.cssSelector("[type='submit']")).click();
//        System.out.println("✅ Contact form submitted");
//    }
//}
//
//class SchoolEnquiryPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private JavascriptExecutor js;
//
//    public SchoolEnquiryPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
//        this.driver = driver;
//        this.wait = wait;
//        this.js = js;
//        navigate();
//    }
//
//    private void navigate() {
//        driver.findElement(By.cssSelector("[href='/admission-enquiry']")).click();
//        scroll(1800);
//    }
//
//    private void scroll(int i) {
//    }
//
//    public SchoolEnquiryPage selectAcademicYear(String year) {
//        selectDropdownOption("//span[contains(text(),'Select academic year')]", year);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectState(String state) throws InterruptedException {
//        selectDropdownOption("//span[contains(text(),'Select state')]", state);
//        sleep(2000);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectCity(String city) throws InterruptedException {
//        selectDropdownOption("//span[contains(text(),'Select city')]", city);
//        sleep(2000);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectBranch(String branch) throws InterruptedException {
//        selectDropdownOption("//span[contains(text(),'Select branch')]", branch);
//        sleep(2000);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectBoard(String board) throws InterruptedException {
//        selectDropdownOption("//span[contains(text(),'Select board')]", board);
//        sleep(2000);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectReservation(String reservation) throws InterruptedException {
//        selectDropdownOption("//span[contains(text(),'Select reservation type')]", reservation);
//        sleep(2000);
//        return this;
//    }
//
//    public SchoolEnquiryPage selectClass(String className) throws InterruptedException {
//        driver.findElement(By.xpath("//span[text()='Select class']")).click();
//        List<WebElement> classList = driver.findElements(By.cssSelector(".block.truncate"));
//        for (WebElement element : classList) {
//            if (element.getText().trim().equals(className)) {
//                element.click();
//                break;
//            }
//        }
//        scroll(800);
//        sleep(1000);
//        return this;
//    }
//
//    public SchoolEnquiryPage fillStudentDetails(String name, String email, String phone) {
//        driver.findElement(By.cssSelector("[placeholder='Enter student name']")).sendKeys(name);
//        driver.findElement(By.cssSelector("[placeholder='Enter parent email id']")).sendKeys(email);
//        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']")).sendKeys(phone);
//        handleOtp();
//        driver.findElement(By.cssSelector("[type='checkbox']")).click();
//        return this;
//    }
//
//    public void submitEnquiry() {
//        driver.findElement(By.cssSelector("[type='submit']")).click();
//        System.out.println("✅ School enquiry submitted");
//    }
//
//    private void selectDropdownOption(String triggerXpath, String optionText) {
//        driver.findElement(By.xpath(triggerXpath)).click();
//        List<WebElement> options = driver.findElements(By.cssSelector(".block.truncate"));
//        for (int i = 0; i < options.size(); i++) {
//            options = driver.findElements(By.cssSelector(".block.truncate"));
//            if (options.get(i).getText().trim().equalsIgnoreCase(optionText)) {
//                options.get(i).click();
//                break;
//            }
//        }
//    }
//
//    private void handleOtp() throws InterruptedException {
//        driver.findElement(By.xpath("//button[normalize-space()='Get OTP']")).click();
//        sleep(2000);
//        driver.findElement(By.xpath("//input[@placeholder='Enter OTP here']")).click();
//        sleep(30000);
//        driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
//    }
//}
//
//class HomepageEnquiryForm {
//    private final WebDriver driver;
//    private WebDriverWait wait;
//    private final JavascriptExecutor js;
//    private Actions actions;
//    private final String parentWindow;
//
//    public HomepageEnquiryForm(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, Actions actions) {
//        this.driver = driver;
//        this.wait = wait;
//        this.js = js;
//        this.actions = actions;
//        scrollToBottom();
//        parentWindow = driver.getWindowHandle();
//    }
//
//    public HomepageEnquiryForm selectCourse(String courseName) throws InterruptedException {
//        List<WebElement> cards = driver.findElements(
//                By.cssSelector("[class='h-[264px] xl:h-[328px] relative rounded-2xl overflow-hidden']"));
//
//        for (WebElement card : cards) {
//            if (card.getText().toLowerCase().contains(courseName.toLowerCase())) {
//                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", card);
//                sleep(2000);
//                card.findElement(By.xpath(".//*[contains(text(),'Download')]")).click();
//                System.out.println("📥 Downloaded: " + courseName);
//                break;
//            }
//        }
//        scroll(1700);
//        return this;
//    }
//
//    private void scroll(int i) {
//    }
//
//    public HomepageEnquiryForm selectState(String state) throws InterruptedException {
//        selectOption("//span[contains(text(),'Select state')]", state);
//        sleep(1000);
//        return this;
//    }
//
//    public HomepageEnquiryForm selectDistrict(String district) {
//        selectOption("//span[contains(text(),'Select district')]", district);
//        return this;
//    }
//
//    public HomepageEnquiryForm fillFormDetails(String phone, String email) {
//        driver.findElement(By.cssSelector("[name='location']")).sendKeys("testingnagar near automation wali gali");
//        driver.findElement(By.cssSelector("[placeholder='Enter student name']")).sendKeys("Punit kumar");
//        driver.findElement(By.cssSelector("[placeholder='Enter mobile number']")).sendKeys(phone);
//        handleOtp();
//        driver.findElement(By.cssSelector("[placeholder='Enter email']")).sendKeys(email);
//        driver.findElement(By.cssSelector("[placeholder='Enter current school']")).sendKeys("selenium");
//        scroll(150);
//        return this;
//    }
//
//    public void submitAndDownloadBrochure() throws InterruptedException {
//        driver.findElement(By.xpath("//button[normalize-space()='Submit']")).click();
//        sleep(3000);
//
//        // Download brochure
//        driver.findElement(By.xpath("(//img[@src='/optimised-images/icons/download.svg'])[2]")).click();
//        handleNewWindow();
//        driver.navigate().back();
//        System.out.println("✅ Homepage enquiry completed");
//    }
//
//    private void selectOption(String triggerXpath, String optionText) {
//        driver.findElement(By.xpath(triggerXpath)).click();
//        List<WebElement> options = driver.findElements(
//                By.cssSelector("[class='px-4 py-2 cursor-pointer flex items-center hover:bg-gray-100']"));
//        for (WebElement option : options) {
//            if (option.getText().equalsIgnoreCase(optionText)) {
//                option.click();
//                break;
//            }
//        }
//    }
//
//    private void handleOtp() throws InterruptedException {
//        driver.findElement(By.xpath("//button[normalize-space()='Get OTP']")).click();
//        sleep(2000);
//        driver.findElement(By.xpath("//input[@placeholder='Enter OTP here']")).click();
//        sleep(20000);
//        driver.findElement(By.xpath("//button[normalize-space()='Verify OTP']")).click();
//    }
//
//    private void handleNewWindow() throws InterruptedException {
//        sleep(2000);
//        Set<String> allWindows = driver.getWindowHandles();
//        for (String window : allWindows) {
//            if (!window.equals(parentWindow)) {
//                driver.switchTo().window(window);
//                sleep(4000);
//                driver.close();
//                break;
//            }
//        }
//        driver.switchTo().window(parentWindow);
//    }
//
//    private void scrollToBottom() throws InterruptedException {
//        for (int i = 0; i < 42; i++) {
//            scroll(100);
//            sleep(150);
//        }
//    }
//}
//
//class DigitalProductsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private Actions actions;
//    private JavascriptExecutor js;
//    private String parentWindow;
//
//    public DigitalProductsPage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js) {
//        this.driver = driver;
//        this.wait = wait;
//        this.actions = actions;
//        this.js = js;
//        parentWindow = driver.getWindowHandle();
//        navigate();
//    }
//
//    private void navigate() {
//        WebElement digitalBtn = driver.findElement(By.xpath("(//img[@src='/optimised-images/icons/chevron-white-left.svg'])[2]"));
//        actions.moveToElement(digitalBtn).perform();
//    }
//
//    public DigitalProductsPage exploreNLearn() {
//        actions.moveToElement(driver.findElement(By.xpath("(//a[@href='/nlearn'])[2]"))).perform();
//        driver.findElement(By.xpath("(//a[@href='/nlearn'])[2]")).click();
//        scrollToBottom(32, 100, 200);
//
//        WebElement nLearnSite = wait.until(ExpectedConditions.elementToBeClickable(
//                By.cssSelector("[href='https://nlearn.nspira.in/']")));
//        nLearnSite.click();
//        handleWindowSwitch();
//        return this;
//    }
//
//    public DigitalProductsPage exploreNConnect() {
//        actions.moveToElement(driver.findElement(By.cssSelector("[href='/n-connect']"))).perform();
//        driver.findElement(By.cssSelector("[href='/n-connect']")).click();
//        driver.navigate().back();
//        return this;
//    }
//
//    private void handleWindowSwitch() throws InterruptedException {
//        sleep(4000);
//        Set<String> windows = driver.getWindowHandles();
//        for (String window : windows) {
//            if (window.equals(parentWindow)) {
//                driver.switchTo().window(window);
//                break;
//            }
//        }
//    }
//
//    private void scrollToBottom(int iterations, int scrollAmount, int sleepTime) {
//        for (int i = 0; i < iterations; i++) {
//            scroll(scrollAmount);
//            sleep(sleepTime);
//        }
//    }
//
//    private void scroll(int scrollAmount) {
//    }
//}
//
//class AcademicsPage {
//    private WebDriver driver;
//    private WebDriverWait wait;
//    private JavascriptExecutor js;
//    private String parentWindow;
//
//    public AcademicsPage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js) {
//        this.driver = driver;
//        this.wait = wait;
//        this.js = js;
//        parentWindow = driver.getWindowHandle();
//        navigate();
//    }
//
//    private void navigate() {
//        driver.findElement(By.xpath("(//a[@href='/academics'])[1]")).click();
//    }
//
//    public void exploreAllSections() {
//        exploreSchools();
//        exploreJuniorColleges();
//        exploreProfessionalColleges();
//        exploreCivilServices();
//        exploreCoachingCentres();
//    }
//
//    private void exploreCoachingCentres() {
//    }
//
//    private void exploreCivilServices() {
//    }
//
//    private void exploreProfessionalColleges() {
//    }
//
//    private void exploreSchools() {
//        clickSectionButton("//button[normalize-space()='Schools']");
//        scroll(400);
//        clickButton("(//div/div/button)[7]");
//        handleWindowSwitch();
//        scroll(-600);
//    }
//
//    private void handleWindowSwitch() {
//    }
//
//    private void clickButton(String s) {
//    }
//
//    private void scroll(int i) {
//    }
//
//    private void clickSectionButton(String s) {
//    }
//
//    private void exploreJuniorColleges() {
//        clickSectionButton("//button[normalize-space()='Junior Colleges']");
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("(
