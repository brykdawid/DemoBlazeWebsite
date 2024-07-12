import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class DemoBlaze {
    //demoblaze.com

    @Test
    public void contactForm(){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try{
            driver.get("https://www.demoblaze.com/index.html");
            driver.findElement(By.linkText("Contact")).click();


        } catch (Exception e) {
            System.out.printf("FAIL");
        }
    }
}
