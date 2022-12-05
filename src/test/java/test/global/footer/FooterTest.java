package test.global.footer;

import org.testng.Assert;
import org.testng.annotations.Test;
import test.test_flows.global.footer.FooterTestFlow;
import test.BaseTest;
import url.Urls;

public class FooterTest extends BaseTest {

    @Test
    public void testHomePageFooter() {

        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();

    }

    @Test
    public void testCategoryPageFooter() {

        driver.get(Urls.BASE_URL);
        Assert.fail();
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();

    }

    @Test
    public void testLoginPageFooter() {

        driver.get(Urls.BASE_URL);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyFooterComponent();

    }

}