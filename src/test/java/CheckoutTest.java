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
        WebElement siteName = driver.findElement(By.cssSelector(".logo"));
        Assert.assertTrue(siteName.isDisplayed());
    }


    @Test
    public void proceedToCheckout() throws InterruptedException {

        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector(".hello strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());
        driver.findElement(By.cssSelector(".nav-primary .level0:nth-child(6) a")).click();
        WebElement productElement = driver.findElement(By.cssSelector(".product-name a[title='Modern Murray Ceramic Vase']"));
        Assert.assertTrue(productElement.isDisplayed());
        driver.findElement(By.cssSelector("#product-collection-image-437")).click();
        WebElement productDescription = driver.findElement(By.cssSelector(".short-description"));
        Assert.assertEquals("Modern, edgy, distinct. Choose from two colors.", productDescription.getText());
        driver.findElement(By.cssSelector("a[title='Black']")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertTrue(productColor.isDisplayed());
        driver.findElement(By.cssSelector("#qty")).clear();
        driver.findElement(By.cssSelector("#qty")).sendKeys("1");
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        WebElement addToCartMessage = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Modern Murray Ceramic Vase was added to your shopping cart.", addToCartMessage.getText());
        try {
            WebElement productQuantity = driver.findElement(By.cssSelector(".product-cart-actions .input-text"));
            Assert.assertEquals(1, productQuantity.getText());
        } catch (AssertionError error) {
            WebElement productQuantity = driver.findElement(By.cssSelector(".product-cart-actions  .input-text"));
            productQuantity.click();
            productQuantity.clear();
            productQuantity.sendKeys("1");
            driver.findElement(By.cssSelector("button[title='Update']")).click();
        }
        driver.findElement(By.cssSelector("#shopping-cart-totals-table + ul li button")).click();
        driver.findElement(By.cssSelector("#billing-buttons-container button")).click();
        Thread.sleep(4000);
        try {
            WebElement shippingButton = driver.findElement(By.cssSelector("#checkout-step-shipping_method button"));
            Assert.assertTrue(shippingButton.isDisplayed());
            shippingButton.click();
        } catch(AssertionError error){
            driver.findElement(By.cssSelector("#s_method_freeshipping_freeshipping")).click();
            driver.findElement(By.cssSelector("#checkout-step-shipping_method button[class='button validation-passed']")).click();
        }
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("#payment-buttons-container button")).click();
        Thread.sleep(2000);
        WebElement productName = driver.findElement(By.cssSelector("#checkout-review-table h3"));
        Assert.assertEquals("MODERN MURRAY CERAMIC VASE", productName.getText());
        driver.findElement(By.cssSelector("#review-buttons-container button")).click();

    }

    @Test
    public void checkoutWithoutAcc() throws InterruptedException {

        try {
            driver.findElement(By.cssSelector(".skip-account .label")).click();
            WebElement logIn = driver.findElement(By.cssSelector("a[title='Log In']"));
            Assert.assertTrue(logIn.isDisplayed());
        } catch (AssertionError error){
            driver.findElement(By.cssSelector("a[title='Log Out']")).click();
        }
        driver.findElement(By.cssSelector(".nav-primary .level0:nth-child(6) a")).click();
        WebElement productElement = driver.findElement(By.cssSelector("#product-collection-image-427"));
        Assert.assertTrue(productElement.isDisplayed());
        productElement.click();
        WebElement productName = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertEquals("DUMBO BOYFRIEND JEAN", productName.getText());
        driver.findElement(By.cssSelector("#swatch27 img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("Blue", productColor.getText());
        driver.findElement(By.cssSelector("#swatch69 .swatch-label")).click();
        WebElement productSize = driver.findElement(By.cssSelector("#select_label_size"));
        Assert.assertEquals("26", productSize.getText());
        WebElement productQuantity = driver.findElement(By.cssSelector("#qty"));
        Assert.assertTrue(productQuantity.isDisplayed());
        driver.findElement(By.cssSelector(".add-to-cart-buttons button")).click();
        WebElement productNameCart = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("DUMBO Boyfriend Jean was added to your shopping cart.", productNameCart.getText());
        try {
            WebElement productQuantityCart = driver.findElement(By.cssSelector(".product-cart-actions .input-text"));
            Assert.assertEquals(1, productQuantityCart.getText());
        } catch (AssertionError error) {
            WebElement productQuantityCart = driver.findElement(By.cssSelector(".product-cart-actions .input-text"));
            productQuantityCart.click();
            productQuantityCart.clear();
            productQuantityCart.sendKeys("1");
            driver.findElement(By.cssSelector("button[title='Update']")).click();
        }
        driver.findElement(By.cssSelector("#shopping-cart-totals-table + ul li button")).click();
        WebElement chckWithoutAcc = driver.findElement(By.cssSelector(".col-1 h3"));
        Assert.assertEquals("CHECKOUT AS A GUEST OR REGISTER", chckWithoutAcc.getText());
        driver.findElement(By.cssSelector("#onepage-guest-register-button")).click();
        driver.findElement(By.cssSelector("#billing-new-address-form  input[title='First Name']")).sendKeys("aaaa");
        driver.findElement(By.cssSelector("#billing-new-address-form  input[title='Last Name']")).sendKeys("aaaaaaa");
        driver.findElement(By.cssSelector("input[title='Email Address']")).sendKeys("aaaaaaa@yahoo.com");
        driver.findElement(By.cssSelector("#opc-billing input[title='Street Address']")).sendKeys("Acasa 2");
        driver.findElement(By.cssSelector("#opc-billing input[title='City']")).sendKeys("Acasa");
        Select dropDownBt = new Select (driver.findElement(By.cssSelector("#opc-billing select[title='State/Province']")));
        dropDownBt.selectByVisibleText("Alaska");
        driver.findElement(By.cssSelector("#opc-billing input[title='Zip/Postal Code']")).sendKeys("55543");
        Select dropDownBt2 = new Select (driver.findElement(By.cssSelector("#opc-billing select[title='Country']")));
        dropDownBt2.selectByIndex(3);
        driver.findElement(By.cssSelector("#opc-billing input[title='Telephone']")).sendKeys("222323");
        driver.findElement(By.cssSelector("#billing-buttons-container button")).click();
        Thread.sleep(4000);
        try {
            WebElement shippingButton = driver.findElement(By.cssSelector("#s_method_freeshipping_freeshipping"));
            Assert.assertTrue(shippingButton.isDisplayed());
            shippingButton.click();
            driver.findElement(By.cssSelector("#checkout-step-shipping_method button")).click();
        } catch(AssertionError error){
            driver.findElement(By.cssSelector("#checkout-step-shipping_method button")).click();
        }
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#payment-buttons-container button")).click();
        Thread.sleep(3000);
        WebElement productNameChck = driver.findElement(By.cssSelector("#checkout-review-table h3"));
        Assert.assertEquals("DUMBO BOYFRIEND JEAN", productNameChck.getText());
        driver.findElement(By.cssSelector("#review-buttons-container button")).click();

    }

    @After
    public void exit(){
        driver.quit();
    }
}
