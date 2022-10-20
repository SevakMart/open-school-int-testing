Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' Id
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Find category or subcategory by id
    When Login by valid "<password>" password and "<email>" email
    Then Get all categories by id
#    Then Find category or subcategory by id
#    Then Status code should be <statusCode>
#    Then Validate categoryid success response values
#    Then Validate categories' response <id> values
    Examples:
      | password  | email                      | statusCode |
      | Test123@# | openschooltest78@gmail.com | 200        |