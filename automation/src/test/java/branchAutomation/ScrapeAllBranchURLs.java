package branchAutomation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ScrapeAllBranchURLs {

    private static final String START_URL =
            "https://staging.narayanaschools.in/andhra-pradesh/vishakapatnam-kommadi";

    private static final By BRANCH_LINKS = By.cssSelector(
            "footer a.group.w-fit.relative.py-1.inline-block.focus\\:outline-none"
                    + ".cursor-pointer.disabled\\:cursor-none.text-\\[12px\\].xl\\:text-\\[16px\\].text-inherit"
    );

    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(START_URL);

            new WebDriverWait(driver, Duration.ofSeconds(20))
                    .until(d -> "complete".equals(
                            ((org.openqa.selenium.JavascriptExecutor) d)
                                    .executeScript("return document.readyState")));

            // Scroll to the footer to trigger any lazy-loaded content.
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");

            Thread.sleep(200);

            List<WebElement> urlBranches = driver.findElements(By.cssSelector(
                    "[class=\"group w-fit relative py-1 inline-block " +
                            "focus:outline-none cursor-pointer disabled:cursor-none " +
                            "text-[12px] xl:text-[16px] text-inherit\"]"
            ));

            System.out.println("Total Branch Elements Found: " + urlBranches.size());

            for (WebElement branch : urlBranches) {

                List<WebElement> branchLinks = branch.findElements(By.tagName("a"));

                for (WebElement branchLink : branchLinks) {

                    String url = branchLink.getAttribute("href");

                    System.out.println("Branch URL: " + url);
                }
            }







        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}