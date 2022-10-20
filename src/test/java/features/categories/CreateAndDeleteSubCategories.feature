Feature: API test for Open School: category-controller
  Description: The purpose of these tests is to cover all flows connected with creation  and delete SubCategory
  Open School Swagger URL : http://open-school-dev.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

  Background:
    Given Setup Rest Assured
    When Login by valid Test777$ password and hdiloyan88@gmail.com email
    Then Set Admin Token

  Scenario Outline: Create new SubCategory
    When Create new category with parent <id>, path <filePath>, title <title>
    Then Validate response JsonSchema "schema/getPostedSubCategoryRequestSchema.json"
    Then Status code should be <statusCode>
    #Then Validate new categories' response values
    Examples:
      | parentCategoryId | filePath                 | title           | statusCode |
      | 1                | src/test/image/new1.webp | TestSubCategory | 201        |