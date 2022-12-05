package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import models.components.global.TopMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.TreeMap;

@ComponentCssSelector(value = ".header")
public class HeaderComponent extends Component {

    public HeaderComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public HeaderLogo headerLogo() {
        return findComponent(HeaderLogo.class, driver);
    }

    public HeaderLink headerLink(){
        return findComponent(HeaderLink.class, driver);
    }

    public SearchBox searchBox(){
        return findComponent(SearchBox.class, driver);
    }

    public void clickOnShoppingCartLink() {

        headerLink().headerLinks().get(2);
        scrollUpToElement(headerLink().headerLinks().get(2));
        headerLink().headerLinks().get(2).click();

    }



}