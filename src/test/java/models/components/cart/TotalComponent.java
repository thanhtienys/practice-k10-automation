package models.components.cart;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentCssSelector(".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By priceTableRowSel = By.cssSelector("table tr");
    private static final By priceTypeSel = By.cssSelector(".cart-total-left");
    private static final By priceValueSel = By.cssSelector(".cart-total-right");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories(){
        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowElem = findElements(priceTableRowSel);

        for (WebElement rowElem : priceTableRowElem) {

            WebElement priceTypeElem = rowElem.findElement(priceTypeSel);
            WebElement priceValueElem = rowElem.findElement(priceValueSel);

            String priceType = priceTypeElem.getText().replace(":", "").trim();
            double priceValue = Double.parseDouble(priceValueElem.getText().trim());

            priceCategories.put(priceType,priceValue);

        }

        return priceCategories;
    }
}
