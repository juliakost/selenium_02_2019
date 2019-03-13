package lesson11.task19.test;

import org.junit.Test;

public class AddToDeleteFromCart extends TestBase {

    @Test

    public void AddDeleteProduct() {

        app.mainPage.open();
        String num = app.mainPage.checkItemCountInCard();

        for (int i = 1; i <= 3; i++) {
            app.mainPage.openProduct();
            app.productPage.addItem(num);
            app.productPage.checkCartNumber(num);
            app.productPage.goToMainPage();
        }

        app.cartPage.open();
        app.cartPage.emptyCart();
    }
}
