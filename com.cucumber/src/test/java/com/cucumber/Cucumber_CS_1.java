package com.cucumber;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.junit.Assertions;
import junit.framework.Assert;


public class Cucumber_CS_1
{	
	WebDriver driver;

@Given("^launch the TestMe app url$")
public void launch_the_TestMe_app_url() throws Throwable 
{            
	          System.setProperty("webdriver.chrome.driver","C:\\Users\\RajPC\\Downloads\\chromedriver.exe");
	          driver=new ChromeDriver();
	          driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS); 
	          driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
	          PageFactory.initElements(driver,PageObject.class);
	          driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();
}

//Invalidlogin

@When("^user on the login page and enter username\"([^\"]*)\"$")
public void user_on_the_login_page_and_enter_username(String un) throws Throwable 
{ 
	  PageObject.uname.sendKeys(un);	
}

@When("^click on login button$")
public void click_on_login_button() throws Throwable 
{
    PageObject.Login_button.click();	
}

@Then("^It should not be login and generate error message$")
public void it_should_not_be_login_and_generate_error_message() throws Throwable 
{
	String error=driver.findElement(By.xpath("/html/body/main/div/div/div/form/fieldset/div[2]/span")).getText();
	Assert.assertEquals("Please Enter Password", error);
	System.err.println("Please Enter Password");
	driver.close();
}

//outline

@When("^Enter the  username \"([^\"]*)\" and  password \"([^\"]*)\"$")
public void enter_the_username_and_password(String a1, String a2) throws Throwable
{
	           PageObject.uname.sendKeys(a1);
	           PageObject.password.sendKeys(a2);
	           PageObject.Login_button.click();	   
}

@SuppressWarnings("deprecation")
@Then("^verify and close the browser$")
public void verify_and_close_the_browser() throws Throwable
{     
	       if(driver.getPageSource().contains("Hi, lalitha"))
                 {
		            String s1=driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul")).getText();
	                System.out.println(s1);
	                System.out.println("lalitha is login "+driver);
	    	        driver.close();
	              }
	      else if(driver.getPageSource().contains("Hi, Admin"))
	          {                               
	    	      System.out.println(driver.findElement(By.xpath("/html/body/header/div/b")).getText());
	    	      System.out.println("Admin is login ");
	    	      driver.close();
	          }
	       else
	          {
	    	       System.out.println("no user found");
	    	        driver.close();
	          }
}

//ProductnotAvailable

@When("^user login and clicking on the serach and enter the product$")
public void user_login_and_clicking_on_the_serach_and_enter_the_product() throws Throwable 
{
	        PageObject.user_login();
	        WebElement text= driver.findElement(By.id("myInput"));
             text.sendKeys("Computer");
             driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();     
}

@Then("^It is showing product not available$")
public void it_is_showing_product_not_available() throws Throwable
{
            String msg=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/h3")).getText();
            Assert.assertEquals("Sorry no products available in this category. Please try some other", msg);
            System.out.println("no products available ");
	        driver.close();
}

 //Productsearch

@SuppressWarnings("deprecation")
@When("^user login and serach the particular product$")
public void user_login_and_serach_the_particular_product() throws Throwable
{   
	                PageObject.user_login();
	              //  Thread.sleep(2000);
		            WebElement text= driver.findElement(By.id("myInput"));
		            Actions action=new Actions(driver);
                    action.sendKeys(text, Keys.SHIFT).sendKeys("H").pause(2000).sendKeys("e").pause(2000).sendKeys("a").sendKeys("d").pause(2000)
                    .sendKeys(Keys.ARROW_DOWN).build().perform();
		            driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
		            Thread.sleep(2000);    
}

@Then("^verify the product$")
public void verify_the_product() throws Throwable 
{
	               String expect_text=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/center/p")).getText();
	               System.out.println(" Searched Product is : "+expect_text);
           if(expect_text.equalsIgnoreCase("Blue tooth head phone"))
                  {
	                  System.out.println("Searched product is correct :"+expect_text+"  as expected");
	                  driver.close();   
                  }
           else
                 {
        	         System.out.println("Product is wrong !!!! Go back to the home and  Search again!!!!!!");
        	         driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[1]")).click();     //home
                 }   
}

// payment

@When("^search the product like \"([^\"]*)\"$")
public void search_the_product_like(String arg1) throws Throwable 
{
	             PageObject.user_login();
	        
	             Thread.sleep(2000);
	             WebElement text= driver.findElement(By.id("myInput"));
	             text.sendKeys("Headphone");
	                                   /* Actions action=new Actions(driver);
                                         action.sendKeys(text, Keys.SHIFT).sendKeys("H").pause(1000).sendKeys("e").pause(1000).sendKeys("a").pause(1000).sendKeys("d").pause(1000)
                                         .sendKeys(Keys.ARROW_DOWN).build().perform();
	                                    */
	            driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
}

@When("^try to processed to payment without adding item in the cart$")
public void try_to_processed_to_payment_without_adding_item_in_the_cart() throws Throwable
{
	      if(driver.getPageSource().contains(" Cart  "))
	        {
		        System.out.println("user can processed to pay for the product");
		       driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();
		    }
	     else
		   {
		       System.out.println("Go back  and add the product into the cart");
		       driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();    //addtocart 
		   }
}
@When("^TestMe app doesnt display the cart icon$")
public void testme_app_doesnt_display_the_cart_icon() throws Throwable 
{
	    boolean cart= driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).isDisplayed();
	    boolean cart1= driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).isEnabled();          //cart button
	     if(cart==true && cart1==true)
	     {
	    	 System.out.println("cart icon  is present click on it  & user can processed to pay ");
	    	 driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();                                  //click on cart icon in to 
	     }
	     else
	     {
	    	 System.out.println("cart icon is not display");
	     }	
}
@Then("^If TestMe app  display the cart icon,user can  processed to pay$")
public void if_TestMe_app_display_the_cart_icon_user_can_processed_to_pay() throws Throwable
{
            //Get the text of added product 
             	String addproduct =driver.findElement(By.xpath(" //*[@id=\"cart\"]/tbody/tr/td[1]/div/div/p")).getText();
	            System.out.println(addproduct);
	
	         //verify the product which is added to the cart
	             junit.framework.Assert.assertTrue(addproduct.contains("Blue tooth head phone"));
	             System.out.println(addproduct);
	  
	         //increase the quantity
                WebElement quantityincre = driver.findElement(By.xpath("//*[@id=\"quantity_1\"]"));
                quantityincre.click(); 
                quantityincre.sendKeys(Keys.chord(Keys.SHIFT.LEFT.BACK_SPACE,"2"));
                Thread.sleep(2000);
                driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();                                            //checkoutbutton
                Thread.sleep(2000);
        
              //address
	              WebElement  addrs= driver.findElement(By.xpath("//*[@id=\"add1\"]"));
	               addrs.clear();
              //addrs.sendKeys("Khardi bypass ,Pune");                                                                                        //Address textarea 1st
                 WebElement checkbox=  driver.findElement(By.xpath("//*[@id=\"choice-dollar\"]"));                   //Address textarea 2nd
                 checkbox.click();
                 System.out.println(checkbox.isSelected());
                 driver.findElement(By.xpath("//*[@id=\"add2\"]")).sendKeys("Khardai bypass ,Pune");
                 driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();  
      
               //payment
                WebElement radiob=driver.findElement(By.xpath("//*[@id=\"swit\"]/div[8]/div/label/i"));
                radiob.click();
                driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();                                                          //paymentbutton
                driver.close();
}
 }
