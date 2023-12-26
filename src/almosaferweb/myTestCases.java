package almosaferweb;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	String WebSiteURL = "https://WWW.almosafer.com/en";
	WebDriver driver = new ChromeDriver();
	SoftAssert softassert = new SoftAssert();
	boolean Loaded=false;
	@BeforeTest
	public void MysetUP() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.manage().window().maximize();
		driver.get(WebSiteURL);
		WebElement WelcomeDialog = driver
				.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
		WelcomeDialog.click();
	}

	@Test(enabled=false)
	// test the default language
	public void TestLanguage() {
		String Actuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");

		String ExpectedLanguage = "en";

		softassert.assertEquals(Actuallanguage, ExpectedLanguage, "this is to test language");

	}

	@Test()
	//check currency
	public void CheckCurrancy() {
		WebElement Currancy = driver.findElement(By.xpath("//button[normalize-space()='SAR']"));
		String ActualCurrancy = Currancy.getText();
		String ExpectedCurrancy = "SAR";
		softassert.assertEquals(ActualCurrancy, ExpectedCurrancy, "THIS IS TO CHECK CURRANCY");
	}

	@Test()
	//check phone number 
	public void CheckNumber() {
		String ExpectedContantNumber = "+966554400000";
		WebElement ActualContantNumber = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));

		softassert.assertEquals(ActualContantNumber.getText(), ExpectedContantNumber,
				"THIS IS TO CHECK NUM OF CONTANT");

	}

	@Test()
	//check if the logo is exist
	public void CheckLogoQitaf() {

		WebElement QitafLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"));
		boolean QitafisDisplayed = QitafLogo.isDisplayed();
		boolean ExpectedQitaf = true;
		softassert.assertEquals(QitafisDisplayed, ExpectedQitaf, "THIS IS TO CHECK Qitaflogo");

	}

	@Test(enabled=false)
public void NotSelectedHotelTab() {
	WebElement HotelTab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	String ActualSelectedtab=HotelTab.getAttribute("aria-selected");
	String ExpectedSelectedTab="false";
	Assert.assertEquals(ActualSelectedtab,ExpectedSelectedTab);

}
	@Test(enabled = false)
	// check the days as required
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
	@Test(priority = 1)
	/// choose language randomly
	public void ChooseLanguage() throws InterruptedException 
	{

		String [] MyUrls= {"https://global.almosafer.com/en","https://global.almosafer.com/ar"};
		Random rand =new Random();
		int randomUrl=rand.nextInt(MyUrls.length);
		driver.get(MyUrls[randomUrl]);
		String Actuallanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
	Assert.assertEquals(Actuallanguage.contains("en") || Actuallanguage.contains("ar") ,true);

	}
	@Test(priority =2)
	
	public void HotelTabTestCase() throws InterruptedException {
Thread.sleep(5000);
		/// click on hotel tab
		WebElement Hoteltab=driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		Hoteltab.click();
		Thread.sleep(3000);
		
		
		/// define the text field to send the city name 

		
		String [] ArabicCountry= {"جدة","دبي"};
		String [] EnglishCountry= {"Dubai","Jeddah","Riyadh"};
		 Random rand=new Random();
		
			
		// GET the current language
		String Currentlanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		if (driver.getCurrentUrl().contains("en")) {
			int indexEN=rand.nextInt(EnglishCountry.length);
			WebElement Location=driver.findElement(By.className("phbroq-2"));
			Location.sendKeys(EnglishCountry[indexEN]);
	Thread.sleep(3000);
	///// click the first option from the auto complete list 
			 WebElement CountryList = driver.findElement(By.className("phbroq-4"));
				List<WebElement> CountriesInTheList = CountryList.findElements(By.tagName("li"));
		CountriesInTheList.get(1).click();
		
		}
			else if (driver.getCurrentUrl().contains("ar"))
			{
			
				int indexAR=rand.nextInt(ArabicCountry.length);
				WebElement Location=driver.findElement(By.className("phbroq-2"));
				Location.sendKeys(ArabicCountry[indexAR]);
				Thread.sleep(3000);
				///// click the first option from the auto complete list 
				 WebElement CountryList = driver.findElement(By.className("phbroq-4"));
					List<WebElement> CountriesInTheList = CountryList.findElements(By.tagName("li"));
			CountriesInTheList.get(1).click();
		
			
			}
			
			
		}
	
		@Test(priority = 3)
		// choose randomly room 
		public void RoomReserved() throws InterruptedException
		{
			Random rand=new Random();
			
		
					WebElement Rooms = driver.findElement(By.cssSelector(".tln3e3-1.eFsRGb"));
			Select select = new Select(Rooms);
			 int numberOfOptions = select.getOptions().size();
			int indexRooms=rand.nextInt(numberOfOptions-1);
			select.selectByIndex(indexRooms);
			WebElement SearchButton=driver.findElement(By.className("js-HotelSearchBox__SearchButton"));
			SearchButton.click();
		
	
		}
		

		@Test(priority = 4)
		///// the page full loaded
		
		public void LodaingFullyComplete() throws InterruptedException
		{
			Thread.sleep(12000);
		WebElement ActualLoadingmsg=driver.findElement(By.className("sc-cClmTo"));
		String Actualmsg=ActualLoadingmsg.getText();
	Assert.assertEquals(Actualmsg.contains("found") || Actualmsg.contains("وجدنا"),true);
		}
	@Test(priority = 5)
	public void SortingTest() throws InterruptedException {
	
	//click on sort button
WebElement SortButton=driver.findElement(By.className("sc-hokXgN"));
SortButton.click();
Thread.sleep(3000);
WebElement PricesSection = driver.findElement(By.className("sc-iIHjhz"));
List<WebElement> pricesValue= PricesSection.findElements(By.className("Price__Value"));
int lowestPrice = Integer.parseInt(pricesValue.get(0).getText());
	int highestPrice = Integer.parseInt(pricesValue.get(pricesValue.size() - 1).getText());

	Assert.assertEquals(highestPrice > lowestPrice, true);

	}
	
	
	
	
	
		
	@AfterTest
	public void Aftertest() {
		softassert.assertAll();
		// driver.close();
	}

}
