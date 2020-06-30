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
        WebElement siteName = driver.findElement(By.cssSelector("#header > div > a > img.large"));
        Assert.assertTrue(siteName.isDisplayed());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
    }

    @Test
    public void addToWishList() throws InterruptedException {

        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-5.parent > a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > h2"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > div.actions > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertTrue(productName.isDisplayed());
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > ul.add-to-links > li:nth-child(1) > a")).click();
        WebElement productNameWishList = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div.my-wishlist > ul > li > ul > li > span"));
        Assert.assertEquals("Racer Back Maxi Dress has been added to your wishlist. Click here to continue shopping.", productNameWishList.getText());

    }

    @Test
    public void removeFromWishList() throws AWTException, InterruptedException {

        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-5.parent > a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > h2"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > div.actions > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertTrue(productName.isDisplayed());
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > ul.add-to-links > li:nth-child(1) > a")).click();
        Thread.sleep(3000);
        WebElement productNameWishList = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div.my-wishlist > ul > li > ul > li > span"));
        Assert.assertEquals("Racer Back Maxi Dress has been added to your wishlist. Click here to continue shopping.", productNameWishList.getText());
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[6]/a")).click();
        Robot robot = new Robot();
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);

    }

    @Test
    public void addtoCartFromWishList() throws InterruptedException {

        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-5.parent > a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > h2"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productElement.getText());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > h2 > a")).click();
        driver.findElement(By.cssSelector("#swatch18 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertTrue(productColor.isDisplayed());
        driver.findElement(By.cssSelector("#swatch78 > span.swatch-label")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > ul.add-to-links > li:nth-child(1) > a")).click();
        WebElement productNameWishList = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[2]/h3/a"));
        Assert.assertEquals("RACER BACK MAXI DRESS", productNameWishList.getText());
        WebElement quantitySet = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[3]/div/div/input"));
        quantitySet.click();
        quantitySet.clear();
        quantitySet.sendKeys("1");
        driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div[2]/div[1]/form[1]/div/table/tbody/tr/td[5]/div[1]/button/span/span")).click();
        WebElement productCart = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Racer Back Maxi Dress was added to your shopping cart.", productCart.getText());

    }

    @After
    public void exit(){
        driver.quit();
    }


}
