package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCssSelector(".header-links")
public class HeaderLink extends Component {

    private static final By headerLinksElem = By.cssSelector("li a");

    public HeaderLink(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<WebElement> headerLinks(){
        return component.findElements(headerLinksElem);
    }
}
