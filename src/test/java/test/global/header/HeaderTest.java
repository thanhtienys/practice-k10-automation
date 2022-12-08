package test.global.header;

import org.testng.annotations.Test;
import test.BaseTest;
import test_flows.global.header.HeaderTestFlow;
import url.Urls;

public class HeaderTest extends BaseTest {

    @Test
    public void verifyHeaderComponent(){

        driver.get(Urls.BASE_URL);
        HeaderTestFlow headerTestFlow = new HeaderTestFlow(driver);
        headerTestFlow.verifyHeaderComponent();
    }
}
