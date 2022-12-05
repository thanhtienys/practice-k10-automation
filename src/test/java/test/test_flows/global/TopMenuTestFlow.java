package test.test_flows.global;

import models.components.global.TopMenuComponent;
import models.components.global.TopMenuComponent.MainCategoryItem;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopMenuTestFlow {

    private final WebDriver driver;

    public TopMenuTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyTopMenuComponent() {

        BasePage basePage = new BasePage(driver);
        TopMenuComponent topMenuComponent = basePage.topMenuComp();

        verifyMainItem(topMenuComponent);


    }
    //TODO
    private void verifySublistComputer(TopMenuComponent topMenuComponent ) {

        List<String> expectedLinkTextsSublist = Arrays.asList(
                "Desktops",
                "Notebooks",
                "Accessories"
        );

        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/desktops",
                "https://demowebshop.tricentis.com/notebooks"
                //"https://demowebshop.tricentis.com/accessories"
        );
       // verifySubListComputerItems()

    }



    private void verifyMainItem(TopMenuComponent topMenuComponent) {

        List<String> expectedLinkTexts = Arrays.asList(
                "BOOKS",
                "COMPUTERS",
                "ELECTRONICS",
                "APPAREL & SHOES",
                "DIGITAL DOWNLOADS",
                "JEWELRY",
                "GIFT CARDS"
        );

        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/books",
                "https://demowebshop.tricentis.com/computers",
                "https://demowebshop.tricentis.com/electronics",
                "https://demowebshop.tricentis.com/apparel-shoes",
                "https://demowebshop.tricentis.com/digital-downloads",
                "https://demowebshop.tricentis.com/jewelry",
                "https://demowebshop.tricentis.com/gift-cards"
        );
        //TODO
        List<String> expectedLinkTextsSublist = Arrays.asList(
                "Desktops",
                "Notebooks",
                "Accessories",
                "Camera, photo",
                "Cell phones"
        );

        verifyMainItemList(topMenuComponent, expectedLinkTexts, expectedHrefs, expectedLinkTextsSublist);

    }

    private void verifyMainItemList(
            TopMenuComponent topMenuComponent, List<String> expectedLinkTexts, List<String> expectedHrefs, List<String> expectedLinkTextsSublist) {

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();
        List<String> actualSublist = new ArrayList<>();


        if (topMenuComponent.mainCategoryItemList().isEmpty())
            Assert.fail("[ERR] The Top Menu has no item to test");

        for (MainCategoryItem link : topMenuComponent.mainCategoryItemList()) {
            actualLinkTexts.add(link.categoryItemLinkElem().getText().trim());
            actualHrefs.add(link.categoryItemLinkElem().getAttribute("href"));

            if (!link.sublistComponentList().isEmpty()){
                for (MainCategoryItem.SublistComponent sublist : link.sublistComponentList()) {
                    actualSublist.add(sublist.sublistCategoryItemLinkElem().getText().trim());
                }
            }
        }

        if (actualHrefs.isEmpty() || actualLinkTexts.isEmpty())
            Assert.fail("[ERR] Texts or hyperlink is empty in the footer column");

        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Link text list is mismatched!");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Hyperlink list is mismatched!");
        Assert.assertEquals(actualSublist, expectedLinkTextsSublist, "[ERR] Sublist is mismatched!");

    }


}
