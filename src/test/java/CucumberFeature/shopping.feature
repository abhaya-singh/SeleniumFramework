
@Tag1
Feature: Happy shopping

Background:
Given I am on website Login page

@happyShopping
Scenario Outline: Positive test of placing an order

Given Login with the given <username> and password <password>
When I add a given product <product> into the cart
And checkout for the product <product> added into the cart for the given <country>
Then display <successMsg> confirmation message on the checkout screen

Examples:

| username           | password  | product     | country | successMsg             |
| abhay123@gmail.com | Abhay@123 | ZARA COAT 3 | India   | THANKYOU FOR THE ORDER.|

