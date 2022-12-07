package models.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOptionsPage extends BasePage {

    private static final By checkoutAsGuest = By.cssSelector("input[class*=\"checkout-as-guest-button\"]");

    public CheckoutOptionsPage(WebDriver driver) {
        super(driver);
    }

    public void checkoutAsGuest(){
        findElement(checkoutAsGuest).click();
    }

}
