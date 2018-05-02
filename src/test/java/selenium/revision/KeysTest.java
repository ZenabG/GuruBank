package selenium.revision;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class KeysTest {
  @Test
  public void f() {
	  System.setProperty("webdriver.gecko.driver", "artifacts/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.demo.guru99.com/V4/");
		driver.manage().window().maximize();
		
		driver.findElement(By.name("uid")).sendKeys(Keys.chord(Keys.INSERT,"m","n","g","r","1","2","6","9","1","9"));	//mngr126919
		driver.findElement(By.name("password")).sendKeys("YrYgUgA");
		driver.findElement(By.name("btnLogin")).click();
		driver.quit();
  }
}
