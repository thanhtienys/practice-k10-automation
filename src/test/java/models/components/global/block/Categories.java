package models.components.global.block;

import models.components.ComponentCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ComponentCssSelector(".block.block-category-navigation")
public class Categories extends BlockBoxComponent {

    public Categories(WebDriver driver, WebElement component) {
        super(driver, component);
    }


}
