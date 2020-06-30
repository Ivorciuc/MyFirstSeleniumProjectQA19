import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;

public class LoginTest {

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
    public void login(){

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        Assert.assertTrue(welcomeElement.isDisplayed());
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());

    }

    @Test
    public void invalidEmailLogin(){

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage.getText());
        driver.findElement(By.cssSelector("#email")).sendKeys("Ivor@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement invalidEmail = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span"));
        Assert.assertTrue(invalidEmail.isDisplayed());


    }

    @Test
    public void invalidPasswordLogin(){

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage.getText());
        driver.findElement(By.cssSelector("#email")).sendKeys("cosmin@fasttrackit.org");
        driver.findElement(By.cssSelector("#pass")).sendKeys("12345");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement invalidEmail = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span"));
        Assert.assertTrue(invalidEmail.isDisplayed());

    }

    @Test
    public void forgottenPassword() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage.getText());
        driver.findElement(By.cssSelector("#login-form > div > div.col-2.registered-users > div.content.fieldset > ul > li:nth-child(3) > a")).click();
        WebElement passwordMessage = driver.findElement(By.cssSelector("#form-validate > div.fieldset > h2"));
        Assert.assertEquals("RETRIEVE YOUR PASSWORD HERE", passwordMessage.getText());
        driver.findElement(By.cssSelector("#email_address")).sendKeys("aaaaa@yahoo.com");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement emailWarning = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > ul > li > ul > li > span"));
        Assert.assertEquals("If there is an account associated with aaaaa@yahoo.com you will receive an email with a link to reset your password.", emailWarning.getText());

    }

    @Test
    public void fromForgotPassToLogin() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage1 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage1.getText());
        driver.findElement(By.cssSelector("#login-form > div > div.col-2.registered-users > div.content.fieldset > ul > li:nth-child(3) > a")).click();
        WebElement passwordMessage = driver.findElement(By.cssSelector("#form-validate > div.fieldset > p.form-instructions"));
        Assert.assertEquals("Please enter your email address below. You will receive a link to reset your password.", passwordMessage.getText());
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > p > a")).click();
        WebElement loginMessage2 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage2.getText());

    }

    @Test
    public void changePassword(){

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage1 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage1.getText());
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement welcomeElement1 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement1.getText());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-left.sidebar.col-left-first > div > div.block-content > ul > li:nth-child(2) > a")).click();
        WebElement editAccountMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > h1"));
        Assert.assertEquals("EDIT ACCOUNT INFORMATION", editAccountMessage.getText());
        driver.findElement(By.cssSelector("#current_password")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#change_password")).click();
        WebElement changePassMessage = driver.findElement(By.cssSelector("#form-validate > div:nth-child(2) > h2"));
        Assert.assertTrue(changePassMessage.isDisplayed());
        driver.findElement(By.cssSelector("#password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456");
        driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button")).click();
        WebElement informationMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > ul > li > ul > li > span"));
        Assert.assertEquals("The account information has been saved.", informationMessage.getText());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        WebElement loginMessage2 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.account-login > div > h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage2.getText());
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement welcomeElement2 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement2.getText());

    }


    @After
    public void exit(){
        driver.quit();
    }



}
