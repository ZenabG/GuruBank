package selenium.revision;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AgileCourseDetailsTest {
	WebDriver driver;
	int i=1;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "artifacts/geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
  @Test
  public void course() throws Exception {
	
	driver.get("http://techcanvass.com/");
	
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	WebElement course = driver.findElement(By.xpath("/html/body/div[2]/div/nav/ul/li[3]/a")); ///html/body/div[2]/div/nav/ul/li[3]/a
	Actions action = new Actions(driver);
	action.moveToElement(course);
	action.build().perform();
	
	WebElement automation = driver.findElement(By.xpath("/html/body/div[2]/div/nav/ul/li[3]/ul/li[3]/a"));
	action.moveToElement(automation);
	action.build().perform();
	
	File f = new File ("artifacts/data.txt");
	FileOutputStream fo = new FileOutputStream(f);
	Thread.sleep(2000);
	driver.findElement(By.xpath("/html/body/div[2]/div/nav/ul/li[3]/ul/li[3]/ul/li[4]/a")).click();
	String data = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[1]/div[1]/p[1]")).getText();
	byte b[] = data.getBytes();
	try {
		fo.write(b);
		fo.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
  
  @AfterMethod
  public void tearDown() {
	  TakesScreenshot pic = (TakesScreenshot) driver;
	  File srcFile;
	  srcFile = pic.getScreenshotAs(OutputType.FILE);
	  File destFile = new File("artifacts/"+i+".png");
	  try {
		FileUtils.copyFile(srcFile, destFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  i++;
	  driver.quit();
  }
}
