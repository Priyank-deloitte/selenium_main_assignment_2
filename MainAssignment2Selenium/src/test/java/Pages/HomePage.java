package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class HomePage {
    public static WebDriver driver;

    @BeforeTest
    public void configure() throws IOException {
        Properties obj = new Properties();
        FileInputStream input = new FileInputStream("src/test/resources/Data.properties");
        obj.load(input);
        String url = obj.getProperty("url");
        String path = obj.getProperty("driverPath");
        System.setProperty("webdriver.chrome.driver",path);
        driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.navigate().to(url);

    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
