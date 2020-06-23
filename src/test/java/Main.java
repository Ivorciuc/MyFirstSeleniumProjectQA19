public class Main {


    public static void main(String[] args) {


       System.setProperty("webdriver.chrome.driver","resources/chromedriver.exe");
        LoginTest login = new LoginTest();
        login.login();
        login.invalidEmailLogin();
        login.invalidPasswordLogin();

        CartTest cart = new CartTest();
        cart.addToCart();
        cart.removeFromCart();
        cart.fromCartToCheckout();

        WishListTest wish = new WishListTest();
        wish.addToWishList();

        RegisterTest register = new RegisterTest();
        register.createAnAccount();

        CheckoutTest checkout = new CheckoutTest();
        checkout.proceedToCheckout();







    }


}
