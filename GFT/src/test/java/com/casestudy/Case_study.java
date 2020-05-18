package com.casestudy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Case_study
{
	@SuppressWarnings("deprecation")
	ExtentHtmlReporter htmlReporter;
	ExtentTest logger;
    ExtentReports extent;
	WebDriver driver;
	             //@SuppressWarnings annotation type allows Java programmers to disable compilation warnings for a certain part of a program
@SuppressWarnings("deprecation")
@BeforeTest
public void launch() 
{
	   System.setProperty("webdriver.chrome.driver","C:\\Users\\RajPC\\Downloads\\chromedriver.exe");
	   driver=new ChromeDriver();
	   driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
	   PageFactory.initElements(driver,PO_selenium.class);
	   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	    htmlReporter=new  ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/CaseStudyReport.html");
		extent =new ExtentReports();
	    extent.attachReporter(htmlReporter);
	    extent.setSystemInfo("Host Name","GFT NextGen Testing Stream");
	    extent.setSystemInfo("Envirnoment","Automation Testing-selenium");
	    extent.setSystemInfo("User Name","Raj Patel");
	    htmlReporter.config().setDocumentTitle("TestMe App report");
	    htmlReporter.config().setReportName("CaseStudy for TestMe App Functionality");
	    htmlReporter.config().setTheme(Theme.STANDARD);
}
	 
	
	@Test
	  public void Login()throws InterruptedException, IOException
	  { 
		  logger = extent.createTest("login Functionality");
		  
		  driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul/li[1]/a")).click();
		  PO_selenium.uname.sendKeys("Lalitha");
		  PO_selenium.password.sendKeys("Password123");
		  PO_selenium.Login_button.click();
		                                                                           /*driver.findElement(By.id("userName")).sendKeys("Lalitha");
		                                                                           driver.findElement(By.id("password")).sendKeys("Password123");
			                                                                        driver.findElement(By.name("Login")).click();
	                                                                                */
	  	    WebElement text=driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/ul"));	  //login  
			String s=text.getText();                                                                                                                        
			System.out.println(s);
		    Assert.assertTrue(s.contains("Hi, Lalitha SignOut"));
            logger.log(Status.PASS,MarkupHelper.createLabel("Username  "+s+" is display in user home page ,so  login functionality  is passed!!!!! ",ExtentColor.GREEN));
		    System.out.println("successfully");
		     logger.log(Status.PASS,MarkupHelper.createLabel("PageTitle is : "+driver.getTitle()+"  functionality   is passed ",ExtentColor.GREEN));
	  }
	
  @SuppressWarnings("static-access")
@Test
	  public void product_search() throws InterruptedException 
	  {
		     logger = extent.createTest("Poduct_Search");
		     
		   WebElement text= driver.findElement(By.id("myInput"));
		   Actions action=new Actions(driver);
           action.sendKeys(text, Keys.SHIFT).sendKeys("H").pause(2000).sendKeys("e").pause(2000).sendKeys("a").sendKeys("d").pause(2000)
           .sendKeys(Keys.ARROW_DOWN).build().perform();
		   Thread.sleep(2000);
		   driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
		   String expect_text=driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/center/p")).getText();
	    	System.out.println("user searched Product is  "+expect_text);
		
		//Scenario 5
//verify the product Searched product	    	
		  Assert.assertTrue(expect_text.contains("Blue tooth head phone"));
		  System.out.println(expect_text);
		  logger.log(Status.PASS,MarkupHelper.createLabel( "PageTitle is : " +driver.getTitle()+", Searched product   " +  expect_text  +"   is correct product!!!!!!!",ExtentColor.GREEN));
  
		   logger = extent.createTest("Add Product to cart");
		   
		   driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();  //add to cart
           driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/div/div/div[2]/div/a[2]")).click();    //cart button
          Thread.sleep(2000);
       
 //verify
        	String addproduct =driver.findElement(By.xpath(" //*[@id=\"cart\"]/tbody/tr/td[1]/div/div/p")).getText();
		    System.out.println(addproduct);
		
//verify the product which is added to the cart
		  Assert.assertTrue(addproduct.contains("Blue tooth head phone"));
		  System.out.println(addproduct);
		  logger.log(Status.PASS,MarkupHelper.createLabel("Added  product  " +addproduct +"  is  a correct product in  the cart!!!!!!",ExtentColor.GREEN));
          
          WebElement quantityincre = driver.findElement(By.xpath("//*[@id=\"quantity_1\"]"));
          quantityincre.click(); 
          quantityincre.sendKeys(Keys.chord(Keys.SHIFT.LEFT.BACK_SPACE,"2"));
           Thread.sleep(2000);
	      driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();                                            //checkoutbutton
	        
//address
	      logger = extent.createTest("User Cart checkout and  Address box ");
	      
		     WebElement  addrs= driver.findElement(By.xpath("//*[@id=\"add1\"]"));
		                    addrs.clear();
		                    addrs.sendKeys("Khardi bypass ,Pune");                                                                             //Address textarea 1st
	                       Thread.sleep(1000);
	          WebElement checkbox=  driver.findElement(By.xpath("//*[@id=\"choice-dollar\"]"));                        //Address textarea 2nd
	                  checkbox.click();
	                  System.out.println(checkbox.isSelected());
	         driver.findElement(By.xpath("//*[@id=\"add2\"]")).sendKeys("Khardai bypass ,Pune");
	         logger.log(Status.PASS,MarkupHelper.createLabel("PageTitle is:  "+driver.getTitle()+" Testcase  is passed!!!!!!  ",ExtentColor.GREEN));
	         driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();  
	       
//payment 
 		    logger = extent.createTest("Payment Gateway");
 		    
              WebElement radiob=driver.findElement(By.xpath("//*[@id=\"swit\"]/div[8]/div/label/i"));
          	  radiob.click();
              Thread.sleep(2000);
             driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();                                                          //paymentbutton
             Thread.sleep(2000);
             logger.log(Status.PASS,MarkupHelper.createLabel( "Your payment was successfull ,THANK YOU !!!!!!",ExtentColor.INDIGO));
             logger.log(Status.PASS,MarkupHelper.createLabel( "PageTitle is :   "+driver.getTitle()+"  is passed !!!!!!!!",ExtentColor.GREEN));
          
   
	  }
	
	@Test
	public void address() throws InterruptedException
	{
		     logger = extent.createTest("Address fail");
		     WebElement  addrs= driver.findElement(By.xpath("//*[@id=\"add1\"]"));
		                     addrs.clear();
	                         addrs.sendKeys(",Khardi bypass ,Pune");                                                                             //Address textarea 1st
	             Thread.sleep(2000);
	          WebElement checkbox=  driver.findElement(By.xpath("//*[@id=\"choice-dollar\"]"));                   //Address textarea 2nd
	                  checkbox.click();
	          System.out.println(checkbox.isSelected());
	                 //  driver.findElement(By.xpath("//*[@id=\"add2\"]")).sendKeys("pune");
	                            //d.click();
	                           // d.sendKeys(Keys.chord(Keys.SHIFT.ARROW_UP.BACK_SPACE,"Pune"));
	                           // Thread.sleep(4000);
	                            //action.doubleClick();
	     	driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();              //processedtopay
	    	 logger.log(Status.PASS,MarkupHelper.createLabel(driver.getTitle()+"  is passTest",ExtentColor.GREEN));
	 
	}
	
	@Test(enabled=false)
	 public void payment()
    {
		          logger = extent.createTest("passTest");
		          driver.findElement(By.xpath("/html/body/b/div/div/div[1]/div/div[2]/div[3]/div/form[2]/input")).click();  
		          WebElement radiob=driver.findElement(By.xpath("//*[@id=\"swit\"]/div[8]/div/label/i"));
                	radiob.click();
   	             driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();                                                          //paymentbutton
   	             logger.log(Status.PASS,MarkupHelper.createLabel(driver.getTitle()+"  is passTest",ExtentColor.GREEN));
    }
	
	@AfterMethod
	  public void getResult(ITestResult result) throws IOException
	  {
		  if(result.getStatus()==ITestResult.FAILURE) 
		  {
			  logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+ "- Test Case Failed",ExtentColor.RED)); 
	          TakesScreenshot snap=(TakesScreenshot)driver;
	          File src=snap.getScreenshotAs(OutputType.FILE);
	          String path=System.getProperty("user.dir") +"/test-output/"+result.getName()+".png";
	          FileUtils.copyFile(src,new File(path));
	           logger.addScreenCaptureFromPath(path,result.getName());
			   logger.log(Status.FAIL,MarkupHelper.createLabel(result.getThrowable() + "TestCase Failed",ExtentColor.RED));
	      }
		  else if(result.getStatus() == ITestResult.SKIP)
		  {
			  logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName() + " Test case Skipped",ExtentColor.ORANGE));
		  }
}
	  @AfterTest
	  public void endReport()
	  {
		  extent.flush();
		  driver.close();
	  }
}
