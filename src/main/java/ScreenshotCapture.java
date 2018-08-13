import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotCapture {
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
