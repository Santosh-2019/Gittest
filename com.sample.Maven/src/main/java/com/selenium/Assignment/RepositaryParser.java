package com.selenium.Assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

public class RepositaryParser {

		 
		private FileInputStream stream;
		private String RepositoryFile;
		private Properties propertyFile = new Properties();
	 
		public RepositaryParser(String fileName) throws IOException
		{
			this.RepositoryFile = fileName;
			stream = new FileInputStream(RepositoryFile);
			propertyFile.load(stream);
		}
	 
		public By getObjectLocator(String locatorName)
		{
			String locatorProperty = propertyFile.getProperty(locatorName);
			//System.out.println(locatorProperty.toString());
			String arr[] = locatorProperty.split(":");
			
			String locatorType = arr[0];
			String locatorValue = arr[1];
	 
			By locator = null;
			switch(locatorType)
			{
			case "ID":
				locator = By.id(locatorValue);
				break;
			case "Name":
				locator = By.name(locatorValue);
				break;
			case "CssSelector":
				locator = By.cssSelector(locatorValue);
				break;
			case "LinkText":
				locator = By.linkText(locatorValue);
				break;
			case "PartialLinkText":
				locator = By.partialLinkText(locatorValue);
				break;
			case "TagName":
				locator = By.tagName(locatorValue);
				break;
			case "Xpath":
				locator = By.xpath(locatorValue);
				break;
			case "className":
				locator = By.className(locatorValue);
				break;
			}
			return locator;
		}
	}