import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;

public class WishListTest {

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
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2")).click();
    }

    @Test
    public void addToWishList() throws InterruptedException {

        driver.findElement(By.cssSelector(".nav-primary .level0:nth-child(5) a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("h2 a[title='Racer Back Maxi Dress']"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("#product-collection-image-423")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertTrue(productName.isDisplayed());
        driver.findElement(By.cssSelector(".add-to-links a")).click();
        WebElement productNameWishList = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Racer Back Maxi Dress has been added to your wishlist. Click here to continue shopping.", productNameWishList.getText());

    }

    @Test
    public void removeFromWishList() throws AWTException, InterruptedException {

        driver.findElement(By.cssSelector(".nav-primary .level0:nth-child(5) a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("h2 a[title='Racer Back Maxi Dress']"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("#product-collection-image-423")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertTrue(productName.isDisplayed());
        driver.findElement(By.cssSelector(".add-to-links a")).click();
        Thread.sleep(2000);
        WebElement productNameWishList = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Racer Back Maxi Dress has been added to your wishlist. Click here to continue shopping.", productNameWishList.getText());
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[6]/a")).click();
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);

    }

    @Test
    public void addtoCartFromWishList() throws InterruptedException {

        driver.findElement(By.cssSelector(".nav-primary .level0:nth-child(5) a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("h2 a[title='Racer Back Maxi Dress']"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("#product-collection-image-423")).click();
        driver.findElement(By.cssSelector("#option78 span")).click();
        driver.findElement(By.cssSelector(".swatch-label img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("Purple", productColor.getText());
        driver.findElement(By.cssSelector("#option78 .swatch-label")).click();
        WebElement productSize = driver.findElement(By.cssSelector("#select_label_size"));
        Assert.assertEquals("L", productSize.getText());
        driver.findElement(By.cssSelector(".add-to-links a")).click();
        WebElement productNameWishList = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[2]/h3/a"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productNameWishList.getText());
        WebElement quantitySet = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[3]/div/div/input"));
        quantitySet.click();
        quantitySet.clear();
        quantitySet.sendKeys("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[5]/div[1]/button/span/span")).click();
        Thread.sleep(2000);
        WebElement productCart = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Racer Back Maxi Dress was added to your shopping cart.", productCart.getText());

    }

    @After
    public void exit(){
        driver.quit();
    }


}
