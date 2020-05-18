Feature: TestMe app functionality 
 


@expression
Scenario: I want to register successfully
 Given  Navigate the TestMe app url
When I will be on home page click on SignUp 
And   I will enter username"Mayank2222"
And  I will enter firstname "mayank" and lastname "Patel"
And  I will enter password "mayank123456789" and confirmpassword "mayank123456789"
And I will select gender as Male
And I will enter emailid "mayank123@gmail.com" and mobileno"1234567890"
And I will enter DOB "2/02/2001" and Address"Bangalore"
And I will select security question and enter answer "mumabai"
And click on Register button
Then I validate the registation  


