package com.selenium.Assignment;

import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Assignment {

	protected static WebDriver driver;
	static HttpURLConnection huc = null;
	static int respCode = 200;
	static JavascriptExecutor js;

	@BeforeTest

	@Parameters("browser")

	public void setupSuite(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/Compatibledriver/geckodriver.exe");

			driver = new FirefoxDriver();
		}

		if (browser.equalsIgnoreCase("Chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/Compatibledriver/chromedriver.exe");

			driver = new ChromeDriver();

		}
	}

	public static void launchUrl() throws Exception {

		driver.get("https://en.wikipedia.org/wiki/Selenium");

	}

	public static void maximize() {
		driver.manage().window().maximize();
	}

	public static void checkLinksStatus(By A) {
		java.util.List<WebElement> element = driver.findElements(A);
		System.out.println(element.size());

		Iterator<WebElement> it = element.iterator();

		while (it.hasNext()) {

			String url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
				} else {
					System.out.println(url + " is a valid link");
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/*
		 * @AfterSuite(alwaysRun = true)
		 * 
		 * public void tearDown() {
		 * 
		 * driver.close();
		 * 
		 * 
		 */
	}

	public static void periodicTable(By PeriodicTablePath, String ElementToClick) {
		driver.manage().window().maximize();
		java.util.List<WebElement> allelements = driver.findElements(PeriodicTablePath);
		System.out.println(allelements.size());
		for (int i = 0; i <= allelements.size(); i++) {
			// System.out.println(allelements.get(i).getText());
			if (allelements.get(i).getText().equalsIgnoreCase(ElementToClick)) {
				allelements.get(i).click();
				break;
			}
		}

		// driver.findElement(ElementToClick).click();
	}

	public static boolean checkForElementPresense(By FeaturedElement) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(FeaturedElement).isDisplayed();
				System.out.println("Selected element is an Featured Article");
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;

	}
	
	public static void linkscount(By Links) {
		
		java.util.List<WebElement> links = driver.findElements(Links);
		System.out.println("Number of PDF links present" +links.size());
		
	}

	/*
	 * public static void takeSceeshotSapecificArea() throws InterruptedException{
	 * 
	 * //WebElement ele = driver.findElement(By.xpath(
	 * ".//*[@id='mw-content-text']/div/table[1]/tbody/tr[15]/th"));
	 * 
	 * 
	 * 
	 * // Take screenshot and store as a file format
	 * 
	 * File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); try {
	 * 
	 * // now copy the screenshot to desired location using copyFile method
	 * 
	 * BufferedImage fullImg = ImageIO.read(src);
	 * 
	 * 
	 * org.openqa.selenium.Point point = ele.getLocation();
	 * 
	 * int eleWidth = ele.getSize().getWidth();
	 * 
	 * int eleHeight = ele.getSize().getHeight();
	 * 
	 * System.out.println(eleWidth+"======="+eleHeight);
	 * 
	 * BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
	 * 
	 * eleWidth, eleHeight);
	 * 
	 * ImageIO.write(eleScreenshot, "png", src);
	 * 
	 * 
	 * FileUtils.copyFile(src, new File(System.getProperty("user.dir") +
	 * "/driver/screen.png")); } catch (IOException e)
	 * 
	 * {
	 * 
	 * System.out.println(e.getMessage());
	 * 
	 * }}
	 * 
	 */
	
	
	public static void takeSceeshotSapecificArea() throws InterruptedException, IOException{
		 ru.yandex.qatools.ashot.Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
	     ImageIO.write(fpScreenshot.getImage(),"PNG",new File(System.getProperty("user.dir")+"/driver/screenshot.png"));
	      
	}
	
	public static void autosuggestionVerification(By Search , By Autolist , String Elementtobeverified,String ElementtoSearch) throws InterruptedException {
		
		driver.findElement(Search).sendKeys(ElementtoSearch);
		Thread.sleep(5000);
		java.util.List<WebElement> autoelements = driver.findElements(Autolist);
		
		System.out.println(autoelements.size());
		//for(WebElement  data:autoelements) {
			int indexNo=-1;
		for(int i=0;i<=autoelements.size();i++) {
			System.out.println(autoelements.get(i).getText());
			if(autoelements.get(i).getText().equalsIgnoreCase(Elementtobeverified)) {
				indexNo=i;
				break;
			
		}
		
	}
		driver.findElement(Search).clear();
		if(indexNo!=-1)
	System.out.println("Index of Plutonium is  "+(indexNo+1));
	}
	
	@AfterClass
	public void closedrive() {
		driver.close();
	}
	
}
