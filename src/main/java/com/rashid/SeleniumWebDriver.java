package com.rashid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.ie.InternetExplorerDriverService;

import java.io.File;

public class SeleniumWebDriver {

	public static void main(String[] args) throws InterruptedException {
		 
//		System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();

//		File file = new File("C:\\IESeleniumDriver\\IEDriverServer_Win32_3.8.0\\IEDriverServer.exe");
//		System.setProperty("webdriver.ie.driver", "C:\\IESeleniumDriver\\IEDriverServer_Win32_3.8.0\\IEDriverServer.exe");
//		WebDriver driver = new InternetExplorerDriver();


		String exePath = "C:\\Users\\rjilani\\Documents\\webdrivers\\IEDriverServer_Win32_3.9.0\\IEDriverServer.exe";
		InternetExplorerDriverService.Builder serviceBuilder = new InternetExplorerDriverService.Builder();
		serviceBuilder.usingAnyFreePort(); // This specifies that sever can pick any available free port to start
		serviceBuilder.usingDriverExecutable(new File(exePath)); //Tell it where you server exe is
		serviceBuilder.withLogLevel(InternetExplorerDriverLogLevel.TRACE); //Specifies the log level of the server
		serviceBuilder.withLogFile(new File("C:\\SeleniumlogFile.txt")); //Specify the log file. Change it based on your system
		InternetExplorerDriverService service = serviceBuilder.build(); //Create a driver service and pass it to Internet explorer driver instance
		InternetExplorerDriver driver = new InternetExplorerDriver(service);
		driver.get("http://toolsqa.wpengine.com");


		  driver.get("http://www.cnn.com");
		  Thread.sleep(5000);  // Let the user actually see something!
//		  WebElement searchBox = driver.findElement(By.name("q"));
//		  searchBox.sendKeys("cnn");
//		  searchBox.submit();
		  Thread.sleep(1000);  // Let the user actually see something!
		  driver.quit();
	 }

}
