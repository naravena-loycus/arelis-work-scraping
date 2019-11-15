package com.prodtv;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class BaseSelenium {

    private static WebDriver driver;


    public BaseSelenium (WebDriver driver){
        this.driver = driver;
    }

    //Conexión con Chrome
    public WebDriver chromeDriverConnection() {
        System.setProperty("webdriver.chrome.driver", "drivers/ChromeVersion78/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        //Navegador Headless
       //options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver= new ChromeDriver(options);

        //---

        //driver= new ChromeDriver();

        return driver;
    }


    // Buscar elemento
    public WebElement findElement (By locator){
        return driver.findElement(locator);
    }

    //Buscar elemento dentro de una lista
    public static List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    // Devolver el texto del elemento que paso por parametro
    public String getText (WebElement element){
        return element.getText();
    }

    // Devolver el texto del elemento que estanmos buscando a traves de ese Locator
    public String getText (By locator){
        return driver.findElement(locator).getText();
    }

    // Limpiar texto

    public void clearText (By locator){
        driver.findElement(locator).clear();
    }

    // Escribir texto
    public void sendKeys(String inputText, By locator){
        driver.findElement(locator).sendKeys(inputText);
    }

    //Click en un elemento
    public void clickInElement(By locator){
        driver.findElement(locator).click();
    }

    // Saber si un elemento esta desplegado
    public static Boolean isDisplayed(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e){
            return false;
        }
    }

    // Url de la Web a visitar
    public void getUrl(String Url){
        driver.get(Url);
    }


    //tomar Captura de pantalla
    public static void takeScreenShot(WebDriver driver, String filePath) throws IOException {

        TakesScreenshot scrShot= (TakesScreenshot)driver;
        File scrFile= scrShot.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileUtils.copyFile(scrFile, destFile);

    }

    // Recorrer lista de elementos dando un false si uno no es encontrado

    public static boolean isDisplayedElements(By locator) {
        boolean f=false;
        List<WebElement> e;
        e= findElements(locator);
        if (isDisplayed(locator)){
            f=true;
            for (int i = 0; i < e.size(); i++) {
                System.out.println(e.get(i).getText());
                if(!e.get(i).isDisplayed()){
                    f=false;
                }
            }

        }
        return f;
    }



    //Obtener driver
    public static WebDriver getDriver() {
        return driver;
    }
}
