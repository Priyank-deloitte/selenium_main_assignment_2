package Tests;

import Pages.Demo;
import org.openqa.selenium.By;
import org.testng.annotations.Test;



public class DemoTests extends Demo {
    @Test
    public void getTemperature() throws InterruptedException {
        String str = driver.findElement(By.xpath("//*[@id=\"temperature\"]")).getText();
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        // Check for negative sign; if it's there, set the isNeg flag
        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        // Process each character of the string;
        while (i < 2) {
            num *= 10;
            num += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg) {
            num = -num;
        }

        if (num > 30){
            driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/a/button")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/span")).click();
            Thread.sleep(3000);

        }
        else{

            driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/a/button")).click();
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/span")).click();
            Thread.sleep(3000);
        }

    }

}