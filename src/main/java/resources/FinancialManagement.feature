@Api-Sirclo
Feature: API main home page

  @TC-01 @Api-Sirclo
  Scenario: As a user i can get response from main homepage
  Given user send "GET" request to "homepage" endpoint
  Then  user should get response "Welcome!"