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
        List<WebElement> hashList = driver.findElements(By.id("transaction-box"));
        System.out.println(hashList);
        List<String> hashName= new ArrayList<>();
        for(int i=0;i<hashList.size();i++){
            if(driver.findElements(By.xpath("//div[@ class='transaction-box']["+i+1+"]//div[@class='vins']/div")).size()==1&&
                    driver.findElements(By.xpath("//div[@ class='transaction-box']["+i+1+"]//div[@class='vouts']/div")).size()==2){
                hashName.add(hashList.get(i).getText());
            }
        }
        System.out.println("Hash Name having the 1 input and 2 output:"+hashName);
    }
    @AfterClass
    public void teardown(){
        if(driver!=null){
            driver.quit();
        }
    }

}
