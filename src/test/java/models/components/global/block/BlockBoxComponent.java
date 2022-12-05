package models.components.global.block;


import models.components.Component;
import models.components.ComponentCssSelector;
import models.components.global.TopMenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import java.util.List;

public class BlockBoxComponent extends Component {

    private static final By tilteElem = By.cssSelector(".title");
    private static final By listBoxElem = By.cssSelector("li a");

    public BlockBoxComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }


    public WebElement titleElem() {
        return component.findElement(tilteElem);
    }

    public List<WebElement> listBoxElem() {
        return component.findElements(listBoxElem);
    }



}