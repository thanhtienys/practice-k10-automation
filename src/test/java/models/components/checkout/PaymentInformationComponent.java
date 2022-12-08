package models.components.checkout;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import test_data.credit.CreditCardType;

@ComponentCssSelector(value = "#opc-payment_info")
public class PaymentInformationComponent extends Component {

    private static final By creditCardDropdownSel = By.cssSelector("#CreditCardType");
    private static final By cardHolderNameSel = By.cssSelector("#CardholderName");
    private static final By cardHolderNumberSel = By.cssSelector("#CardNumber");
    private static final By expireMonthDropdownSel = By.cssSelector("#ExpireMonth");
    private static final By expireYearDropdownSel = By.cssSelector("#ExpireYear");
    private static final By cardCodeSel = By.cssSelector("#CardCode");

    private static final By continueBtnSel = By.cssSelector("input[class*='payment-info-next-step-button']");

    public PaymentInformationComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public void selectCreditCardType(CreditCardType creditCardType) {
        if (creditCardType == null) {
            throw new IllegalArgumentException("[ERR] Credit Card Type can't be null");
        }
        Select select = new Select(findElement(creditCardDropdownSel));
        switch (creditCardType) {
            case VISA:
                select.selectByVisibleText("Visa");
                break;
            case MASTER_CARD:
                select.selectByVisibleText("Master card");
                break;
            case DISCOVER:
                select.selectByVisibleText("Discover");
                break;
            case AMEX:
                select.selectByVisibleText("Amex");
                break;
        }
    }

    public void inputCardHolderName(String value) {
        findElement(cardHolderNameSel).sendKeys(value);
    }
    public void inputCardHolderNumber(String value) {
        findElement(cardHolderNumberSel).sendKeys(value);
    }

    public void inputExpireMonth(String month){
        Select select = new Select(findElement(expireMonthDropdownSel));
        select.selectByValue(month);
    }

    public void inputExpireYear(String year){
        Select select = new Select(findElement(expireYearDropdownSel));
        select.selectByValue(year);
    }
    public void inputCardCode(String value) {
        findElement(cardCodeSel).sendKeys(value);
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}
