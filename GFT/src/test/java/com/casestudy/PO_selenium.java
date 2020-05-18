package com.casestudy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PO_selenium 
{
	static WebDriver driver;
	
	@FindBy(how=How.ID,using="userName")
	 public static WebElement uname;
   
	@FindBy(how=How.ID,using="password")
      public static WebElement password;

    @FindBy(how=How.NAME,using="Login")
     public static WebElement Login_button;


}
