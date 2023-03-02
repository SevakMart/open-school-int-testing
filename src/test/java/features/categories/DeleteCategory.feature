Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' delete
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "adminEmail" email and "adminPsd" password

  Scenario: Verify that SubCategory is deleted
    When Create parentCategory with provided file
    When Create subCategory
    Then Delete category by Id
    Then Status code should be 204
    Then Delete parentCategory from DB

  Scenario: Verify that parentCategory delete is not allowed
    Then Create parentCategory with provided file
    Then Calling delete Parentcategory by Id
    Then Status code should be 400
    Then Validate message of response
    Then Delete parentCategory from DB


  Scenario: Verify that user without admin role cannot delete category
    When Create parentCategory with provided file
    When Login by valid "userEmail" email and "userPsd" password
    Then Calling delete Parentcategory by Id
    Then Status code should be 403
    Then Delete parentCategory from DB
