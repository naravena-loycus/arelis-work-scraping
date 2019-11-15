package com.prodtv;



import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


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
        System.setProperty("webdriver.chrome.driver", "drivers/macos/chromedriver");
        ChromeOptions options= new ChromeOptions();
        //Navegador Headless
        options.addArguments("--headless");
        //options.addExtensions(new File("drivers/macos/AdBlock.crx"));
        //options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);
        driver.get("https://empresite.eleconomista.es/Actividad/PRODUCCION-TV/provincia/MADRID/");
        //---

        //driver= new ChromeDriver();
    }


    public void a() throws InterruptedException {

        By olLabels= By.xpath("//ol/li");
        By email=By.xpath("//span[@class='email'][1]");


        List<WebElement> ol=driver.findElements(olLabels);


        /**
         * Rigorous Test :-)
         *
         */

        for (int i = 1; i <= 30; i++) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            WebElement a = driver.findElement(By.xpath("//ol/li[" + i + "]/article/div[4]/button[1]"));


            By l= By.xpath("//ol/li["+ i +"]/article/div/div/div/a");
            String titleBiz = driver.findElement(l).getText();

            Actions actions = new Actions(driver);

            actions.moveToElement(a).click().perform();

            a.click();

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
        Thread.sleep(1000);
        for (int i = 2; i <= num.size(); i++) {
            WebElement b = driver.findElement(By.xpath("//div[6]/ul/li["+i+"]"));
            a();
            Thread.sleep(1000);
            driver.navigate().to("https://empresite.eleconomista.es/Actividad/PRODUCCION-TV/provincia/MADRID/PgNum-"+i+"/");
            Thread.sleep(1000);
        }

    }

}
