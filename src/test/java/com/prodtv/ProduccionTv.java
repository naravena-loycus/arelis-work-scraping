package com.prodtv;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
class ProduccionTv extends BaseSelenium{

     ProduccionTv(WebDriver driver) {
        super(driver);
    }

    private By olLabels= By.xpath("//ol/li");
    private By txtTitle= By.xpath("//ol/li[1]/article/div/div/div/a");



    void a(){
        List<WebElement> ol=findElements(olLabels);
        for (int i = 1; i <= ol.size(); i++) {
            By l= By.xpath("//ol/li["+i+"]/article/div/div/div/a");
            System.out.println("Text --> "+ i +": " + getText(l));

        }
        }
}