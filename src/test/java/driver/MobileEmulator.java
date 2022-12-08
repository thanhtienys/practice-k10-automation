package driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;


import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MobileEmulator {

    public static WebDriver getMobileEmulator(String deviceName){

        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);


        ChromeOptions chromeOptions = new ChromeOptions();



        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,false);

        WebDriver driver = new ChromeDriver(chromeOptions);

        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5L));

        return driver;

    }

    public static WebDriver getMobile(){

        Map<String, Object> deviceMetrics = new HashMap<>();

        deviceMetrics.put("width", 1080);

        deviceMetrics.put("height", 2340);

        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceMetrics", deviceMetrics);

        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions(); chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        WebDriver driver = new ChromeDriver(chromeOptions);

        return driver;
    }
    public static void main(String[] args) {

        WebDriver driver = MobileEmulator.getMobileEmulator("iPhone SE");
        driver.navigate().refresh();
        driver.get("http://kiosk-admin.drimaesvn.com/");
       // driver.quit();

    }

}