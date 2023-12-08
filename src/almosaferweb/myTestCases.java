package almosaferweb;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	String WebSiteURL = "https://WWW.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();

	@BeforeTest
	public void MysetUP() {

		driver.manage().window().maximize();
		driver.get(WebSiteURL);
		WebElement WelcomeDialog = driver
				.findElement(By.xpath("//button[normalize-space()='Kingdom of Saudi Arabia, SAR']"));
		WelcomeDialog.click();
	}

	@Test(enabled = false)
	public void TestLanguage() {
		String Actuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		String ExpectedLanguage = "en";

		softassert.assertEquals(Actuallanguage, ExpectedLanguage, "this is to test language");

	}

	@Test()
	public void CheckCurrancy() {
		WebElement Currancy = driver.findElement(By.xpath("//button[normalize-space()='SAR']"));
		String ActualCurrancy = Currancy.getText();
		String ExpectedCurrancy = "SAR";
		softassert.assertEquals(ActualCurrancy, ExpectedCurrancy, "THIS IS TO CHECK CURRANCY");
	}

	@Test()
	public void CheckNumber() {
		String ExpectedContantNumber = "+966554400000";
		WebElement ActualContantNumber = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));

		softassert.assertEquals(ActualContantNumber.getText(), ExpectedContantNumber,
				"THIS IS TO CHECK NUM OF CONTANT");

	}

	@Test()
	public void CheckLogoQitaf() {

		WebElement QitafLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"));
		boolean QitafisDisplayed = QitafLogo.isDisplayed();
		boolean ExpectedQitaf = true;
		softassert.assertEquals(QitafisDisplayed, ExpectedQitaf, "THIS IS TO CHECK Qitaflogo");

	}

	@Test()
public void NotSelectedHotelTab() {
	WebElement HotelTab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	String ActualSelectedtab=HotelTab.getAttribute("aria-selected");
	String ExpectedSelectedTab="false";
	Assert.assertEquals(ActualSelectedtab,ExpectedSelectedTab);

}
	@Test()
public void checkTheDayDepartureArrival() {
	LocalDate today=LocalDate.now();
	int Expecteddaydeparture=today.plusDays(1).getDayOfMonth();
	int ExpecteddayReturn=today.plusDays(2).getDayOfMonth();
	WebElement ActualDepartureDate=driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
	WebElement ActualreturneDate=driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
	
int  ActualDValue=Integer.parseInt(ActualDepartureDate.getText());
	int ActualRValue=Integer.parseInt(ActualreturneDate.getText());
	Assert.assertEquals(ActualDValue, Expecteddaydeparture);
	Assert.assertEquals(ActualRValue, ExpecteddayReturn);
	
	
	/// check the day
	
	WebElement Dayname=driver.findElement(By.cssSelector(
"div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"));
	String ActualDayName=Dayname.getText();
	String ExpectedDayName=today.plusDays(1).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toString();
	Assert.assertEquals(ActualDayName, ExpectedDayName);
	/// check the month
	
	WebElement monthname=driver.findElement(By.cssSelector(
"div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-hvvHee cuAEQj']"));
	String ActualmonthName=monthname.getText();
	String ExpectedmonthName=today.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toString();
	Assert.assertEquals(ActualmonthName, ExpectedmonthName);
	
	}
	@Test()
	public void ChoseLanguage() 
	{
	String ENlang="English";
	String ARlang="العربية";
	
		WebElement language=driver.findElement(By.cssSelector(".sc-gkFcWv.jJNggu"));
		
		String ActualLang=language.getText();
		if(ActualLang=="العربية") {
			 ENlang="English";
		}
		else if(ActualLang=="English") {
			 ARlang="العربية";
		}
		String [] Langs= {ENlang,ARlang};
		
	Random rand=new Random();
	int randLang=rand.nextInt(Langs.length);
	language.click();
	}
		
		
		
		
		
	
	
	
	
	
	
	@AfterTest
	public void Aftertest() {
		softassert.assertAll();
		// driver.close();
	}

}
