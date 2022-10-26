Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with delete categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Verify that SubCategory is deleted
    When Login by valid "<password>" password and "<email>" email
    Then Get Subcategory from DB
    Then Delete category by Id
    Then Status code should be 204
    Examples:
      | password  | email                    |
      | Test1111! | anidarbinyan14@yahoo.com |

  Scenario Outline: Verify that parentCategory delete is not allowed
    When Login by valid "<password>" password and "<email>" email
    Then Get category from DB
    Then Delete category by Id
    Then Status code should be 400
    Then Validate message of response
    Examples:
      | password  | email                    |
      | Test1111! | anidarbinyan14@yahoo.com |

  Scenario Outline: Verify that user cannot delete category
    When Login by valid "<password>" password and "<email>" email
    Then Get category from DB
    Then Delete category by Id
    Then Status code should be 403
    Examples:
      | password  | email                      |
      | Test123@# | openschooltest78@gmail.com |