Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario: Find all categories and relevant subcategories by all users
    When Login by valid "userEmail" email and "userPsd" password
    Then Get all categories and relevant subcategories
    Then Status code should be 200

  Scenario: Find all categories and relevant subcategories without login
    When Get all categories and relevant subcategories without login
    Then Status code should be 401

  Scenario: Find all categories by title mapped by subcategories
    When Login by valid "userEmail" email and "userPsd" password
    Then Get category by title
    Then Status code should be 200

  Scenario: Find category or subcategory by id
    When Login by valid "adminEmail" email and "adminPsd" password
    Then Create parentCategory with provided file
    Then Find category or subcategory by id
    Then Status code should be 200
    Then Validate categoryid success response values

  Scenario: Find all parent categories
    When Login by valid "adminEmail" email and "adminPsd" password
    Then Get all parent categories as list
    Then Status code should be 200
    Then Validate the count of response elements
    Then Validate by jsonSchema
