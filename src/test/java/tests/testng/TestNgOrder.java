package tests.testng;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgOrder {

    @Test(dependsOnMethods = {"testB"})
    public void testA(){
        System.out.println("TestA");
    }

    @Test()
    public void testB(){
        System.out.println("TestB");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 2, "[ERR] failed in the filed....");
        softAssert.assertEquals(1, 1);
        softAssert.assertAll();
        System.out.println("Hello");
    }
}