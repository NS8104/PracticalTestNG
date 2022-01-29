package test;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import pages.AddCategory;
import util.BrowserFactory;
import util.ExcelReader;

public class TestPage {
	
	static WebDriver driver;
	ExcelReader excelreader= new ExcelReader("src\\main\\java\\data\\TestData 1.xlsx");
	String categoryname = excelreader.getCellData("CategoryName", "ItemName", 2);
	AddCategory addCategory;
	
	@BeforeMethod
	public void init() {
		driver= BrowserFactory.init();
	}

	@Test (priority= 1)
	public void userShouldBeAbleToAddCategory() {
		
		addCategory = PageFactory.initElements(driver, AddCategory.class); 
		addCategory.insertCategoryName(categoryname);
		addCategory.clickOnAddCategoryButton();
		addCategory.validateCategoryAddedandDisplayed();
			
		
	}
	@Test (priority= 2)
	public void userShouldNotBeAbleToAddDuplicateCategory() {
		
		addCategory = PageFactory.initElements(driver, AddCategory.class); 
		addCategory.addingDuplicateCategory(categoryname);
		
	}
	
	@Test (priority= 3)
	public void validdropdownMonthcategory() {
		
		addCategory = PageFactory.initElements(driver, AddCategory.class);
		addCategory.ValidateMonthdropdown();
	}
	
	@AfterMethod
	public void teardown() {
		driver = BrowserFactory.teardown();

	}
	
	

}
