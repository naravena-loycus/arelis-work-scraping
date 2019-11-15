package com.prodtv;



import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.testng.annotations.*;


import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private WebDriver driver;



    public void pageclick (){

    }

    @BeforeTest
    public void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/ChromeVersion78/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        //Navegador Headless
        //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver= new ChromeDriver(options);
        driver.get("https://empresite.eleconomista.es/Actividad/PRODUCCION-TV/provincia/MADRID/");
        //---

        //driver= new ChromeDriver();
    }


    public void a() throws InterruptedException {

        By olLabels= By.xpath("//ol/li");
        By txtTitle= By.xpath("//ol/li[1]/article/div/div/div/a");
        By email=By.xpath("//span[@class='email'][1]");


        List<WebElement> ol=driver.findElements(olLabels);
        // driver.findElement(txtTitle).click();

        /**
         * Rigorous Test :-)
         *
         */

        for (int i = 1; i <= 30; i++) {
            WebElement a = driver.findElement(By.xpath("//ol/li[" + i + "]/article/div[4]/button[1]"));

            Thread.sleep(5000);

            By l= By.xpath("//ol/li["+ i +"]/article/div/div/div/a");
            String titleBiz = driver.findElement(l).getText();
            // System.out.println(i + ": "+ titleBiz);
            Actions actions = new Actions(driver);

            actions.moveToElement(a).click().perform();
            a.click();
            Thread.sleep(5000);
            String emaail = null;
            try {
                emaail = driver.findElement(email).getText();
            }catch (NoSuchElementException e){
                emaail ="No encontrado";
            }
            System.out.println(i+". Empresa:  "+titleBiz+ "\n Email:  "+emaail);

            driver.navigate().back();
        }
    }


    @Test
    public void shouldAnswerWithTrue() throws InterruptedException {

        List<WebElement> num=driver.findElements(By.xpath("//div[6]/ul/li"));
        Thread.sleep(6000);
        for (int i = 2; i <= num.size(); i++) {
            WebElement a = driver.findElement(By.xpath("//div[6]/ul/li["+i+"]"));
            a();
            Actions actions = new Actions(driver);

            actions.moveToElement(a).click().perform();
            Thread.sleep(6000);
            a.click();
            Thread.sleep(6000);
            driver.navigate().back();
        }

    }

}
