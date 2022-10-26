Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation categories
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured

  Scenario Outline: Create new Category
    When Login by valid "<password>" password and "<email>" email
    When Create Category where filePath is "<filePath>", image is "<title>"
    Then Status code should be 201
    Then Validate response JsonSchema "schemas/categorySchemas/getPostedParentCategoryRequestSchema.json"
    Examples:
      | password  | email                    |  filePath                 | title |
      | Test1111! | anidarbinyan14@yahoo.com        | src/test/image/new1.webp | Test1 |

  Scenario Outline: Create new category without attaching an image
    When Login by valid "<password>" password and "<email>" email
    When Create new category with "<title>"
    Then Status code should be 400
    Then Validate new categories' response values
    Examples:
      | password  | email                    |  title |
      | Test1111! | anidarbinyan14@yahoo.com |  Test  |


  Scenario Outline: Create new category without admin role
    When Login by valid "<password>" password and "<email>" email
    When Create Category where filePath is "<filePath>", image is "<title>"
    Then Status code should be 403
    Examples:
      | password  | email                      |  filePath                 | title |
      | Test123@# | openschooltest78@gmail.com |  src/test/image/new1.webp | Test  |
