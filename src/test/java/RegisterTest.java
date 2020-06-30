import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;

public class RegisterTest {

    private WebDriver driver;


    @Before
    public void fastStart(){
        System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        WebElement siteName = driver.findElement(By.cssSelector("#header > div > a > img.large"));
        Assert.assertTrue(siteName.isDisplayed());
    }

    @Test
    public void createAnAccount() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        WebElement accountElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-create > div > h1"));
        Assert.assertTrue(accountElement.isDisplayed());
        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span"));
        Assert.assertTrue(registerButton.isDisplayed());
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Bob");
        driver.findElement(By.cssSelector("#middlename")).sendKeys("Boby");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Andrei");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("ddasaaefsd@yahoo.com" );
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
        driver.findElement(By.cssSelector("#is_subscribed")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > ul > li > ul > li > span"));
        Assert.assertTrue(welcomeElement.isDisplayed());

    }


    @Test
    public void invalidEmail() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        WebElement accountElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-create > div > h1"));
        Assert.assertTrue(accountElement.isDisplayed());
        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span"));
        Assert.assertTrue(registerButton.isDisplayed());
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Bob");
        driver.findElement(By.cssSelector("#middlename")).sendKeys("Boby");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Andrei");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("ddasaaefsd@yahoocom" );
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
        driver.findElement(By.cssSelector("#is_subscribed")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement invalidEmail = driver.findElement(By.cssSelector("#advice-validate-email-email_address"));
        Assert.assertTrue(invalidEmail.isDisplayed());

    }

    @Test
    public void invalidPasswordTooWeak(){

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        WebElement accountElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-create > div > h1"));
        Assert.assertTrue(accountElement.isDisplayed());
        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span"));
        Assert.assertTrue(registerButton.isDisplayed());
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Bob");
        driver.findElement(By.cssSelector("#middlename")).sendKeys("Boby");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Andrei");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("ddasaaefsd@yahoo.com" );
        driver.findElement(By.cssSelector("#password")).sendKeys("12345");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("12345");
        driver.findElement(By.cssSelector("#is_subscribed")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement weakPassword = driver.findElement(By.cssSelector("#advice-validate-password-password"));
        Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", weakPassword.getText());

    }

    @Test
    public void passwordMatch() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        WebElement accountElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-create > div > h1"));
        Assert.assertTrue(accountElement.isDisplayed());
        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span"));
        Assert.assertTrue(registerButton.isDisplayed());
        driver.findElement(By.cssSelector("#firstname")).sendKeys("Bob");
        driver.findElement(By.cssSelector("#middlename")).sendKeys("Boby");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Andrei");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("ddasaaefsd@yahoo.com" );
        driver.findElement(By.cssSelector("#password")).sendKeys("1234511");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123451");
        driver.findElement(By.cssSelector("#is_subscribed")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement passwordMatch = driver.findElement(By.cssSelector("#advice-validate-cpassword-confirmation"));
        Assert.assertEquals("Please make sure your passwords match.", passwordMatch.getText());

    }

    @Test
    public void emptyField() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a")).click();
        WebElement accountElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-create > div > h1"));
        Assert.assertTrue(accountElement.isDisplayed());
        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button > span > span"));
        Assert.assertTrue(registerButton.isDisplayed());
        driver.findElement(By.cssSelector("#firstname")).sendKeys("");
        driver.findElement(By.cssSelector("#middlename")).sendKeys("Boby");
        driver.findElement(By.cssSelector("#lastname")).sendKeys("Andrei");
        driver.findElement(By.cssSelector("#email_address")).sendKeys("ddasaaefsd@yahoo.com" );
        driver.findElement(By.cssSelector("#password")).sendKeys("123451");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123451");
        driver.findElement(By.cssSelector("#is_subscribed")).click();
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement emptyField = driver.findElement(By.cssSelector("#advice-required-entry-firstname"));
        Assert.assertEquals("This is a required field.", emptyField.getText());

    }

    @After
    public void exit(){
        driver.quit();
    }
}
