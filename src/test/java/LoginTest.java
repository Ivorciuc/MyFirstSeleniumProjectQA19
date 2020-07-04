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
        WebElement siteName = driver.findElement(By.cssSelector(".logo"));
        Assert.assertTrue(siteName.isDisplayed());
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();
        WebElement loginMessage = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage.getText());
    }

    @Test
    public void login(){

        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector(".hello strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());

    }

    @Test
    public void invalidEmailLogin(){

        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoocom");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement invalidEmail = driver.findElement(By.cssSelector(".validation-advice"));
        Assert.assertTrue(invalidEmail.isDisplayed());


    }

    @Test
    public void invalidPasswordLogin(){

        driver.findElement(By.cssSelector("#email")).sendKeys("cosmin@fasttrackit.org");
        driver.findElement(By.cssSelector("#pass")).sendKeys("12345");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement invalidPassword = driver.findElement(By.cssSelector("li span"));
        Assert.assertTrue(invalidPassword.isDisplayed());

    }

    @Test
    public void forgottenPassword() throws InterruptedException {

        driver.findElement(By.cssSelector("a[class='f-left']")).click();
        WebElement passwordMessage = driver.findElement(By.cssSelector(".fieldset h2"));
        Assert.assertEquals("RETRIEVE YOUR PASSWORD HERE", passwordMessage.getText());
        driver.findElement(By.cssSelector("#email_address")).sendKeys("aaaaa@yahoo.com");
        driver.findElement(By.cssSelector(".buttons-set .button")).click();
        WebElement emailWarning = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("If there is an account associated with aaaaa@yahoo.com you will receive an email with a link to reset your password.", emailWarning.getText());

    }

    @Test
    public void fromForgotPassToLogin() throws InterruptedException {

        driver.findElement(By.cssSelector("a[class='f-left']")).click();
        WebElement passwordMessage = driver.findElement(By.cssSelector(".form-instructions"));
        Assert.assertEquals("Please enter your email address below. You will receive a link to reset your password.", passwordMessage.getText());
        driver.findElement(By.cssSelector(".back-link a")).click();
        WebElement loginMessage2 = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage2.getText());

    }

    @Test
    public void changePassword() throws InterruptedException {

        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement welcomeElement1 = driver.findElement(By.cssSelector(".hello strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement1.getText());
        driver.findElement(By.cssSelector(".block-content ul li:nth-child(2)")).click();
        WebElement editAccountMessage = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("EDIT ACCOUNT INFORMATION", editAccountMessage.getText());
        driver.findElement(By.cssSelector("#current_password")).sendKeys("123456");
        driver.findElement(By.cssSelector("#change_password")).click();
        driver.findElement(By.cssSelector("#password")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#confirmation")).sendKeys("123456789");
        driver.findElement(By.cssSelector(".buttons-set .button")).click();
        WebElement informationMessage = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("The account information has been saved.", informationMessage.getText());
        WebElement accountButton = driver.findElement(By.cssSelector(".skip-account .label"));
        accountButton.click();
        driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();
        WebElement loginMessage2 = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("LOGIN OR CREATE AN ACCOUNT", loginMessage2.getText());
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement welcomeElement2 = driver.findElement(By.cssSelector(".hello strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement2.getText());

    }


    @After
    public void exit(){
        driver.quit();
    }



}
