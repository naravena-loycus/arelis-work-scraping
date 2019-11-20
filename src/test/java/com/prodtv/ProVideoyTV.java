package com.prodtv;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProVideoyTV {
    private WebDriver driver;
    @BeforeTest
    public void setUpClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/macos/chromedriver");
        ChromeOptions options= new ChromeOptions();
        //Navegador Headless
        options.addArguments("--headless");
        //options.addExtensions(new File("drivers/macos/AdBlock.crx"));
        //options.addArguments("--start-maximized");
        driver= new ChromeDriver(options);
        driver.get("http://www.madrid.org/filmmadrid/guia-empresas-audiovisuales.html");

        //---

        //driver= new ChromeDriver();
    }
    private String ruta = "docs/arelis-work.txt";
    private File archivo = new File(ruta);
    private void writeLineInFile(String s) throws IOException {

        BufferedWriter bw;
        if(archivo.exists()) {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(s);
            bw.newLine();
        } else {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("Acabo de crear el fichero de texto.");
        }
        bw.close();

    }
    @Test
    public void principalTest() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='listProductoras']/li[5]/a")).click();
        List<WebElement> num=driver.findElements(By.xpath("//div[@class='resultado row']/div"));
        String title;
        String email;
        int numA=0;
        for (int i = 1; i <= num.size(); i++) {
            try {
                title= driver.findElement(By.xpath("//*[@id='resultados']/div["+i+"]/h4")).getText();
                email=driver.findElement(By.xpath("//*[@id='resultados']/div["+i+"]/div/div[2]/p/a[text()[contains(.,'@')]]")).getText();
                if (title!=null && email!=null){
                    numA++;
                    String s=numA +". " +title + "\t|\t"+email;
                    //writeLineInFile(s);
                    System.out.println(s);
                }

            }catch (NoSuchElementException e){
            }

        }

        Thread.sleep(2000);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
