Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with categories' modification
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Modify data of categories or subcategories by title
    When Login by valid "<password>" password and "<email>" email
    Then Get category from DB
    Then Modify category or subcategory by "<title>" and <parentCategoryId>
    Then Status code should be 200
    Then Validate response body by title
    Examples:
      | password  | email                    | title    | parentCategoryId |
      | Test1111! | anidarbinyan14@yahoo.com | TestSub3 | 4                |

  Scenario Outline: Modify data of categories or subcategories by image
    When Login by valid "<password>" password and "<email>" email
    Then Get category from DB
    Then Modify category or subcategory by "<imageFilePath>"
    Then Status code should be 200
    Then Validate response body by image
    Examples:
      | password  | email                    | imageFilePath            |
      | Test1111! | anidarbinyan14@yahoo.com | src/test/image/new1.webp |