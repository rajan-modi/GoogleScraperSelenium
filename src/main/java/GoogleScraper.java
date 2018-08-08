import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class ScreenshotCapture{
    public static void captureScreenshot (WebDriver driver, String screenshotName){
        try {
            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String dest = "./" + screenshotName + ".png";
            File destination = new File(dest);
            FileUtils.copyFile(source, destination);
        }
        catch (IOException e) {e.printStackTrace();}
    }
}

public class GoogleScraper {
    public static void main (String args[]){
        File file = new File("/usr/local/bin/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        WebDriver driver = new ChromeDriver();
        TakesScreenshot screen = (TakesScreenshot) driver;

        driver.get("http://www.google.com");
        WebElement element = driver.findElement(By.name("q"));

        element.sendKeys("Jaguar MonroevilleServices + 3780 William Penn Hwy + Monroeville+ PA+ 15146");
        element.submit();

        List<WebElement> findUrlElements = driver.findElements(By.xpath("//div[@class='rc']//h3[@class='r']/a"));
        for (WebElement webElement : findUrlElements)
        {
            System.out.println(webElement.getAttribute("href"));
            System.out.println(webElement.getText());
        }

//        List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='rc']//h3[@class='s']"));
//        for (WebElement webElement : findElements)
//        {
//            System.out.println(webElement.getText());
//        }
//        ScreenshotCapture.captureScreenshot(driver, "google");

        driver.close();
    }
}
