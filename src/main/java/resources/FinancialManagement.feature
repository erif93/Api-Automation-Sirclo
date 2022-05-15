@Api-Sirclo
Feature: API main home page

  @TC-01 @Api-Sirclo
  Scenario: As a user i can get response from main homepage
  Given user send "GET" request to "homepage" endpoint
  Then user should get response "Welcome!"
  
  @TC-02 @Api-Sirclo
  Scenario: As a user i can get response wrong username and password
  Given user send "POST" request to logout end point
  When user send "POST" request with "incorrect" login credential
  Then user should get wrong login response "The password or username is wrong"
   
  @TC-03 @Api-Sirclo
  Scenario: As a user i can get response Welcome with login
  Given user send "POST" request to "logout" endpoint
  When user send "POST" request with "valid" login credential
  Then user should get response "Welcome!"
  
  @TC-05 @Api-Sirclo
  Scenario: As a user i can logout from system
   Given user send "POST" request to logout endpoint
   Then  user should get response "Welcome!"
    
  @TC-06 @Api-Sirclo
  Scenario: As a user i cannot get response from data endpoint without login
   Given user send "GET" request to "data" endpoint
   When user send "POST" request with "valid" login credential
   Then user should get response "Welcome!"
     
  @TC-07 @Api-Sirclo
   Scenario: As a user i can get response from data endpoint with login
   Given user send "POST" request to login end point with "valid" credential
   And user send "GET" request to data endpoint
   Then  user should get response "Welcome!"
   
  @TC-08 @Api-Sirclo
  Scenario: As a user i cannot get response Welcome without login
  Given user send "POST" request to "logout" endpoint
  When user send "POST" request with "empty" login credential
  Then user should get empty login response "/login"
  