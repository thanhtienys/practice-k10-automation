package test.global;

import org.testng.annotations.Test;
import test.BaseTest;
import test.test_flows.global.TopMenuTestFlow;
import url.Urls;

public class TopMenuTest extends BaseTest {

    @Test
    public void testHomePage(){

        driver.get(Urls.BASE_URL);

        TopMenuTestFlow topMenuTestFlow = new TopMenuTestFlow(driver);
        topMenuTestFlow.verifyTopMenuComponent();



    }

}
