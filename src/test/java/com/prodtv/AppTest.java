package com.prodtv;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


import java.io.*;
import java.util.List;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private WebDriver driver;
    private int wait = 20;

    private String getEmail(By email) {
        String s = null;
        try {
            s = driver.findElement(email).getText();
        }catch (NoSuchElementException ignored){
        }
        return s;
    }

    private int n=1;


    @BeforeTest
    public void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/windows/ChromeVersion78/chromedriver.exe");
        ChromeOptions options= new ChromeOptions();
        //Navegador Headless
        options.addArguments("--headless");
        //options.addExtensions(new File("drivers/macos/AdBlock.crx"));
       // options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);
        driver.get("https://empresite.eleconomista.es/Actividad/PRODUCCION-TV/provincia/MADRID/");

        //---

        //driver= new ChromeDriver();
    }


    //Tomar captura de pantalla con nombre
    private void takeScreenshoot(String nameScreenshoot) throws IOException {

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("screenshots/"+nameScreenshoot+".png"));
    }

    private void writeLineInFile(String s) throws IOException {

        try(FileWriter fw = new FileWriter("docs/arelisWork.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(s);
        } catch (IOException e) {
            System.out.println("algo va mal");
        }

    }

    private void scrapingWebBiz() throws InterruptedException, IOException {
        Thread.sleep(5000);
        By email=By.xpath("//span[@class='email'][1]");
        WebDriverWait WAIT = new WebDriverWait(driver,wait);

        for (int i = 1; i <= 30; i++) {


            WebElement btnVerEmpresa = driver.findElement(By.xpath("//ol/li[" + i + "]/article/div[4]/button[1]"));
            WAIT.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//ol/li[" + i + "]/article/div[4]/button[1]")));

            By l= By.xpath("//ol/li["+ i +"]/article/div/div/div/a");
            WAIT.until(ExpectedConditions.presenceOfElementLocated(l));
            String titleBiz = driver.findElement(l).getText();

            Actions actions = new Actions(driver);
            actions.moveToElement(btnVerEmpresa).click().perform();
            takeScreenshoot(titleBiz);
            Thread.sleep(3000);
            btnVerEmpresa.click();

            if(getEmail(email) != null) {
                    String line = n++ + ". Empresa:  " + titleBiz + "\t\t|\t Email:  " + getEmail(email);
                    writeLineInFile(line);
            }

            driver.navigate().back();
        }
    }


    @Test
    public void principalTest() throws InterruptedException, IOException {
        List<WebElement> num=driver.findElements(By.xpath("//div[6]/ul/li"));
        for (int i = 2; i <= num.size(); i++) {
            scrapingWebBiz();
            Thread.sleep(5000);
            driver.navigate().to("https://empresite.eleconomista.es/Actividad/PRODUCCION-TV/provincia/MADRID/PgNum-"+i+"/");
            Thread.sleep(5000);
        }

    }

}
