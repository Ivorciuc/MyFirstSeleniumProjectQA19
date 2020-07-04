import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;

public class Features {

    private WebDriver driver;

    @Before
    public void fastStart() {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://testfasttrackit.info/selenium-test/");
        WebElement siteName = driver.findElement(By.cssSelector(".logo"));
        Assert.assertTrue(siteName.isDisplayed());

    }


    @Test
    public void searchBar(){

        driver.findElement(By.cssSelector("#search")).sendKeys("shirt");
        driver.findElement(By.cssSelector("button[title='Search']")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-name a[title='Core Striped Sport Shirt']"));
        Assert.assertEquals("CORE STRIPED SPORT SHIRT", productName.getText());
        WebElement productName1 = driver.findElement(By.cssSelector(".product-name a[title='Plaid Cotton Shirt']"));
        Assert.assertEquals("PLAID COTTON SHIRT", productName1.getText());

    }

    @Test
    public void deleteFromCart() throws InterruptedException {

        WebElement searchBar = driver.findElement(By.cssSelector("#search"));
        searchBar.sendKeys("glasses");
        WebElement searchButton = driver.findElement(By.cssSelector("button[title='Search']"));
        searchButton.click();
        WebElement productName1 = driver.findElement(By.cssSelector(".product-info a"));
        Assert.assertEquals("RETRO CHIC EYEGLASSES", productName1.getText());
        driver.findElement(By.cssSelector("#product-collection-image-339")).click();
        WebElement productName2 = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertTrue(productName2.isDisplayed());
        WebElement addToCartButton = driver.findElement(By.cssSelector(".add-to-cart-buttons button"));
        addToCartButton.click();
        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector("#search")).sendKeys("a tale of two cities");
        driver.findElement(By.cssSelector("button[title='Search']")).click();
        driver.findElement(By.cssSelector(".category-products ol a")).click();
        Thread.sleep(1000);
        WebElement productName3 = driver.findElement(By.cssSelector(".product-name a[title='A Tale of Two Cities']"));
        Assert.assertEquals("A TALE OF TWO CITIES", productName3.getText());
        driver.findElement(By.cssSelector("#product-collection-image-448")).click();
        WebElement productName4 = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertTrue(productName4.isDisplayed());
        driver.findElement(By.cssSelector("#links_20")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        driver.findElement(By.cssSelector(".first .a-center  a[title='Remove Item']")).click();
        try {
            WebElement afterDelete = driver.findElement(By.cssSelector(".main .product-name a[href='http://testfasttrackit.info/selenium-test/retro-chic-eyeglasses.html']"));
            Assert.assertTrue(afterDelete.isDisplayed());
        } catch ( NoSuchElementException error){
            System.out.println("Success!");
        }

    }

    @Test
    public void proceedToCheckout(){

        driver.findElement(By.cssSelector("#search")).sendKeys("a tale of two cities");
        driver.findElement(By.cssSelector("button[title='Search']")).click();
        driver.findElement(By.cssSelector(".category-products ol a")).click();
        WebElement productName3 = driver.findElement(By.cssSelector(".product-name a[title='A Tale of Two Cities']"));
        Assert.assertEquals("A TALE OF TWO CITIES", productName3.getText());
        driver.findElement(By.cssSelector("#product-collection-image-448")).click();
        WebElement productName4 = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertTrue(productName4.isDisplayed());
        driver.findElement(By.cssSelector("#links_20")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        WebElement checkCartProduct = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("A Tale of Two Cities was added to your shopping cart.", checkCartProduct.getText());
        driver.findElement(By.cssSelector("#shopping-cart-totals-table + ul li button")).click();

    }

    @Test
    public void sortByElement() throws InterruptedException {

        driver.findElement(By.cssSelector("#search")).sendKeys("slim");
        driver.findElement(By.cssSelector("button[title='Search']")).click();
        Select sortBy = new Select(driver.findElement(By.cssSelector(".sorter:first-of-type select:only-of-type")));
        sortBy.selectByVisibleText("Name");
        WebElement firstProductAlphabetically = driver.findElement(By.cssSelector(".product-name a[title='Bowery Chino Pants']"));
        Assert.assertEquals("BOWERY CHINO PANTS", firstProductAlphabetically.getText());

    }




    @After
    public void exit(){
        driver.quit();
    }

}
