Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Find all categories and relevant subcategories by all users
    When Login by valid "<password>" password and "<email>" email
    Then Get all categories and relevant subcategories
    Then Status code should be 200
    Examples:
      | password  | email                      |
      | Test123@# | openschooltest78@gmail.com |
      | Test1111! | anidarbinyan14@yahoo.com   |

  Scenario: Find all categories and relevant subcategories without login
    When Get all categories and relevant subcategories without login
    Then Status code should be 401

  Scenario Outline: Find all categories by title mapped by subcategories
    When Login by valid "<password>" password and "<email>" email
    Then Get any category title from DB
    Then Get category by title
    Then Status code should be 200
    Examples:
      | password  | email                      |
      | Test123@# | openschooltest78@gmail.com |

  Scenario Outline: Find all parent categories
    When Login by valid "<password>" password and "<email>" email
    Then Get all parent categories
    Then Status code should be 200
    Examples:
      | password  | email                      |
      | Test123@# | openschooltest78@gmail.com |
