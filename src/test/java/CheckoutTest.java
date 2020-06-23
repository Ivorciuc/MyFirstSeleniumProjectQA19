import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckoutTest {
    public void proceedToCheckout(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://testfasttrackit.info/selenium-test/");
        driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a > span.label")).click();
        driver.findElement(By.cssSelector("#header-account > div > ul > li.last > a")).click();
        driver.findElement(By.cssSelector("#email")).sendKeys("cosmin@fasttrackit.org");
        driver.findElement(By.cssSelector("#pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("#send2 > span > span")).click();
        driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-6.last > a")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(3) > div > div.actions > a")).click();
        driver.findElement(By.cssSelector("#swatch20 > span.swatch-label > img")).click();
        driver.findElement(By.cssSelector("#qty")).clear();
        driver.findElement(By.cssSelector("#swatch20 > span.swatch-label > img")).click();
        driver.findElement(By.cssSelector("#qty")).sendKeys("1");
        driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span")).click();
        driver.findElement(By.cssSelector("body > div > div.page > div.main-container.col1-layout > div > div > div.cart.display-single-price > div.cart-totals-wrapper > div > ul > li > button > span > span")).click();
        driver.findElement(By.cssSelector("#billing-buttons-container > button > span > span")).click();
        driver.findElement(By.cssSelector("#s_method_freeshipping_freeshipping")).click();
        driver.findElement(By.cssSelector("#shipping-method-buttons-container > button > span > span")).click();
        driver.findElement(By.cssSelector("#payment-buttons-container > button > span > span")).click();
        driver.findElement(By.cssSelector("#review-buttons-container > button > span > span")).click();
        driver.quit();

    }
}
