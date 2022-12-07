//package driver;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class mobileEmulator {
//
//    public static WebDriver driver;
//
//    public static Map<String, String> deviceList(){
//
//        Map<String, String> deviceList = new HashMap<>();
//
//        return deviceList.get(deviceName)
//    }
//
//
//    public static WebDriver getDevice(){
//
//        mobileEmulation.put("deviceName", "Samsung Galaxy S8+");
//        mobileEmulation.put("deviceName", "iPhone SE");
//    }
//
//    public static void main(String[] args) {
//
//
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//
//        driver = new ChromeDriver(chromeOptions);
//        driver.get("https://kiosk-admin.drimaesvn.com/");
//    }
//
//    public void emulatorTest() throws InterruptedException {
//        Map<String, String> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceName", "iPhone 6");
//
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
//
//        driver = new ChromeDriver(chromeOptions);
//        driver.get("https://kiosk-admin.drimaesvn.com/");
//    }
//}