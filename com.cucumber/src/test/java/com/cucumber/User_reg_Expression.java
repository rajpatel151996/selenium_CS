package com.cucumber;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.*;

public class User_reg_Expression
{
WebDriver driver;

@Given("^Navigate the TestMe app url$")
public void navigate_the_TestMe_app_url() throws Throwable
{
	 System.setProperty("webdriver.chrome.driver","C:\\Users\\RajPC\\Downloads\\chromedriver.exe");
	  driver=(WebDriver) new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
	  driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
	  PageFactory.initElements(driver,PageObject.class);
}

@When("^I will be on home page click on SignUp$")
public void i_will_be_on_home_page_click_on_SignUp() throws Throwable 
{
	  driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[2]/a")).click();
}

@When("^I will enter username\"([^\"]*)\"$")
public void i_will_enter_username(String un) throws Throwable 
{
	PageObject.user_Name.sendKeys(un);
}

@When("^I will enter firstname \"([^\"]*)\" and lastname \"([^\"]*)\"$")
public void i_will_enter_firstname_and_lastname(String fn, String ln) throws Throwable
{
	    PageObject.first_Name.sendKeys(fn);

	    PageObject.last_Name.sendKeys(ln);

}

@When("^I will enter password \"([^\"]*)\" and confirmpassword \"([^\"]*)\"$")
public void i_will_enter_password_and_confirmpassword(String pwd, String cpwd) throws Throwable
{
	    PageObject.pass_word.sendKeys(pwd);
	  
	    PageObject.pass_confirmation.sendKeys(cpwd);
}

@When("^I will select gender as Male$")
public void i_will_select_gender_as_Male() throws Throwable
{
    WebElement   r1=driver.findElement(By.xpath("//*[@id=\"gender\"]"));
    WebElement   r2=driver.findElement(By.xpath("//*[@id=\"gender\"]"));
    r1.click();
    Thread.sleep(1000);
}

@When("^I will enter emailid \"([^\"]*)\" and mobileno\"([^\"]*)\"$")
public void i_will_enter_emailid_and_mobileno(String e_id, String mob) throws Throwable
{
	   PageObject.email_Address.sendKeys(e_id);
	    
	    PageObject.mobile_Number.sendKeys(mob);
}

@When("^I will enter DOB \"([^\"]*)\" and Address\"([^\"]*)\"$")
public void i_will_enter_DOB_and_Address(String dob, String addr) throws Throwable
{
	    PageObject.do_b.sendKeys(dob);
	    PageObject.add_ress.sendKeys(addr);
}

@When("^I will select security question and enter answer \"([^\"]*)\"$")
public void i_will_select_security_question_and_enter_answer(String ans) throws Throwable
{
	    Select s1= new Select(driver.findElement(By.xpath("//*[@id=\"securityQuestion\"]")));

	    s1.selectByVisibleText("What is your Birth Place?");

	    PageObject.ans_wer.sendKeys(ans);
}

@When("^click on Register button$")
public void click_on_Register_button() throws Throwable
{
	  PageObject.bttm.click();
	    Thread.sleep(2000);
}

@Then("^I validate the registation$")
public void i_validate_the_registation() throws Throwable
{
	Assert.assertEquals("Login", driver.getTitle());
  System.out.println(" User Registered Succesfully!!! Please login");
  driver.close();
  
}


	
}
