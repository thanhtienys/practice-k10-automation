package models.components.global;

import driver.DriverFactory;
import models.components.Component;
import models.components.ComponentCssSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import url.Urls;

import java.sql.Driver;
import java.util.List;

@ComponentCssSelector(value = ".top-menu")
public class TopMenuComponent extends Component {

    public TopMenuComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public List<MainCategoryItem> mainCategoryItemList() {
        return findComponents(MainCategoryItem.class, driver);
    }

    @ComponentCssSelector(".top-menu > li")
    public static class MainCategoryItem extends Component {

        public MainCategoryItem(WebDriver driver, WebElement component) {
            super(driver, component);
        }

        public WebElement categoryItemLinkElem() {
            return component.findElement(By.tagName("a"));
        }

        public List<SublistComponent> sublistComponentList() {
            Actions actions = new Actions(driver);
            actions.moveToElement(component).perform();
            return findComponents(SublistComponent.class, driver);
        }

        @ComponentCssSelector(".sublist li")
        public static class SublistComponent extends Component {

            public SublistComponent(WebDriver driver, WebElement component) {
                super(driver, component);
            }

            public WebElement sublistCategoryItemLinkElem(){
                return component.findElement(By.tagName("a"));
            }

        }

    }
}
