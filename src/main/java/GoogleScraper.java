import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GoogleScraper {
    public static void main(String args[]) {
        File file = new File("/usr/local/bin/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

        // Sample proxy from https://www.us-proxy.org/ for reference/test purpose. Paste your list of proxies in proxies.properties.
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(new ProxyManager().getProxy());

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("proxy", proxy);
        capabilities.setJavascriptEnabled(true);

        WebDriver driver = new ChromeDriver(capabilities);
        TakesScreenshot screen = (TakesScreenshot) driver;

        driver.get("https://www.google.co.in");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;

        WebElement element = driver.findElement(By.name("q"));
        String queryString = "Jaguar MonroevilleServices + 3780 William Penn Hwy + Monroeville + PA + 15146";  //Dealer name+ Street+ City+ State+ Zipcode

        element.sendKeys(queryString);
        element.submit();

        JSONArray search_output = new JSONArray();

        List<WebElement> findUrlElements = driver.findElements(By.xpath(".//div[@class='rc']"));
        for (WebElement webElement : findUrlElements)
        {
            JSONObject record = new JSONObject();
            try{
                record.put("title", webElement.findElement(By.xpath(".//h3[@class='r']")).getText());
                record.put("text", webElement.findElement(By.xpath(".//span[@class='st']")).getText());
                record.put("url", webElement.findElement(By.xpath(".//h3[@class='r']/a")).getAttribute("href"));
                search_output.put(record);
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }

        System.out.println(search_output); //JSON output

        ScreenshotCapture.captureScreenshot(driver, "google_search");
        driver.close();
//        exit(1);
    }
}
