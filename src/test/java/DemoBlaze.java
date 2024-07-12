import javaautomation.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class DemoBlaze {
    //demoblaze.com

    @Test
    public void contactForm() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            Random rand = new Random();
            int scenario;
            String email = FileReader.getNextEmail();
            String name = FileReader.getNextContactName();
            String msg = FileReader.getNextMessage();

            driver.get("https://www.demoblaze.com/index.html");
            driver.findElement(By.linkText("Contact")).click();

            WebElement contactEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recipient-email")));
            WebElement contactName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recipient-name")));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message-text")));

            WebElement close = driver.findElement(By.xpath("//button[text()='Close']"));
            WebElement sendMessage = driver.findElement(By.xpath("//button[text()='Send message']"));

            contactEmail.sendKeys(email);
            contactName.sendKeys(name);
            message.sendKeys(msg);

            scenario = rand.nextInt(2) + 1;
            switch (scenario) {
                case 1:
                    close.click();
                    driver.switchTo().alert().accept();
                    break;
                case 2:
                    sendMessage.click();
                    driver.switchTo().alert().accept();
                    break;
            }


        } catch (Exception e) {
            System.out.printf("FAIL");
        }
    }
}
