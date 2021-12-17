package TP3Junit;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	WebDriver driver;
	JavascriptExecutor js;
	ArrayList<String> tasks = new ArrayList<String>(Arrays.asList("do Task1", "do Task2", "do Task3"));

	@BeforeAll
	public static void initWebDriver() {
		WebDriverManager.edgedriver().setup();
	}

	@BeforeEach
	public void createTimeOut() {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

	}

	@Test
	public void oneTestCase() throws Exception {
		driver.get("https://todomvc.com");
		driver.findElement(By.linkText("Backbone.js")).click();
		tasks.forEach(new Consumer<String>() {
			public void accept(String task) {
				try {
					TP3Junit.addTodo(driver, task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		TP3Junit.removeTodo(driver, 1);

		Thread.sleep(1000);
		TP3Junit.myAssert(driver, 2);
		TP3Junit.screenshot(driver, "C:\\Users\\asus\\eclipse-workspace\\hellowjunit\\screenshots\\screenshot.png");
		;
	}

	@ParameterizedTest
	@ValueSource(strings = { "Backbone.js", "AngularJS", "Dojo", "React" })
	public void multipleTestCase(String platform) throws Exception {
		driver.get("https://todomvc.com");
		driver.findElement(By.linkText(platform)).click();

		tasks.forEach(new Consumer<String>() {
			public void accept(String task) {
				try {
					TP3Junit.addTodo(driver, task);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		TP3Junit.removeTodo(driver, 1);

		TP3Junit.myAssert(driver, 2);
		TP3Junit.screenshot(driver,
				"C:\\Users\\asus\\eclipse-workspace\\TP3Junit\\screenshots\\screenshot" + platform + ".png");

	}

	@AfterEach
	public void quit() throws InterruptedException {
		driver.quit();
	}
}
