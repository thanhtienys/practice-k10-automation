package models.components.global.header;

import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


@ComponentCssSelector(".search-box")
public class SearchBox extends Component {

    private static final By itemElem= By.cssSelector("input");

    public SearchBox(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<WebElement> itemListElem(){
        return component.findElements(itemElem);
    }

}
