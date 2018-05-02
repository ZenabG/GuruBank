package selenium.revision;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

public class InvalidPwdTest {
	WebDriver driver;
	int inc;

	@Test
	public void incorrectLogin() {
		driver.findElement(By.name("uid")).sendKeys("1303");
		driver.findElement(By.name("password")).sendKeys("Guru99");
		driver.findElement(By.name("btnLogin")).click();
		alertHandling();
	}

	private void alertHandling() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	@Test
	public void correctLogin() {
		driver.findElement(By.name("uid")).sendKeys("mngr126919");
		driver.findElement(By.name("password")).sendKeys("YrYgUgA");
		driver.findElement(By.name("btnLogin")).click();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.gecko.driver", "artifacts/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.demo.guru99.com/V4/");
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		screenshot();
		driver.quit();
	}

	private void screenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile;
		srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("artifacts/" + inc + ".png");
		FileUtils.copyFile(srcFile, destFile);
		inc++;
	}

}
