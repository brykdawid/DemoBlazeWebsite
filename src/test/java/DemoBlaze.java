import javaautomation.FileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
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

    @Test
    public void productCards() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i <= 15; i++) {
            try {
                Random rand = new Random();
                int randomCategory;
                int randomPage;
                int randomProduct;
                int randomScenario;
                int randomScenario2;
                driver.get("https://www.demoblaze.com/index.html#");
                randomCategory = rand.nextInt(4);
                List<WebElement> categories = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='list-group'] //a[@class='list-group-item']")));
                switch (randomCategory) {
                    //leave it on ladning page
                    case 0:
                        WebElement homePageNext = driver.findElement(By.id("next2"));
                        WebElement homePagePrevious = driver.findElement(By.id("prev2"));
                        randomPage = rand.nextInt(2) + 1;
                        switch (randomPage) {
                            //next page
                            case 1:
                                homePageNext.click();
                                List<WebElement> productCardsNextPage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100'] //img[@class='card-img-top img-fluid']")));
                                System.out.printf(productCardsNextPage.size() + "\n");
                                randomProduct = rand.nextInt(productCardsNextPage.size());
                                productCardsNextPage.get(randomProduct).click();
                                break;
                            //previous page
                            case 2:
                                homePagePrevious.click();
                                List<WebElement> productCardsPreviousPage = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100'] //img[@class='card-img-top img-fluid']")));
                                System.out.printf(productCardsPreviousPage.size() + "\n");
                                randomProduct = rand.nextInt(productCardsPreviousPage.size());
                                productCardsPreviousPage.get(randomProduct).click();
                                break;


                        }
                        break;
                    //phones
                    case 1:
                        categories.get(0).click();
                        List<WebElement> phoneCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100'] //img[@class='card-img-top img-fluid']")));
                        System.out.printf(phoneCards.size() + "\n");
                        randomProduct = rand.nextInt(phoneCards.size());
                        phoneCards.get(randomProduct).click();
                        break;
                    //laptops
                    case 2:
                        categories.get(1).click();
                        List<WebElement> laptopCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100'] //img[@class='card-img-top img-fluid']")));
                        System.out.printf(laptopCards.size() + "\n");
                        randomProduct = rand.nextInt(laptopCards.size());
                        laptopCards.get(randomProduct).click();

                        break;
                    //monitors
                    case 3:
                        categories.get(2).click();
                        List<WebElement> monitorCards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='card h-100'] //img[@class='card-img-top img-fluid']")));
                        System.out.printf((monitorCards.size() + "\n"));
                        randomProduct = rand.nextInt(monitorCards.size());
                        monitorCards.get(randomProduct).click();

                        break;

                }

                WebElement productTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='name']")));
                System.out.printf(productTitle.getText() + "\n");
                WebElement priceContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='price-container']")));
                System.out.printf(priceContainer.getText() + "\n");
                WebElement productDesc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myTabContent")));
                System.out.printf(productDesc.getText() + "\n");

                WebElement addToCart = driver.findElement(By.linkText("Add to cart"));
                addToCart.click();
                wait.until(ExpectedConditions.alertIsPresent());
                driver.switchTo().alert().accept();

                driver.findElement(By.linkText("Cart")).click();

                randomScenario = rand.nextInt(2) + 1;
                switch (randomScenario) {
                    case 1:
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete"))).click();
                        System.out.printf("PRODUCT DELETED \n");
                        Thread.sleep(1000);
                        continue;
                    case 2:
                        System.out.printf("PLACE ORDER \n");
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Place Order']"))).click();
                        break;

                }
                String name = FileReader.getNextName();
                String country = FileReader.getNextCountry();
                String city = FileReader.getNextCity();
                String creditcard = FileReader.getNextCreditcard();
                String month = FileReader.getNextMonth();
                String year = FileReader.getNextYear();

                WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
                WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
                WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
                WebElement creditCardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card")));
                WebElement monthField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
                WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year")));

                nameField.sendKeys(name);
                countryField.sendKeys(country);
                cityField.sendKeys(city);
                creditCardField.sendKeys(creditcard);
                monthField.sendKeys(month);
                yearField.sendKeys(year);

                randomScenario2 = rand.nextInt(2) + 1;
                switch (randomScenario2) {
                    case 1:
                        System.out.printf("ORDER PLACED\n");
                        Thread.sleep(1500);
                        driver.findElement(By.xpath("//div[@id='orderModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@class='btn btn-primary']")).click();
                        WebElement receipt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sweet-alert")));
                        System.out.printf(receipt.getText() + "\n");
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("confirm")));
                        Thread.sleep(1000);
                        break;
                    case 2:
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='orderModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@data-dismiss='modal']"))).click();
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Delete"))).click();
                        System.out.printf("TRANSACTION ABORTED \n");
                        Thread.sleep(1000);
                        continue;


                }
                Thread.sleep(1000);


            } catch (Exception e) {
                System.out.printf("FAIL");
            }
        }
        driver.quit();
    }

    @Test
    public void registerLogin() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (int i = 0; i <= 15; i++)
            for (boolean repeatProcess = true; repeatProcess; ) {
                try {
                    Random rand = new Random();
                    int scenario;
                    int scenario2;
                    String name = FileReader.getNextUsername();
                    String pass = FileReader.getNextPassword();
                    driver.get("https://www.demoblaze.com/index.html");

                    WebElement signUp = driver.findElement(By.linkText("Sign up"));
                    signUp.click();

                    WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-username")));
                    WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-password")));
                    username.sendKeys(name);
                    password.sendKeys(pass);

                    WebElement close = driver.findElement(By.xpath("//div[@id='signInModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@class='btn btn-secondary']"));
                    WebElement confirm = driver.findElement(By.xpath("//div[@id='signInModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@class='btn btn-primary']"));

                    scenario = rand.nextInt(2) + 1;
                    switch (scenario) {
                        case 1:
                            close.click();
                            System.out.printf("SIGN UP CLOSE \n");
                            continue;
                        case 2:
                            confirm.click();
                            System.out.printf("SIGN UP CONTINUE \n");
                            break;
                    }

                    String alertText = wait.until(ExpectedConditions.alertIsPresent()).getText();
                    System.out.printf(alertText + "\n");
                    driver.switchTo().alert().accept();
                    if (alertText.equals("This user already exist.")) {
                        System.out.printf("USER ALREADY EXISTS, NEXT USER" + "\n");
                        continue;
                    }

                    WebElement login = driver.findElement(By.id("login2"));
                    login.click();

                    WebElement usernameLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
                    WebElement passwordLogin = wait.until(ExpectedConditions.elementToBeClickable(By.id("loginpassword")));

                    usernameLogin.sendKeys(name);
                    passwordLogin.sendKeys(pass);

                    WebElement closeLogin = driver.findElement(By.xpath("//div[@id='logInModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@class='btn btn-secondary']"));
                    WebElement confirmLogin = driver.findElement(By.xpath("//div[@id='logInModal'] //div[@class='modal-content'] //div[@class='modal-footer'] //button[@class='btn btn-primary']"));

                    scenario2 = rand.nextInt(2) + 1;
                    switch (scenario2) {
                        case 1:
                            closeLogin.click();
                            System.out.printf("LOGIN CLOSE \n");
                            continue;
                        case 2:
                            confirmLogin.click();
                            System.out.printf("LOGIN SUCCESSFUL \n");
                            break;
                    }
                    repeatProcess = false;

                } catch (Exception e) {
                    System.out.printf("FAIL");
                    throw new RuntimeException(e);
                }
            }
        driver.quit();
    }
}

