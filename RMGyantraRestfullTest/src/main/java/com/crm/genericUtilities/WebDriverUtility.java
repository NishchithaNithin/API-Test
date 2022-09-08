package com.crm.genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

/**
 * 
 * @author SanjayBabu
 *
 */
public class WebDriverUtility {
	/**
	 * to maximize the browser
	 * @param driver
	 */
	public void maximizeTheWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	/**
	 * to minimize the browser
	 * @param driver
	 */
	public void minimizeTheBrowser(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	/**
	 * to refresh the page
	 * @param driver
	 */
	public void refershThePage(WebDriver driver)
	{
		driver.navigate().refresh();
	}
	/**
	 * to get back to previous page
	 * @param driver
	 */
	public void backToPreviousPage(WebDriver driver)
	{
		driver.navigate().back();
	}
	/**
	 * to get forward to next page
	 * @param driver
	 */
	public void forwardToNextPage(WebDriver driver)
	{
		driver.navigate().forward();
	}
	/**
	 *it will wait for 10 seconds till the page gets load.
	 * @param driver
	 */
	public void waitForPageToLoad(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IConstants.implicitlyWaitDuration));
	}
	/**
	 * it will wait for js elements
	 * @param driver
	 */
	public void waitforPageToLoadForJSElement(WebDriver driver)
	{
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(IConstants.scriptTimeOutDuration));
	}
	/**
	 * it will check for the element in the GUI for polling time of 500ms
	 * @param driver
	 * @param element
	 */
	public void waitForElemenetToBeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * it will check for the title in the GUI for polling time of 500ms
	 * @param driver
	 * @param title
	 */
	public void waitForTitleContains(WebDriver driver,String title)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * this method will wait till element to visible
	 * @param driver
	 * @param element
	 */
	public void waitTillElementToVisible(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * this method will wait for the URL of the Page
	 * @param driver
	 * @param URL
	 */
	public void waitTillPageLoadURL(WebDriver driver,String URL)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.until(ExpectedConditions.urlContains(URL));
	}
	/**
	 * this method will ignore the no such element exception for the perticular duration
	 * @param driver
	 */
	public void ignoreNosuchElementException(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(IConstants.explicitWaitDuration));
		wait.ignoring(NoSuchElementException.class);
	}
	/**
	 * here we can change the polling time from default 500ms to any polling time
	 * @param driver
	 * @param pollingTime
	 * @param element
	 */
	public void waitForElementToCustom(WebDriver driver,int pollingTime,WebElement element)
	{
		FluentWait wait=new FluentWait(driver);
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.ignoring(Exception.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * here we are giving custom timeout
	 * @param element
	 * @throws InterruptedException
	 */
	public void waitAndClick(WebElement element)
	{
		int count=0;
		while(count<10)
		{
			try {
				element.click();
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				count++;
			}
		}
	}
	/**
	 * this method is used to switch to window using title
	 * @param driver
	 * @param actualTitle
	 */
	public void switchTowindow(WebDriver driver,String actualTitle)
	{
		Set<String> set = driver.getWindowHandles();
		for (String string : set)
		{
			driver.switchTo().window(string);
			String title = driver.getTitle();
			if(title.contains(actualTitle))
			{
				break;
			}
		}
	}
	/**
	 * this method is used to switch to window by using URL
	 * @param actualURL
	 * @param driver
	 */
	public void switchToWindow(String actualURL,WebDriver driver)
	{
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext())
		{
			String wId = it.next();
			driver.switchTo().window(wId);
			String url = driver.getCurrentUrl();
			if(url.contains(actualURL))
			{
				break;
			}
		}
	}
	/**
	 * switch from one frame to another frame by using index
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	/**
	 * switch from one frame to another frame by using id_attribute
	 * @param driver
	 * @param id_attribute
	 */
	public void switchToFrame(WebDriver driver,String id_attribute)
	{
		driver.switchTo().frame(id_attribute);
	}
	/**
	 * switch from one frame to another frame by using absolute_path
	 * @param driver
	 * @param element
	 */
	public void switchtoFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	/**
	 * its used to switch back from one child frame to parent frame
	 * @param driver
	 */
	public void switchBackToMainPage(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	/**
	 * This method is used to switch alert popUp and accept the popup
	 * @param driver
	 * @param expectedMsg
	 */
	public void switchToAlertPopUpAndAccept(WebDriver driver,String expectedMsg)
	{
		Alert alert = driver.switchTo().alert();
		if(alert.getText().trim().equalsIgnoreCase(expectedMsg.trim()))
		{
			System.out.println("alert msg is verified");
		}else {
			System.out.println("alert msg is not verified");
		}
		alert.accept();
	}
	/**
	 * this method is used to switch to alert popUp and dismiss
	 * @param expectedMsg
	 * @param driver
	 */
	public void switchToAlertPopUpAndDismiss(String expectedMsg,WebDriver driver)
	{
		Alert alert = driver.switchTo().alert();
		if(alert.getText().trim().equalsIgnoreCase(expectedMsg))
		{
			System.out.println("alert msg is verified");
		}else {
			System.out.println("alert msg is not verified");
		}
		alert.dismiss();
	}
	/**
	 * it is used switch to select dropdown by using index
	 * @param element
	 * @param index
	 */
	public void selectDropDown(WebElement element,int index)
	{
		Select select=new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * it is used switch to select dropdown by using value
	 * @param element
	 * @param value
	 */
	public void selectDropDown(WebElement element,String value)
	{
		Select select=new Select(element);
		select.selectByValue(value);
	}
	/**
	 * it is used switch to select dropdown by using visible_text
	 * @param element
	 * @param visible_text
	 */
	public void selectDropDownByVtext(WebElement element,String visible_text)
	{
		Select select=new Select(element);
		List<WebElement> options = select.getOptions();
		select.selectByVisibleText(visible_text);
	
	}
	/**
	 * it is used to mouse over an element
	 * @param driver
	 * @param element
	 */
	public void mouseOverAnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	/**
	 * it is used to right click on element
	 * @param driver
	 * @param element
	 */
	public void rightClickOnElement(WebDriver driver,WebElement element)
	{
		Actions action=new Actions(driver);
		action.contextClick(element).perform();
	}
	/**
	 * it is used to click on enter button using keyboard actions
	 * @param driver
	 */
	public void clickOnEnterButton(WebDriver driver)
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}
	/**
	 * it is used to take screenShot
	 * @param driver
	 * @param screenShotName
	 * @throws Throwable
	 */
	public static String takeScreenShot(WebDriver driver,String screenShotName)
	{
		TakesScreenshot takeScreenShot = (TakesScreenshot)driver;
		File src = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./screenShot/"+screenShotName+".PNG");
		try {
			Files.copy(src, dst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenShotName;
	}
	/**
	 * it is used to perform scrollBar Actions
	 * @param driver
	 */
	public void scrollBarAction(WebDriver driver)
	{
		JavascriptExecutor javaScript = (JavascriptExecutor)driver;
		javaScript.executeScript("window.scrollBy(0,500)");
	}
}
