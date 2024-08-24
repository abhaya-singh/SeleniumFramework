
@Tag1
Feature: errorValidation

Background:
Given I am on website Login page

@errorValidation
Scenario Outline: negative test of user login 

Given Login with the incorrect <username> and password <password>
Then Display invalid creds message <errorMsg> on login screen

Examples:

| username           | password    | errorMsg                    |
| abhay1234@gmail.com | Abhay@1234 | Incorrect email or password.|

