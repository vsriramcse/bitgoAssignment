package SeleniumTest.TestScripts;

import SeleniumTest.BaseTest;
import SeleniumTest.DriverUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static SeleniumTest.DriverUtils.driver;

public class TestScript extends BaseTest {

    @FindBy(xpath = "//div[@class='transactions']/h3")
    private WebElement header;

    @BeforeTest
    public void driverSetUp(){
        driver= new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testingSite() throws InterruptedException {
        test= extent.createTest("Demo Test");
        driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
        WebElement element = driver.findElement(By.xpath("//div[@class='transactions']/h3"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        DriverUtils.verifyAndProceed(driver.findElement(By.xpath("//div[@class='transactions']/h3")).getText(),"25 of 2875 Transactions");
        List<WebElement> hashList = driver.findElements(By.xpath("//div[@class='transaction-box']"));
        System.out.println(hashList);
        for(int i=0;i<hashList.size();i++){
            if(driver.findElements(By.xpath("//div[@class='transaction-box']["+i+"]/div[@class='ins-and-outs']/div[1]/div")).size()==1){
                if(driver.findElements(By.xpath("//div[@class='transaction-box']["+i+"]/div[@class='ins-and-outs']/div[3]/div")).size()==2){
                    System.out.println(driver.findElement(By.xpath("//div[@class='transaction-box']["+i+"]/div[1]/div[1]/a")).getText());
                }
            }
        }
    }
    @AfterClass
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
