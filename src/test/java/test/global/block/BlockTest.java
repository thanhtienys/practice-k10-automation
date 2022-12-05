package test.global.block;

import models.components.global.block.BlockComponent;
import org.testng.annotations.Test;
import test.BaseTest;
import test.test_flows.global.block.BlockTestFlow;
import url.Urls;

public class BlockTest extends BaseTest {

    @Test
    public void CategoriesTest() {

        driver.get(Urls.BASE_URL);
        BlockTestFlow blockTestFlow = new BlockTestFlow(driver);
        blockTestFlow.verifyBlockBoxComponent();
    }

    @Test
    public void SubListComputerTest() {

        driver.get(Urls.COMPUTERS);
        BlockTestFlow blockTestFlow = new BlockTestFlow(driver);
        blockTestFlow.verifySubListComponent();
    }

    @Test
    public void SubListElectronicsTest() {

        driver.get(Urls.ELECTRONICS);
        BlockTestFlow blockTestFlow = new BlockTestFlow(driver);
        blockTestFlow.verifySubListComponent();
    }
}
