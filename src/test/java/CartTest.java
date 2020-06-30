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
        WebElement siteName = driver.findElement(By.cssSelector("#header > div > a > img.large"));
        Assert.assertTrue(siteName.isDisplayed());
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("aadriaan2@yahoo.com");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456789");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        WebElement welcomeElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div > div.welcome-msg > p.hello > strong"));
        Assert.assertTrue(welcomeElement.isDisplayed());
        Assert.assertEquals("Hello, Adrian Adriann!", welcomeElement.getText());
        driver.findElement(By.cssSelector("#header > div > a > img.large")).click();
    }

    @Test
    public void addToCart() {

        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > div.widget.widget-new-products > div.widget-products > ul > li:nth-child(2) > div > h3 > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("#swatch22 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81 > span.swatch-label")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        WebElement carCheck = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());

    }

    @Test
    public void removeFromCart(){

        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > div.widget.widget-new-products > div.widget-products > ul > li:nth-child(2) > div > h3 > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("#swatch22 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81 > span.swatch-label")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        WebElement carCheck = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());
        driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.a-center.product-cart-remove.last > a")).click();
        WebElement cartEmpty = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.page-title > h1"));
        Assert.assertEquals("SHOPPING CART IS EMPTY", cartEmpty.getText());

    }

    @Test
    public void fromCartToCheckout() throws InterruptedException {

        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > div.widget.widget-new-products > div.widget-products > ul > li:nth-child(2) > div > h3 > a")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertEquals("LINEN BLAZER", productName.getText());
        driver.findElement(By.cssSelector("#swatch22 > span.swatch-label > img")).click();
        WebElement productColor = driver.findElement(By.cssSelector("#select_label_color"));
        Assert.assertEquals("White", productColor.getText());
        driver.findElement(By.cssSelector("#swatch81 > span.swatch-label")).click();
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        WebElement carCheck = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Linen Blazer was added to your shopping cart.", carCheck.getText());
        Thread.sleep(2000);
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
        WebElement checkout = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-right-layout > div > div.col-main > div.page-title > h1"));
        Assert.assertEquals("CHECKOUT", checkout.getText());

    }

        @Test
        public void reorder() throws InterruptedException {

        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.first > a")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-left.sidebar.col-left-first > div > div.block-content > ul > li:nth-child(4) > a")).click();
        WebElement myOrdersElement = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col2-left-layout > div > div.col-main > div.my-account > div.page-title > h1"));
        Assert.assertEquals("MY ORDERS", myOrdersElement.getText());
        WebElement numberProduct = driver.findElement(By.cssSelector("#my-orders-table > tbody > tr.first.odd > td.number"));
        Assert.assertEquals("145000370", numberProduct.getText());
        driver.findElement(By.cssSelector("#my-orders-table > tbody > tr.first.odd > td.a-center.view.last > span > a.link-reorder")).click();
        Thread.sleep(2000);
        WebElement productInCart = driver.findElement(By.cssSelector("#shopping-cart-table > tbody > tr > td.product-cart-info > h2 > a"));
        Assert.assertTrue(productInCart.isDisplayed());

        }

        @Test
        public void removeAllFromCart(){

        driver.findElement(By.cssSelector("#header > div > a > img.large")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > ul > li:nth-child(3) > a > img")).click();
        WebElement luggageElement1 = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.page-title.category-title > h1"));
        Assert.assertTrue(luggageElement1.isDisplayed());
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(1) > div > div.actions > button > span > span")).click();
        WebElement productName = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-name > span"));
        Assert.assertEquals("ISLA CROSSBODY HANDBAG", productName.getText());
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button")).click();
        driver.findElement(By.cssSelector("#header > div > a > img.large")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.std > ul > li:nth-child(3) > a > img")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(2) > div > div.actions > button > span > span")).click();
        WebElement productNameCart = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > ul > li > ul > li > span"));
        Assert.assertEquals("Flatiron Tablet Sleeve was added to your shopping cart.", productNameCart.getText());
        driver.findElement(By.cssSelector("#empty_cart_button > span > span")).click();
        WebElement emptyCart = driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.page-title > h1"));
        Assert.assertEquals("SHOPPING CART IS EMPTY", emptyCart.getText());
        
        }





    @After
    public void exit(){
        driver.quit();
    }



}
