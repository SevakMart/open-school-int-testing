Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' Id
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Find category or subcategory by id
    When Login by valid "<password>" password and "<email>" email
    Then Get category from DB
    Then Find category or subcategory by id
    Then Status code should be 200
    Then Validate categoryid success response values
    Examples:
      | password  | email                    |
      | Test1111! | anidarbinyan14@yahoo.com |
