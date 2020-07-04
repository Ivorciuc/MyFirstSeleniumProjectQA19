import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CartTest {

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
        WebElement welcomeElement = driver.findElement(By.cssSelector(".hello strong"));
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());
        driver.findElement(By.cssSelector(".logo")).click();
    }

    @Test
    public void addToCart() {

        driver.findElement(By.cssSelector(".product-name a[title='Linen Blazer']")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-shop .h1"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("a[title='White'] .swatch-label")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button[title='Add to Cart']")).click();
        WebElement carCheck = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());

    }

    @Test
    public void removeFromCart(){

        driver.findElement(By.cssSelector(".product-name a[title='Linen Blazer']")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-shop .h1"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("a[title='White'] .swatch-label")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button[title='Add to Cart']")).click();
        WebElement carCheck = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());
        driver.findElement(By.cssSelector("#shopping-cart-table td:nth-child(6) a")).click();
        WebElement cartEmpty = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("SHOPPING CART IS EMPTY", cartEmpty.getText());

    }

    @Test
    public void fromCartToCheckout() throws InterruptedException {

        driver.findElement(By.cssSelector(".product-name a[title='Linen Blazer']")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-shop .h1"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("a[title='White'] .swatch-label")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button[title='Add to Cart']")).click();
        WebElement carCheck = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());
        Thread.sleep(2000);
        try {
            WebElement productQuantityCart = driver.findElement(By.cssSelector("[title='Qty']"));
            Assert.assertEquals(1, productQuantityCart.getText());
        } catch (AssertionError error) {
            WebElement productQuantityCart = driver.findElement(By.cssSelector("[title='Qty']"));
            productQuantityCart.click();
            productQuantityCart.clear();
            productQuantityCart.sendKeys("1");
            driver.findElement(By.cssSelector("[title='Update']")).click();
        }
        driver.findElement(By.cssSelector("#shopping-cart-totals-table + ul li button")).click();
        WebElement checkout = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("CHECKOUT", checkout.getText());

    }

        @Test
        public void reorder() throws InterruptedException {

        driver.findElement(By.cssSelector(".skip-account .label")).click();
        driver.findElement(By.cssSelector("#header-account a[title='My Account']")).click();
        driver.findElement(By.cssSelector(".main .current + li + li + li a")).click();
        WebElement myOrdersElement = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("MY ORDERS", myOrdersElement.getText());
        WebElement numberProduct = driver.findElement(By.cssSelector("tbody .first .number"));
        Assert.assertEquals("145000370", numberProduct.getText());
        driver.findElement(By.cssSelector(".nobr .separator + a")).click();
        Thread.sleep(2000);
        WebElement productInCart = driver.findElement(By.cssSelector(".product-cart-info .product-name a"));
        Assert.assertTrue(productInCart.isDisplayed());

        }

        @Test
        public void removeAllFromCart() throws InterruptedException {

        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".promos li:nth-child(3) img")).click();
        WebElement luggageElement1 = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertTrue(luggageElement1.isDisplayed());
        driver.findElement(By.cssSelector("#product-collection-image-370")).click();
        WebElement productName = driver.findElement(By.cssSelector(".product-name span"));
        Assert.assertEquals("ISLA CROSSBODY HANDBAG", productName.getText());
        WebElement cartButton = driver.findElement(By.cssSelector(".add-to-cart-buttons button[title='Add to Cart"));
        cartButton.click();
        driver.findElement(By.cssSelector(".logo")).click();
        driver.findElement(By.cssSelector(".promos li:nth-child(3) img")).click();
        driver.findElement(By.cssSelector("#product-collection-image-372")).click();
        driver.findElement(By.cssSelector(".add-to-cart-buttons button[title='Add to Cart")).click();
        WebElement productNameCart = driver.findElement(By.cssSelector(".success-msg span"));
        Assert.assertEquals("Flatiron Tablet Sleeve was added to your shopping cart.", productNameCart.getText());
        driver.findElement(By.cssSelector("[title='Empty Cart']")).click();
        WebElement emptyCart = driver.findElement(By.cssSelector(".page-title h1"));
        Assert.assertEquals("SHOPPING CART IS EMPTY", emptyCart.getText());
        
        }





    @After
    public void exit(){
        driver.quit();
    }



}
