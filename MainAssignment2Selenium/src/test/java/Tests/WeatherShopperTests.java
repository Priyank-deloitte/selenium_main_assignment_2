package Tests;

import Pages.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


public class WeatherShopperTests extends HomePage {
    ArrayList<String> brandName = new ArrayList<String>();
    ArrayList<String> sunScreensSPF50 = new ArrayList<String>();
    ArrayList<String> sunScreenSPF30 = new ArrayList<String>();
    ArrayList<String> aloe = new ArrayList<String>();
    ArrayList<String> almond = new ArrayList<String>();
    ArrayList<Integer> cost = new ArrayList<Integer>();
    ArrayList<Integer> sunScreensSPF50Price = new ArrayList<Integer>();
    ArrayList<Integer> sunScreensSPF30Price = new ArrayList<Integer>();
    ArrayList<Integer> aloePrice = new ArrayList<Integer>();
    ArrayList<Integer> almondPrice = new ArrayList<Integer>();
    ArrayList<String> price = new ArrayList<String>();


    public static Logger logger = LogManager.getLogger(WeatherShopperTests.class);



    int temperature = 0;
    @Test(priority = 1)
    public void getTemperature() {

        logger.info("Fetching the Temperature from the provided Website.");

        String str = driver.findElement(By.xpath("//*[@id=\"temperature\"]")).getText();
        int i = 0;

        boolean isNeg = false;

        // Check for negative sign; if it's there, set the isNeg flag
        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        // Process each character of the string;
        while (i < 2) {
            temperature *= 10;
            temperature += str.charAt(i++) - '0'; // Minus the ASCII code of '0' to get the value of the charAt(i++).
        }

        if (isNeg) {
            temperature = -temperature;
        }
    }

    @Test(priority = 2)
    public void selectCremeType() throws InterruptedException {

        logger.info("Selecting the type of the Creme based on the temperature.");

        if (temperature > 30){
            driver.findElement(By.xpath("/html/body/div/div[3]/div[2]/a/button")).click();
            takeScreenshot.screenshots(driver, "Sunscreens");
            Thread.sleep(3000);

        }
        else{

            driver.findElement(By.xpath("/html/body/div/div[3]/div[1]/a/button")).click();
            takeScreenshot.screenshots(driver, "Moisturizers");
            Thread.sleep(3000);
        }
    }
    @Test(priority = 3)
    public void readInstruction(){

        logger.info("Reading the instruction about the task from i button.");

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/span")).click();
        takeScreenshot.screenshots(driver, "Instruction");

    }

    @Test(priority = 4)
    public void chooseCreme(){

        logger.info("Picking the items to add them to the cart");

        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)","");
        for(int i = 1; i<4;i++) {
            brandName.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText());
            price.add(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[2]")).getText());
        }
        for(int i = 1; i<4;i++) {
            brandName.add(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/p[1]")).getText());
            price.add(driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/p[2]")).getText());
        }
        System.out.println(price);
        for(int i = 0 ; i <6 ; i++) {
            String str;
            // Replacing every non-digit number
            // with a space(" ")
            str = (price.get(i).replaceAll("[^\\d]", " "));
            // Remove extra spaces from the beginning
            // and the ending of the string
            str = str.trim();
            // Replace all the consecutive white
            // spaces with a single space
            str = str.replaceAll(" +", " ");
            cost.add(Integer.parseInt(str));
        }
        System.out.println(cost);
        String headers = driver.findElement(By.xpath("/html/body/div[1]/div[1]")).getText();
        logger.info(headers);
        System.out.println(headers);
        FindMinimumPrices min = new FindMinimumPrices();
        String minimumPriceBrand;
        if(headers.equals("Sunscreens")) {
            for (int i = 0; i < 6; i++) {

                if (brandName.get(i).contains("SPF-50") || brandName.get(i).contains("spf-50")) {
                    sunScreensSPF50.add(brandName.get(i));
                    sunScreensSPF50Price.add(cost.get(i));
                }
                if (brandName.get(i).contains("SPF-30") || brandName.get(i).contains("spf-30")) {
                    sunScreenSPF30.add(brandName.get(i));
                    sunScreensSPF30Price.add(cost.get(i));
                }

            }


            minimumPriceBrand = min.getMinimumBrand(sunScreensSPF30Price, sunScreenSPF30);
            System.out.println(minimumPriceBrand);
            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minimumPriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minimumPriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();
                }
            }

            logger.info(minimumPriceBrand);
            minimumPriceBrand = min.getMinimumBrand(sunScreensSPF50Price, sunScreensSPF50);
            System.out.println(minimumPriceBrand);
            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minimumPriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minimumPriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();
                }
            }
            logger.info(minimumPriceBrand);
        }
        else{
            for (int i = 0; i < brandName.size(); i++) {
                if (brandName.get(i).contains("Aloe") || brandName.get(i).contains("aloe")) {
                    aloe.add(brandName.get(i));
                    aloePrice.add(cost.get(i));
                }
                if (brandName.get(i).contains("Almond") || brandName.get(i).contains("almond")) {
                    almond.add(brandName.get(i));
                    almondPrice.add(cost.get(i));
                }
            }
            minimumPriceBrand = min.getMinimumBrand(aloePrice, aloe);
            System.out.println(minimumPriceBrand);
            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minimumPriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minimumPriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();
                }
            }
            logger.info(minimumPriceBrand);
            minimumPriceBrand = min.getMinimumBrand(almondPrice,almond);
            System.out.println(minimumPriceBrand);
            for(int i = 1; i<4;i++) {
                if(driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/p[1]")).getText().equals(minimumPriceBrand)){
                    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div["+i+"]/button")).click();
                }
            }
            for(int i = 1; i<4;i++) {
                if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[" + i + "]/p[1]")).getText().equals(minimumPriceBrand)) {
                    driver.findElement(By.xpath("/html/body/div[1]/div[3]/div["+i+"]/button")).click();
                }
            }
            logger.info(minimumPriceBrand);

        }
        takeScreenshot.screenshots(driver, "ItemAdded");

    }
    @Test(priority = 5)
    public void verifyCart(){

        logger.info("Verifying the cart after adding the right products.");

        driver.findElement(By.xpath("/html/body/nav/ul/button")).click();
        takeScreenshot.screenshots(driver, "CheckOrder");
    }
    @Test (priority = 6)
    public void paymentClick(){
        logger.info("Clicking on 'Pay with Card' to make the payment with the card.");

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/button")).click();
        takeScreenshot.screenshots(driver, "CardDetailsForm");
        driver.switchTo().frame(driver.findElement(By.xpath("/html/body/iframe")));
    }

    @Test(priority = 7)
    public void fillCardDetails() throws IOException {

        logger.info("Filling the details required to make the payment.");

        Properties prop = new Properties();
        FileInputStream obj = new FileInputStream("src/test/resources/Data.properties");
        prop.load(obj);
        String email = prop.getProperty("email");
        String cardNumber1 = prop.getProperty("cardNumber1");
        String cardNumber2 = prop.getProperty("cardNumber2");
        String cardNumber3 = prop.getProperty("cardNumber3");
        String cardNumber4 = prop.getProperty("cardNumber4");
        String month = prop.getProperty("month");
        String year = prop.getProperty("year");
        String cvv = prop.getProperty("cvv");
        String zipCode = prop.getProperty("zipCode");

        logger.info("Entering the email.");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(email);
        logger.info(email);

        logger.info("Entering Card Number.");
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardNumber1);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardNumber2);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardNumber3);
        driver.findElement(By.xpath("//*[@id=\"card_number\"]")).sendKeys(cardNumber4);
        logger.info(cardNumber1+cardNumber2+cardNumber3+cardNumber4);

        logger.info("Entering the expiry.");
        driver.findElement(By.xpath("//*[@id=\"cc-exp\"]")).sendKeys(month);
        driver.findElement(By.xpath("//*[@id=\"cc-exp\"]")).sendKeys(year);
        logger.info(month+"/"+year);

        logger.info("Entering the cvv.");
        driver.findElement(By.xpath("//*[@id=\"cc-csc\"]")).sendKeys(cvv);
        logger.info(cvv);

        logger.info("Entering the postal code");
        driver.findElement(By.xpath("//*[@id=\"billing-zip\"]")).sendKeys(zipCode);
        logger.info(zipCode);
        takeScreenshot.screenshots(driver, "filledDetails");
    }
    @Test(priority = 8)
    public void payToOrder() throws InterruptedException {

        logger.info("Clicking on 'Pay' button to make the payment.");
        driver.findElement(By.xpath("//*[@id=\"submitButton\"]")).click();
        Thread.sleep(3000);
        logger.info("Process Success!");
    }

}
