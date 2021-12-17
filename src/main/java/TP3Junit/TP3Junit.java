package TP3Junit;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TP3Junit {

	public static void screenshot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}

	protected static void addTodo(WebDriver driver, String todo) throws InterruptedException {
		WebElement element = driver.findElement(By.className("new-todo"));
		element.sendKeys(todo);
		element.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
	}

	protected static void removeTodo(WebDriver driver, int number) throws InterruptedException {
		driver.findElement(By.cssSelector("li:nth-child(" + number + ") .toggle")).click();
		Thread.sleep(1000);
	}

	protected static void myAssert(WebDriver driver, int result) throws InterruptedException {
		WebElement element = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
		String resultStringFormat = String.format("$d item left", result);
		ExpectedConditions.textToBePresentInElement(element, resultStringFormat);
		Thread.sleep(2000);
	}

}
