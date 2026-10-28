package branchAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class branch_from {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.narayanaschools.in/telangana/hyderabad-madhapur-basara-hostel");

        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Select Day Scholar / Residential
        WebElement typeDropdown = wait1.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[normalize-space()='Select Day Scholar / Residential' or normalize-space()='Day Scholar' or normalize-space()='Residential']")
                )
        );
        typeDropdown.click();

        try {
            wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Residential']"))).click();
        } catch (Exception e) {
            wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Day Scholar']"))).click();
        }

        // 2. Open Class dropdown
        WebElement classDropdown = wait1.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//span[normalize-space()='Select Class'] | (//div[contains(@class, 'px-[16px]')])[3]")
                )
        );
        classDropdown.click();

        // 3. Click "VII STUDY"
        WebElement option = wait1.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[normalize-space()='VII STUDY']")
                )
        );
        option.click();
    }
}
