package selenium.revision;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;

public class guru99BankTest {
	WebDriver driver;
	int i = 1;
	String custId, accountno;
	byte b1[], b2[];

	@Test(priority = 0)
	public void customer() {

		driver.findElement(By.linkText("New Customer")).click();
		driver.findElement(By.name("name")).sendKeys("Zenab");
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]")).click();
		driver.findElement(By.name("dob")).sendKeys("06/17/1988");
		driver.findElement(By.name("addr")).sendKeys("Fatehpura");
		driver.findElement(By.name("city")).sendKeys("Udaipur");
		driver.findElement(By.name("state")).sendKeys("Rajasthan");
		driver.findElement(By.name("pinno")).sendKeys("313001");
		driver.findElement(By.name("telephoneno")).sendKeys(randomString());
		driver.findElement(By.name("emailid")).sendKeys(randomString(6) + "@gmail.com");
		driver.findElement(By.name("password")).sendKeys("DAnish@106");
		driver.findElement(By.name("sub")).click();

		custId = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
	}

	@Test(priority = 1)
	public void account() throws FileNotFoundException {

		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[5]/a")).click();
		driver.findElement(By.name("cusid")).sendKeys(custId);
		
		WebElement accountType = driver.findElement(By.name("selaccount"));
		Select s = new Select(accountType);
		s.selectByValue("Current");
		
		driver.findElement(By.name("inideposit")).sendKeys("50000");
		driver.findElement(By.name("button2")).click();

		accountno = driver.findElement(By.xpath("//*[@id=\"account\"]/tbody/tr[4]/td[2]")).getText();
		WebElement table = driver.findElement(By.id("account"));
		File f = new File("artifacts/account.txt");
		FileOutputStream fos = new FileOutputStream(f);
		List<WebElement> tr = table.findElements(By.tagName("tr"));
		try {
			for(int i=0;i<tr.size();i++)
			{
				b1 = tr.get(i).getText().toString().getBytes();
				fos.write(b1);
				System.out.println("\r\n");
			   	}
			fos.close();
			}catch (IOException e) {
					e.printStackTrace();
			}
	}

	@Test(priority=2)
	public void miniStatement() throws FileNotFoundException {
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[13]/a")).click();
		driver.findElement(By.name("accountno")).sendKeys(accountno);
		driver.findElement(By.name("AccSubmit")).click();
		
		WebElement table = driver.findElement(By.id("ministmt"));
		File f = new File("artifacts/mini statement.txt");
		FileOutputStream fos = new FileOutputStream(f);
		List<WebElement> tr = table.findElements(By.tagName("tr"));
		try {
		for(int i=0;i<tr.size();i++)
		{
			b2 = tr.get(i).getText().toString().getBytes();
			fos.write(b2);
			System.out.println("\n");
		   	}
		fos.close();
		}catch (IOException e) {
				e.printStackTrace();
			}	
		   	
	}

	private String randomString(int j) {
		String text = "dipzen";
		SecureRandom sr = new SecureRandom();
		StringBuilder sb = new StringBuilder(j);
		for (int i = 0; i < j; i++)
			sb.append(text.charAt(sr.nextInt(text.length())));
		return sb.toString();
	}

	private String randomString() {
		String text = "123456789";
		SecureRandom sr = new SecureRandom();
		StringBuilder sb = new StringBuilder(10);
		for (int i = 0; i < 10; i++)
			sb.append(text.charAt(sr.nextInt(text.length())));
		return sb.toString();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.gecko.driver", "artifacts/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.demo.guru99.com/V4/");
		driver.manage().window().maximize();
		driver.findElement(By.name("uid")).sendKeys("mngr126919");
		driver.findElement(By.name("password")).sendKeys("YrYgUgA");
		driver.findElement(By.name("btnLogin")).click();
	}

	@AfterMethod
	public void afterMethod() throws IOException {
		screenshot();
		driver.quit();
	}

	public void screenshot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile;
		srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("artifacts/zen" + i + ".png");
		FileUtils.copyFile(srcFile, destFile);
		i++;
	}

}
