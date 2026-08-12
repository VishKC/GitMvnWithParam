package Jenkins.mvn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestToBuild {
	
	WebDriver driver = null;
	
	@Parameters("Browser")
	@BeforeTest
	public void LaunchBrowser(String browserName) {
		
		if(browserName.equalsIgnoreCase("Chrome")) {
//			System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver_win32\\chromedriver.exe");
//			WebDriverManager.chromedriver().setup();
			WebDriverManager.chromedriver().driverVersion("151.0.7922.76").setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("Firefox")) {
//			System.setProperty("webdriver.gecko.driver", "D:\\Selenium\\geckodriver-v0.33.0-win32\\geckodriver.exe");
//			WebDriverManager.firefoxdriver().setup();
			WebDriverManager.firefoxdriver().driverVersion("0.37.1").setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("Edge")) {
//			System.setProperty("webdriver.edge.driver", "D:\\Selenium\\edgedriver_win64\\msedgedriver.exe");
//			WebDriverManager.edgedriver().setup();
			WebDriverManager.iedriver().driverVersion("4.14.0").setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
	}
	
	@Test(priority=1)
	public void selectOptions() {
		driver.findElement(By.xpath("//*[@id=\"radio-btn-example\"]/fieldset/label[2]")).click();	
		driver.findElement(By.id("checkBoxOption3")).click();
	}
	
	@Test(priority=2)
	public void hideShow() {
		driver.findElement(By.id("hide-textbox")).click();
		WebElement textBox = driver.findElement(By.id("displayed-text"));
		if(!textBox.isDisplayed()) {
			driver.findElement(By.id("show-textbox")).click();
			textBox.sendKeys("India");
		}
	}

	@Test(priority=3)
	public void alertCheck() {
		driver.findElement(By.id("name")).sendKeys("Vish");
		driver.findElement(By.id("confirmbtn")).click();
		driver.switchTo().alert();
		String msg = driver.switchTo().alert().getText();
		System.out.println("Message: "+msg);
		driver.switchTo().alert().accept();
	}
	
	@AfterTest
	public void closeDriver() {
		driver.close();
		driver.quit();
	}
	
}