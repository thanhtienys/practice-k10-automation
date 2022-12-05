package models.components.global.block;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@ComponentCssSelector(".listbox > ul > li > .sublist li ")
public class SublistComponent extends Component {

    public SublistComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public WebElement sublistElem(){
        return component.findElement(By.tagName("a"));
    }
}
