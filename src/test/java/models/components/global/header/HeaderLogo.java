package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".header-logo")
public class HeaderLogo extends Component {

    private static final By headerLogoElem = By.tagName("a");

    public HeaderLogo(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement headerLogoElem(){
        return component.findElement(headerLogoElem);
    }

}
