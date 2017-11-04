package com.org.learningMaven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class HelloWorldTest{
	
	@Test
	public void Login() {
		System.out.println("Logging into account");
		WebDriver driver;
		System.setProperty("webdriver.gecko.driver","C:\\CodeRepository\\myMavenProject\\geckodriver-v0.19.0-win64\\geckodriver.exe");
		driver= new FirefoxDriver();
		driver.get("http://google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Kenji Sato");
		element.submit();
	}
}