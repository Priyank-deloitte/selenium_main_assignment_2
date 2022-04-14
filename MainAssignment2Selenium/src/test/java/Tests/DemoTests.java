package Tests;

import Pages.Demo;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class DemoTests extends Demo {
    @Test
    public void getTemperature(){
        String x = driver.findElement(By.xpath("//*[@id=\"temperature\"]")).getText();
        System.out.println(x);

    }
}
