package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class AddCategory extends BasePage {
	WebDriver driver;

	public AddCategory(WebDriver driver) {
		this.driver = driver;
	}

	// Element Library
	@FindBy(how = How.NAME, using = "categorydata")
	WebElement ADD_CATEGORY_DATAFIELD_ELEMENT;
	@FindBy(how = How.CSS, using = "[value= \"Add category\"]")
	WebElement ADD_CATEGORY_SUBMITBUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "/html/body/div[3]/a[208]/span")
	WebElement TEST_ELEMENT;
	@FindBy(how = How.XPATH, using = "//select[@name='due_month']")
	WebElement DROPDOWN_ELEMENT;
	@FindBy(how = How.CSS, using = "[name=\"category\"]")
	WebElement ENTERED_CATEGORY_ELEMENT;
	@FindBy(how = How.CSS, using = "div.controls>:nth-last-child(3)")
	WebElement REMOVE_CATEGORY_ELEMENT;
	@FindBy(how = How.XPATH, using = "//a[1]")
	WebElement REMOVE_CATEGORY_YES_ELEMENT;
	String enteredCategoryName;

	public void insertCategoryName(String categoryName) {

		ADD_CATEGORY_DATAFIELD_ELEMENT.clear();
		enteredCategoryName = categoryName + generateRandomNum(999);
		ADD_CATEGORY_DATAFIELD_ELEMENT.sendKeys(enteredCategoryName);
		
		
	}

	public void clickOnAddCategoryButton() {
		ADD_CATEGORY_SUBMITBUTTON_ELEMENT.click();
		//ENTERED_CATEGORY_ELEMENT.click();
	}

	public void validateCategoryAddedandDisplayed() {
//		insertCategoryName(enteredCategoryName);
//		clickOnAddCategoryButton();
		Select sel = new Select(ENTERED_CATEGORY_ELEMENT);
		sel.selectByVisibleText(enteredCategoryName);
		WebElement Selop= sel.getFirstSelectedOption();
		String enteredCategoryNamefromDropdown= Selop.getText();
		System.out.println(enteredCategoryNamefromDropdown);
	    Assert.assertEquals(enteredCategoryNamefromDropdown, enteredCategoryName, "Not Same");
	}
	
	public void addingDuplicateCategory(String categoryName) {
		ADD_CATEGORY_DATAFIELD_ELEMENT.sendKeys(categoryName);
		ADD_CATEGORY_SUBMITBUTTON_ELEMENT.click();
		ADD_CATEGORY_DATAFIELD_ELEMENT.sendKeys(categoryName);
		//waitForElement(driver, 5, ADD_CATEGORY_SUBMITBUTTON_ELEMENT);
		ADD_CATEGORY_SUBMITBUTTON_ELEMENT.click();
		if (categoryName==categoryName) {
			System.out.println("The category you want to add already exists:"+ categoryName);
		}
		Assert.assertEquals(categoryName, categoryName);
		driver.navigate().back();
		waitForElement(driver, 5, REMOVE_CATEGORY_ELEMENT);
		waitForElement(driver, 5, REMOVE_CATEGORY_YES_ELEMENT);
	}


	

	public void ValidateMonthdropdown() {
		Select select = new Select(DROPDOWN_ELEMENT);

		List<String> actualDropdownvalues = new ArrayList<String>();// array list object adding each value 
		select.getOptions();// returns list of WebElement
		for (WebElement element : select.getOptions()) {

			actualDropdownvalues.add(element.getText());

		}
		List<String> ExpectedDropdownvalues = new ArrayList<String>();
		
		ExpectedDropdownvalues.add("None");
		ExpectedDropdownvalues.add("Jan");
		ExpectedDropdownvalues.add("Feb");
		ExpectedDropdownvalues.add("Mar");
		ExpectedDropdownvalues.add("Apr");
		ExpectedDropdownvalues.add("May");
		ExpectedDropdownvalues.add("Jun");
		ExpectedDropdownvalues.add("Jul");
		ExpectedDropdownvalues.add("Aug");
		ExpectedDropdownvalues.add("Sep");
		ExpectedDropdownvalues.add("Oct");
		ExpectedDropdownvalues.add("Nov");
		ExpectedDropdownvalues.add("Dec");
		
		for (int i = 0 ;i<actualDropdownvalues.size();i++ ) {
			System.out.println("Actual :"+actualDropdownvalues.get(i) + "Expected: "+ ExpectedDropdownvalues.get(i));
			Assert.assertTrue(actualDropdownvalues.get(i).equals(ExpectedDropdownvalues.get(i)));
		}
	}
}
