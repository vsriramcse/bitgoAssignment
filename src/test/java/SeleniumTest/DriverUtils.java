package SeleniumTest;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class DriverUtils extends BaseTest {
    public static WebDriver driver;
    public static void verifyAndProceed(String actual, String expected){
        if(actual.equalsIgnoreCase(expected)){
            test.log(Status.PASS,"Works as Expected :"+ actual);
        }else {
            test.log(Status.FAIL,"Actual and Expected is missing. Actual:"+actual+" Expected:"+expected);
        }
    }
    public static Media takeScreenshot(String fileName) {
        // Convert WebDriver object to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capture the screenshot
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        createDirectory("test-output/Screenshot");


        // Specify the destination file path
        File destinationFile = new File(fileName);

        try {
            // Copy the captured screenshot to the destination file
            FileHandler.copy(sourceFile, destinationFile);
            test.addScreenCaptureFromPath(destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
        return null;
    }
    private static void createDirectory(String directoryPath) {
        Path path = Paths.get(directoryPath);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created: " + directoryPath);
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }
    }
    public static void waitAndProceed(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20) );
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
