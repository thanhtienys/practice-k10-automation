package test.test_flows.global.block;


import models.components.global.block.BlockBoxComponent;
import models.components.global.block.Categories;
import models.components.global.block.SublistComponent;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.ArrayList;
import java.util.List;

public class BlockTestFlow {

    private final WebDriver driver;


    public BlockTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyBlockBoxComponent() {

        BasePage basePage = new BasePage(driver);
        Categories categories = basePage.blockComponent().categories();
        verifyBlockBox(categories);

    }

    public void verifySubListComponent(){
        BasePage basePage = new BasePage(driver);
        List<SublistComponent> sublistComponent = basePage.blockComponent().sublistComponent();

        verifySubList(sublistComponent);
    }

    private void verifySubList(List<SublistComponent> sublistComponent) {

        List<String> actualSubListTexts = new ArrayList<>();
        List<String> actualSubListLinks = new ArrayList<>();

        for (SublistComponent component : sublistComponent) {
            actualSubListTexts.add(component.sublistElem().getText());
            actualSubListLinks.add(component.sublistElem().getAttribute("href"));
        }

        System.out.println(actualSubListTexts);
        System.out.println(actualSubListLinks);
    }

    private void verifyBlockBox(BlockBoxComponent blockBoxComponent) {

        List<String> actualListBoxTexts = new ArrayList<>();
        List<String> actualListBoxLink = new ArrayList<>();

        String actualTitleTexts = blockBoxComponent.titleElem().getText();

        for (WebElement item : blockBoxComponent.listBoxElem()) {
            actualListBoxTexts.add(item.getText());
            actualListBoxLink.add(item.getAttribute("href"));
        }

        System.out.println(actualTitleTexts);
        System.out.println(actualListBoxTexts);
        System.out.println(actualListBoxLink);


    }


}
