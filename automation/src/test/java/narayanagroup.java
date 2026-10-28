import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class narayanagroup {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://staging.narayanaschools.in/andhra-pradesh");

        // Close popup
       // driver.findElement(By.xpath("(//button/img[@alt='close'])[1]")).click();

        // Locate menu (Example locator — adjust if needed)
        WebElement experienceMenu =
                driver.findElement(By.xpath("//*[normalize-space()='Our Branches']"));
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement Menu = wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("By.xpath('//span[text()='Experience Narayana']/parent::a')")
       //
        // Hover action
       Thread.sleep(3000);
        Actions act = new Actions(driver);
        act.moveToElement(experienceMenu).perform();
        Thread.sleep(2000);

        List<WebElement> branches = driver.findElements(
                By.cssSelector(".flex.flex-row.items-start.gap-8.min-w-max")
        );

        System.out.println("Branches: " + branches.size());

        for (WebElement branch : branches) {
            List<WebElement> links = branch.findElements(By.tagName("a"));

            for (WebElement link : links) {
                System.out.println(link.getText());
                //System.out.println( link.getAttribute("href"));
            }
        }
//        WebElement aboutus =
//                driver.findElement(By.cssSelector("[href='/about-us#about']"));
//        act.moveToElement(aboutus).perform();
//        Thread.sleep(2000);
//        WebElement founder =
//                driver.findElement(By.cssSelector("[href='/about-us#founder']"));
//        act.moveToElement(founder).perform();
//        Thread.sleep(2000);
//        WebElement ourmangement =
//                driver.findElement(By.cssSelector("div div div a[href='/about-us#management']"));
//        act.moveToElement(ourmangement).perform();
//        Thread.sleep(4000);
//        WebElement VisionandMission =
//                driver.findElement(By.cssSelector("[href='/about-us#vision']"));
//        act.moveToElement( VisionandMission).perform();
//
//        experienceMenu.click();


    }
}