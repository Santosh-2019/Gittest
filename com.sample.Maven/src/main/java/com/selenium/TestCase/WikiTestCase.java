package com.selenium.TestCase;

import java.awt.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.Assignment.Assignment;
import com.selenium.Assignment.RepositaryParser;

public class WikiTestCase extends Assignment {
	
	static RepositaryParser Parse;
	static JavascriptExecutor js ;
	
	
	public static void oxygen() throws Throwable {
		
		Parse = new RepositaryParser(System.getProperty("user.dir")+"\\TestData\\ObjectRepo.properties");
		js = (JavascriptExecutor) driver;
		WebElement Data=	driver.findElement(Parse.getObjectLocator("ExternalLink"));
		js.executeScript("arguments[0].scrollIntoView();", Data);
		Data.click();
		//Method called to check the links status in external links block
		checkLinksStatus(Parse.getObjectLocator("ExternalLinkBlock"));
		//Dynamic method called to click on oxygen (can also click on any element present in table without changing the xpath every time
		periodicTable(Parse.getObjectLocator("PeriodicTable"), "O");
		Thread.sleep(2000);
		//Method called to check for featured article or not
		checkForElementPresense(Parse.getObjectLocator("FeaturedArticle"));
		//Method call to verify the position of plutonium
		autosuggestionVerification(Parse.getObjectLocator("Search"),Parse.getObjectLocator("SearchAutosuggestion"),"Plutonium","Pluto");
		//Method called to take screenshot of total page and it gets saved in driver folder in the same project
		takeSceeshotSapecificArea();
		//Method called to verrify number of pdf links present in citilings
		linkscount(Parse.getObjectLocator("pdflinks"));
		
	}

}
