package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Demo {
    public static WebDriver driver;
    @BeforeTest
    public WebDriver setup(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\priyankverma\\Downloads\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.navigate().to("https://weathershopper.pythonanywhere.com/");
        return driver;
    }
    @AfterTest
    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
