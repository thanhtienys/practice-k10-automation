package test.test_flows.global.header;

import models.components.global.header.HeaderComponent;
import models.components.global.header.HeaderLink;
import models.components.global.header.HeaderLogo;
import models.components.global.header.SearchBox;
import models.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeaderTestFlow {

    private final WebDriver driver;

    public HeaderTestFlow(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyHeaderComponent() {
        BasePage basePage = new BasePage(driver);
        HeaderComponent headerComponent = basePage.headerComp();
        HeaderLogo headerLogo = headerComponent.headerLogo();
        HeaderLink headerLink = headerComponent.headerLink();
        SearchBox searchBox = headerComponent.searchBox();

        verifyHeaderLogo(headerLogo);
        verifyHeaderLink(headerLink);
        verifySearchBox(searchBox);
    }

    private void verifySearchBox(SearchBox searchBox) {

        if (searchBox.itemListElem().isEmpty()){
            Assert.fail("[ERR] The search box no item to test");
        }

        Assert.assertEquals(searchBox.itemListElem().get(0).getAttribute("value") ,
                "Search store", "[ERR] Value display in search box mismatched");

        Assert.assertEquals(searchBox.itemListElem().get(1).getAttribute("value") ,
                "Search", "[ERR] Value display in search box mismatched");

    }

    private void verifyHeaderLink(HeaderLink headerLink) {

        List<String> expectedLinkTexts = Arrays.asList(
                "Register",
                "Log in",
                "Shopping cart(0)",
                "Wishlist(0)"
        );

        List<String> expectedHrefs = Arrays.asList(
                "https://demowebshop.tricentis.com/register",
                "https://demowebshop.tricentis.com/login",
                "https://demowebshop.tricentis.com/cart",
                "https://demowebshop.tricentis.com/wishlist"
        );

        List<String> actualLinkTexts = new ArrayList<>();
        List<String> actualHrefs = new ArrayList<>();

        if (headerLink.headerLinks().isEmpty()) {
            Assert.fail("[ERR] The header links no item to test");
        }

        for (WebElement link : headerLink.headerLinks()) {
            actualLinkTexts.add(link.getText().replaceAll("\\n", ""));
            actualHrefs.add(link.getAttribute("href"));
        }

        if (actualLinkTexts.isEmpty() || actualHrefs.isEmpty()) {
            Assert.fail("[ERR] Texts or hyperlinks is empty");
        }

        Assert.assertEquals(actualLinkTexts, expectedLinkTexts, "[ERR] Link text list is mismatched!");
        Assert.assertEquals(actualHrefs, expectedHrefs, "[ERR] Hyperlink list is mismatched!");

    }

    private void verifyHeaderLogo(HeaderLogo headerLogo) {

        String expectedHref = "https://demowebshop.tricentis.com/";
        String actualHref = headerLogo.headerLogoElem().getAttribute("href");

        if (!headerLogo.headerLogoElem().isDisplayed()) {
            Assert.fail("[ERR] The Header Logo has no item to test");
        }

        Assert.assertEquals(actualHref, expectedHref, "[ERR] Hyperlink is mismatched");

    }

}
