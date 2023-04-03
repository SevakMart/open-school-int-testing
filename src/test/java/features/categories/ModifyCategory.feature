Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' modification
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid "adminEmail" email and "adminPsd" password

  Scenario: Modify data of parent categories by title
    When Create parentCategory with provided file
    Then Modify parent category by title
    Then Status code should be 200
    Then Validate response body by title
    Then Delete parentCategory from DB

  Scenario: Modify data of subcategories by title
    When Create parentCategory with provided file
    When Create subCategory
    Then Modify subcategory by title
    Then Status code should be 200
    Then Validate response body by title
    Then Delete category by Id
    Then Delete parentCategory from DB

  Scenario: Modify data of categories or subcategories by image
    When Create parentCategory with provided file
    Then Modify category or subcategory by image
    Then Status code should be 200
    Then Validate response body by image
    Then Delete parentCategory from DB

  Scenario: Modify data of categories or subcategories by invalid category id
    Then Modify category or subcategory by invalid category id
    Then Status code should be 400
    Then Validate error message about invalid category

  Scenario: Modify data of categories or subcategories by invalid parentCategoryId
    When Create parentCategory with provided file
    When Create subCategory
    Then Modify category or subcategory by invalid parentCategoryId
    Then Status code should be 400
    Then Validate error message about invalid category
    Then Delete category by Id
    Then Delete parentCategory from DB

  Scenario: Modify data of categories or subcategories by invalid title
    When Create parentCategory with provided file
    Then Modify category or subcategory by invalid title
    Then Status code should be 400
    Then Validate error message about invalid category
    Then Delete parentCategory from DB

  Scenario: Modify data of categories or subcategories by existing title
    When Create parentCategory with provided file
    Then Modify category or subcategory by existing title
    Then Status code should be 400
    Then Validate error message about existing title
    Then Delete parentCategory from DB
