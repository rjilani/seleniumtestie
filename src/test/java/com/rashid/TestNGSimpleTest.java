package com.rashid;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestNGSimpleTest {

    public WebDriver driver;

//	InternetExplorerDriver driver;

    @BeforeTest
    public void beforeMethod() {
//		setUpChrome();
        setUpIE();

        // Put a Implicit wait, this means that any search for elements on the page
        // could take the time the implicit wait is set for before throwing exception
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8081/index.html");

    }

    private void setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rjilani\\Documents\\webdrivers\\chromedriver_win32\\chromedriver.exe");
//	    ChromeOptions chromeOptions = new ChromeOptions();
//	    chromeOptions.addArguments("--headless");
//		driver = new ChromeDriver(chromeOptions);
        driver = new ChromeDriver();
    }

    private void setUpIE() {
        String exePath = "C:\\Users\\rjilani\\Documents\\webdrivers\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe";
        InternetExplorerDriverService.Builder serviceBuilder = new InternetExplorerDriverService.Builder();
        serviceBuilder.usingAnyFreePort(); // This specifies that sever can pick any available free port to start
        serviceBuilder.usingDriverExecutable(new File(exePath)); //Tell it where you server exe is
        serviceBuilder.withLogLevel(InternetExplorerDriverLogLevel.TRACE); //Specifies the log level of the server
        serviceBuilder.withLogFile(new File("./SeleniumlogFile.txt")); //Specify the log file. Change it based on your system
        InternetExplorerDriverService service = serviceBuilder.build(); //Create a driver service and pass it to Internet explorer driver instance
        driver = new InternetExplorerDriver(service);
    }

    @Test
    public void appSwithcing() {
        WebElement springAppLink = driver.findElement(By.xpath("/html/body/a"));
        System.out.println("Rashid jilani ... " + springAppLink.getText());
        assertEquals(springAppLink.getText(), "Spring boot application");
        springAppLink.click();


        String parent = driver.getWindowHandle();
        Set<String> s1 = driver.getWindowHandles();

        s1.forEach(s -> System.out.println("inside lambda: " + s));

        List<String> list = new ArrayList<String>(s1);

        System.out.println(parent);

        System.out.println("outside lambda: " + list.get(0));
        System.out.println("outside lambda" + list.get(1));


        Iterator<String> I1 = s1.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                System.out.println(child_window);
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
                WebElement elm = driver.findElement(By.xpath("//*[@id=\"albums\"]/div[3]/div/div[1]/div/div/h4[1]/div/span[1]/span/span"));
                System.out.println("Title of the page ... " + elm.getText() + ".." + driver.getTitle());
                driver.close();
            }
        }
        driver.switchTo().window(parent);
        System.out.println(driver.switchTo().window(parent).getTitle());

//		WebElement elm = driver.findElement(By.xpath("//*[@id=\"albums\"]/div[3]/div/div[1]/div/div/h4[1]/div/span[1]/span/span"));
//		System.out.println("Title of the page ... " + elm.getText() + ".." + driver.getTitle());
//		elm.click();
    }


    @AfterTest
    public void afterMethod() {
        // Close the driver
        driver.quit();
    }

}
