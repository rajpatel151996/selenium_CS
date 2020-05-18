Feature: 
check the functionality of all TestMe app
Background:
Given  launch the TestMe app url


@Invalidlogin
Scenario: login without entering password
When user on the login page and enter username"Lalitha"
And click on login button 
Then It should not be login and generate error message

   
#@outline
#Scenario Outline: login fuctionality by outline datadriven
#When Enter the  username "<username>" and  password "<password>"
#Then   verify and close the browser
#Examples:
#|username||password|
#|lalitha||Password123|
#|Admin||Password456|


#@Productnotavailable
#Scenario: Product not available
#When user login and clicking on the serach and enter the product 
#Then It is showing product not available

#@productserach
#Scenario: Search the product just type four character and got appropriate result
#When user login and serach the particular product 
#Then verify the product 


#@payment
#Scenario: user move to cart without adding any item in it
#When search the product like "headphone"
#And try to processed to payment without adding item in the cart
#And TestMe app doesnt display the cart icon
#Then   If TestMe app  display the cart icon,user can  processed to pay









