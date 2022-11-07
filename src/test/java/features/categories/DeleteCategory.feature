Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' delete
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Verify that SubCategory is deleted
    When Login by valid "<email>" email and "<password>" password
    When Create parentCategory with provided file
    When Create subCategory
    Then Delete category by Id
    Then Status code should be 204
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario Outline: Verify that parentCategory delete is not allowed
    When Login by valid "<email>" email and "<password>" password
    Then Create parentCategory with provided file
    Then Calling delete Parentcategory by Id
    Then Status code should be 400
    Then Validate message of response
    Examples:
      | password | email      |
      | adminPsd | adminEmail |

  Scenario: Verify that user without admin role cannot delete category
    When Login by valid "adminEmail" email and "adminPsd" password
    When Create parentCategory with provided file
    When Login by valid "userEmail" email and "userPsd" password
    Then Calling delete Parentcategory by Id
    Then Status code should be 403
