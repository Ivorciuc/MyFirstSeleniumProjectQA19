import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;

public class CheckoutTest {

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
    public void proceedToCheckout() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());
        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-6.last > a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(3) > div > h2 > a"));
        Assert.assertTrue(productElement.isDisplayed());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(3) > div > div.actions > a")).click();
        WebElement productDescription = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.short-description > div"));
        Assert.assertEquals("Modern, edgy, distinct. Choose from two colors.", productDescription.getText());
        driver.findElement(By.cssSelector("#swatch20 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertTrue(productColor.isDisplayed());
        driver.findElement(By.cssSelector("#qty")).clear();
        driver.findElement(By.cssSelector("#swatch20 > span.swatch-label > img")).click();
        driver.findElement(By.cssSelector("#qty")).sendKeys("1");
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span")).click();
        WebElement addToCartMessage = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Modern Murray Ceramic Vase was added to your shopping cart.", addToCartMessage.getText());
        try {
            WebElement productQuantity = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > input"));
            Assert.assertEquals(1, productQuantity.getText());
        } catch (AssertionError error) {
            WebElement productQuantity = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > input"));
            productQuantity.click();
            productQuantity.clear();
            productQuantity.sendKeys("1");
            driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > button > span > span")).click();
        }
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.cart-totals-wrapper > div > ul > li > button > span > span")).click();
        driver.findElement(By.cssSelector("#billing-buttons-container > button > span > span")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#shipping-method-buttons-container > button")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#payment-buttons-container > button")).click();
        Thread.sleep(2000);
        WebElement productName = driver.findElement(By.cssSelector("#checkout-review-table > tbody > tr > td:nth-child(1) > h3"));
        Assert.assertEquals("MODERN MURRAY CERAMIC VASE", productName.getText());
        driver.findElement(By.cssSelector("#review-buttons-container > button > span > span")).click();

    }

    @Test
    public void checkoutWithoutAcc() throws InterruptedException {

        try {
            driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
            WebElement logIn = driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a"));
            Assert.assertFalse(logIn.isDisplayed());
        } catch (AssertionError error){
            driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        }
        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-6.last > a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(2) > div > h2 > a"));
        Assert.assertTrue(productElement.isDisplayed());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(2) > div > h2 > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertEquals("DUMBO BOYFRIEND JEAN", productName.getText());
        driver.findElement(By.cssSelector("#swatch27 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("Blue", productColor.getText());
        driver.findElement(By.cssSelector("#swatch69 > span.swatch-label")).click();
        WebElement productSize = driver.findElement(By.cssSelector("#select_label_size"));
        Assert.assertEquals("26", productSize.getText());
        WebElement productQuantity = driver.findElement(By.cssSelector("#qty"));
        Assert.assertTrue(productQuantity.isDisplayed());
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span")).click();
        WebElement productNameCart = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("DUMBO Boyfriend Jean was added to your shopping cart.", productNameCart.getText());
        try {
            WebElement productQuantityCart = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > input"));
            Assert.assertEquals(1, productQuantityCart.getText());
        } catch (AssertionError error) {
            WebElement productQuantityCart = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > input"));
            productQuantityCart.click();
            productQuantityCart.clear();
            productQuantityCart.sendKeys("1");
            driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-actions > button > span > span")).click();
        }
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.cart-totals-wrapper > div > ul > li > button > span > span")).click();
        WebElement chckWithoutAcc = driver.findElement(By.cssSelector("#checkout-step-login > div > div.col-1 > h3"));
        Assert.assertEquals("CHECKOUT AS A GUEST OR REGISTER", chckWithoutAcc.getText());
        driver.findElement(By.cssSelector("#onepage-guest-register-button > span > span")).click();
        driver.findElement(By.cssSelector("#billing\\:firstname")).sendKeys("aaaa");
        driver.findElement(By.cssSelector("#billing\\:lastname")).sendKeys("aaaaaaa");
        driver.findElement(By.cssSelector("#billing\\:email")).sendKeys("aaaaaaa@yahoo.com");
        driver.findElement(By.cssSelector("#billing\\:street1")).sendKeys("Acasa 2");
        driver.findElement(By.cssSelector("#billing\\:city")).sendKeys("Acasa");
        Select dropDownBt = new Select (driver.findElement(By.cssSelector("#billing\\:region_id")));
        dropDownBt.selectByVisibleText("Alaska");
        driver.findElement(By.cssSelector("#billing\\:postcode")).sendKeys("55543");
        Select dropDownBt2 = new Select (driver.findElement(By.cssSelector("#billing\\:country_id")));
        dropDownBt2.selectByIndex(3);
        driver.findElement(By.cssSelector("#billing\\:telephone")).sendKeys("222323");
        driver.findElement(By.cssSelector("#billing-buttons-container > button")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#s_method_freeshipping_freeshipping")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#shipping-method-buttons-container > button > span > span")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#payment-buttons-container > button")).click();
        Thread.sleep(3000);
        WebElement productNameChck = driver.findElement(By.cssSelector("#checkout-review-table > tbody > tr > td:nth-child(1) > h3"));
        Assert.assertEquals("DUMBO BOYFRIEND JEAN", productNameChck.getText());
        driver.findElement(By.cssSelector("#review-buttons-container > button")).click();

    }

    @After
    public void exit(){
        driver.quit();
    }
}
